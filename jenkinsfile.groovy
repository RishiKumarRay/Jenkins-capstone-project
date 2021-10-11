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
      stage('Build')
      {
          steps
          {   echo "Building"
              sh 'mvn compile'
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
      
   }
}
