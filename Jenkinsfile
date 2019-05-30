pipeline {
    agent any
    
    stages {
        stage ("Build") {
            steps {
		sh "./gradlew yes | $ANDROID_HOME/bin/sdkmanager 'platforms;android-28'"
		sh "./gradlew yes | $ANDROID_HOME/bin/sdkmanager 'build-tools;27.0.3'"
                sh "./gradlew assembleDebug"
            }
        }
    }
}
