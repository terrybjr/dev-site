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
                    sh '''
                        docker ps -q -f ancestor=btd-app:latest | xargs -r docker stop
                        docker ps -a -q -f ancestor=btd-app:latest | xargs -r docker rm
                        docker run -d -p 9080:9080 btd-app:latest
                    '''
                }
            }
        }
    }
}