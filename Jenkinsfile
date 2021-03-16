pipeline {
    agent any

    tools {

        maven 'Maven 3.6.3'
    }

    stages {
        stage('Test'){
                // To run Maven on a Windows agent, use
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean verify"
                }
            }
        }
	}
}