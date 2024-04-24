pipeline {
    agent any // This pipeline can be executed on any available agent
    stages {
        stage('Clean') { // Stage for cleaning the project
            steps {
                sh 'mvn clean' // Execute 'mvn clean' command
            }
        }
        stage('Install') { // Stage for installing the project dependencies
            steps {
                sh 'mvn install' // Execute 'mvn install' command
            }
        }
        stage('Build Docker Image') { // Stage for building the Docker image
            steps {
                script {
                    docker.build('btd-app:latest', './btd-app-ear') // Build Docker image from the Dockerfile in the 'btd-app-ear' directory
                    sh 'docker image prune -f' // Remove all dangling Docker images
                }
            }
        }
        stage('Replace Image in Container') { // Stage for replacing the running Docker container with a new one
            steps {
                script {
                    sh '''
                        docker ps -q -f ancestor=btd-app:latest | xargs -r docker stop // Stop any running Docker containers from the 'btd-app:latest' image
                        docker ps -a -q -f ancestor=btd-app:latest | xargs -r docker rm // Remove any Docker containers from the 'btd-app:latest' image
                        docker run -d -p 9080:9080 btd-app:latest // Run a new Docker container from the 'btd-app:latest' image
                    '''
                }
            }
        }
    }
}