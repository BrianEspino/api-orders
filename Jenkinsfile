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
                bat 'mvn clean package'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Construir Docker') {
            steps {
                bat 'docker build -t api-orders .'
            }
        }

        stage('Levantar Contenedor') {
            steps {
                bat 'docker rm -f api-orders'
                bat 'docker run -d --name api-orders -p 63342:8080 api-orders'
            }
        }
    }
}