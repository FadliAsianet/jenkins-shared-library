def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('Install Dependencies') {
                steps {
                    echo "Install package PHP untuk ${config.appName}..."
                    sh 'composer install --no-dev --optimize-autoloader'
                }
            }
            stage('Test & Build Assets') {
                steps {
                    script {
                        if (fileExists('package.json')) {
                            echo "Building frontend assets..."
                            sh 'npm install && npm run build'
                        }
                    }
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying ${config.appName} ke server ${config.serverIp}..."
                    sh "echo 'Deploy PHP sukses ke ${config.serverIp}'"
                }
            }
        }
    }
}
