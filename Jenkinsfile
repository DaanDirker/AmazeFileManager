pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage ("Build") {
            steps {
                sh "./gradlew clean"
                sh "./gradlew assembleDebug"
            }
        }
        stage ("Unit testing") {
            steps {
                sh './gradlew connectedDebugAndroidTest'
                junit '**/test-results/**/*.xml'
            }
        }
        stage('Archive APK') {
            steps {
                archiveArtifacts '**/*.apk'
            }
        }
    }
}
