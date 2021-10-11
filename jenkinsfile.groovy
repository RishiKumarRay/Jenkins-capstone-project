pipeline{
    agent any
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
               cleanws()
           }
       }
      stage('Build')
      {
          steps
          {   echo "Building"
              sh 'mvn clean install'
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
}
