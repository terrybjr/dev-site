pipeline {
    agent any
    environment {
        SDKMAN_DIR = tool 'SDKMAN' // Ensure 'SDKMAN' is configured as a tool in Jenkins
    }
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    sh 'source "$SDKMAN_DIR/bin/sdkman-init.sh"'
                }
            }
        }
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Install') {
            steps {
                sh 'mvn install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('my-image:latest', '.')
                }
            }
        }
        stage('Replace Image in Container') {
            steps {
                script {
                    docker.image('my-image:latest').withRun('-d -p 8080:8080') { c ->
                        echo 'Container ID: ' + c.id
                    }
                }
            }
        }
    }
}
