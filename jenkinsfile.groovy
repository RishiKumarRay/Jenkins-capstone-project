pipeline{
    agent 
    {
        label 'slave'
    }
    tools
    {
        maven 'Maven'
        jdk 'jdk'
    }

   stages{
       stage('clean workspaces')
       {
           steps 
           {
               sh 'mvn clean'
           }
       }
      stage('test') 
      {
          steps
          {
              echo "Checking Testcases"
              sh 'mvn clean test'
          }
      }

      
   }
   post {
        always{
            emailext body: '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:
Check console output at $BUILD_URL to view the results.''', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'rishirai760@gmail.com'
}
    }
}
