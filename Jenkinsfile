pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Install') {
            steps {
                bat 'mvn install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('btd-app:latest', '.')
                }
            }
        }
        stage('Replace Image in Container') {
            steps {
                script {
                    docker.image('btd-app:latest').withRun('-d -p 9080:9080') { c ->
                        echo 'Container ID: ' + c.id
                    }
                }
            }
        }
    }
}
