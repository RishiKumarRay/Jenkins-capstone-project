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
              sh 'mvn test'
          }
      }
      
      stage('Package') 
      {
          steps
          {
              
              sh 'mvn package'
          }
      } 
      
      stage('Deploy')
      {
          steps
          {
              sshPublisher(publishers: [sshPublisherDesc(configName: 'deploy', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'java -jar target/*.jar', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)])
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
