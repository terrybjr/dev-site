pipeline {
    agent any
    stages {
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
                    docker.build('btd-app:latest', './btd-app-ear')
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