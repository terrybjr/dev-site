pipeline {
    agent any
    stages {
        stage('Setup Environment') {
            steps {
                script {
                    sh(script: '''
                        sudo apt update
                        sudo apt install -y openjdk-17-jdk
                        echo "JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")" >> $HOME/.bashrc
                        source $HOME/.bashrc
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