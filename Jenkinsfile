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
        stage('Sonar Analysis') {
            steps {
                dir('swapi-tests') {
                    script {
                        def scannerHome = tool 'SONAR_SCANNER'

                        withSonarQubeEnv('SONAR_LOCAL') {
                            withEnv(["SCANNER_HOME=${scannerHome}"]) {
                                bat '''
                                    @echo off
                                    call "%SCANNER_HOME%\\bin\\sonar-scanner.bat" ^
                                    -Dsonar.projectKey=SWAPIteste ^
                                    -Dsonar.projectName=SWAPIteste ^
                                    -Dsonar.sources=src ^
                                    -Dsonar.java.binaries=target/classes ^
                                    -Dsonar.tests=src/test/java
                                '''
                            }
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    script {
                        def qualityGate = waitForQualityGate()

                        if (qualityGate.status != 'OK') {
                            error "Quality Gate reprovado: ${qualityGate.status}"
                        }
                    }
                }
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