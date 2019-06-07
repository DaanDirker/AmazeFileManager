pipeline {
    agent {
        label any
    }
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage ("Compile") {
            steps {
                sh "./gradlew compileDebugSources"
            }
        }
        stage ("Unit testing") {
            steps {
                sh './gradlew testDebugUnitTest testDebugUnitTest'
                junit '**/TEST-*.xml'
            }
        }
        // stage("SonarQube analysis") {
        //     environment {
        //         scannerHome = tool 'SonarQubeScanner'
        //     }

        //     steps {
        //         withSonarQubeEnv('sonarqube') {
        //             sh "${scannerHome}/bin/sonar-scanner"
        //         }

        //         timeout(time: 10, unit: 'MINUTES') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }
        stage('Build APK') {
            steps {
                sh './gradlew assembleDebug'
                archiveArtifacts '**/*.apk'
            }
        }
    }
}
