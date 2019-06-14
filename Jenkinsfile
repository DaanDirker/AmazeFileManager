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
                sh './gradlew test'
            }
        }
        stage ("Sonarqube analysis") {
            steps {
                sh './gradlew sonarqube'
            }
        }
        stage('Archive Results') {
            steps {
                archiveArtifacts '**/*.apk'
                junit 'app/build/outputs/androidTest-results/connected/flavors/FDROID/*.xml'
            }
        }
    }
}
