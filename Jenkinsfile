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
                sh './gradlew test'
                sh './gradlew deviceAndroidTest'
            }
        }
        stage('Archive APK') {
            steps {
                archiveArtifacts '**/*.apk'
            }
        }
    }
}
