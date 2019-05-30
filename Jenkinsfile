pipeline {
    agent any
    
    stages {
        stage ("Build") {
            steps {
		sh "./gradlew yes | $ANDROID_HOME/tools/bin/sdkmanager "platforms;android-28""
		sh "./gradlew yes | $ANDROID_HOME/tools/bin/sdkmanager "build-tools;27.0.3""
                sh "./gradlew assembleDebug"
            }
        }
    }
}
