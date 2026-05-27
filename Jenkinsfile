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

        stage('Compilar') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Pruebas') {
            steps {
                bat 'mvn test'
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
    }
}