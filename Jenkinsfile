pipeline {
    agent any

    stages {

        stage('SWAPI Test') {
            steps {
                dir('swapi-tests') {
                    git branch: 'main',
                        url: 'https://github.com/danmsj/SWAPIteste'

                    bat 'mvn clean test'
                }
            }
        }

    post {
        success {
            echo 'Pipeline executado com sucesso!'
        }

        failure {
            echo 'Pipeline apresentou falha. Verifique o console do Jenkins.'
        }

        always {
            echo 'Pipeline finalizado.'
        }
    }
}