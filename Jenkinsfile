pipeline {
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
        stage('Build APK') {
            steps {
                sh './gradlew assembleDebug'
                archiveArtifacts '**/*.apk'
            }
        }
    }
}
