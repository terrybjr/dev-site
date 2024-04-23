pipeline {
    agent any
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    // Install SDKMAN
                    sh 'curl -s "https://get.sdkman.io" | bash'
                    sh 'source "$HOME/.sdkman/bin/sdkman-init.sh"'
                    // Install Java 21
                    sh 'sdk install java 21.0.0.j9-adpt'
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
