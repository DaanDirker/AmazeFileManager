pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }

    stages {
        stage ("Build") {
            steps {
                sh "./gradlew clean assembleDebug"
            }
        }
        stage ("Unit testing") {
            steps {
                lock('emulator') {
                    sh './gradlew connectedCheck'
                }
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
