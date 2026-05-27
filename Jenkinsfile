pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'MAVEN_HOME'
    }

    stages {

        stage('Clonar') {
            steps {
                git branch: 'main', url: 'https://github.com/BrianEspino/api-orders.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Construir Docker') {
            steps {
                sh 'docker build -t api-orders .'
            }
        }

        stage('Levantar Contenedor') {
            steps {
                sh 'docker rm -f api-orders || true'
                sh 'docker run -d --name api-orders -p 63342:8080 api-orders'
            }
        }
    }
}