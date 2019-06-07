pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage ("Compile") {
            steps {
                sh "./gradlew clean"
                sh "./gradlew assembleDebug"
            }
        }
        stage ("Unit testing") {
            steps {
                sh './gradlew test'
                sh './gradlew connectedAndroidTest'
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
