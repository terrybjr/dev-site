pipeline {
    agent any
    environment {
        SDKMAN_DIR = "/var/jenkins_home/.sdkman"
    }
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    sh(script: '''
                        curl -s "https://get.sdkman.io" | bash
                        . "$SDKMAN_DIR/bin/sdkman-init.sh"
                        sdk install java 17.0.1.j9-adpt
                    ''', returnStdout: true, shell: '/bin/bash')
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