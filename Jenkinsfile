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
                sh './gradlew assembleAndroidTest'
                sh './gradlew connectedAndroidTest'
            }
        }
        stage('Archive Results') {
            steps {
                archiveArtifacts '**/*.apk'
                junit '**/TEST-*.xml'
            }
        }
    }
}
