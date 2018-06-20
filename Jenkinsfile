

node {

    stage('Initialize')
    {
        def dockerHome = tool 'MyDocker'
        def mavenHome  = tool 'MyMaven'
        def miniShiftHome='/usr/local';
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${miniShiftHome}/bin:${env.PATH}"
    }

    stage('Checkout') 
    {
        checkout scm
    }


   
      stage('Build') 
           {
            sh 'uname -a'
            //sh 'mvn -B -DskipTests clean package'  
          }

        stage('Test') 
        {
            //sh 'mvn test'
            sh 'ifconfig' 
        }

        stage('Deliver') 
          {
                sh  'export PATH="/Users/pjadda/.minishift/cache/oc/v3.9.0/darwin:$PATH" &&  oc login -u system -p admin https://192.168.64.3:8443 --insecure-skip-tls-verify  && sudo docker ps'
         
              //sh 'bash ./jenkins/deliver.sh'
        }
}

def imagePrune(containerName)
{
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}









/* *******************************************************************************************************        */


/*
def dockerHome="/usr/local/"
def mavenHome="/usr/local/"
pipeline 
{
      agent 
        { 
            docker
             {
               image 'maven:3-alpine'
               args '-v /root/.m2:/root/.m2'
            }  
        } 

     environment 
          { 
              PATH = "${dockerHome}/bin:${mavenHome}/bin:${PATH}"
          }
     
     stages 
     {
         stage('Build') 
           {
               
      
              steps 
              {
                //sh 'mvn -B -DskipTests clean package'
                   sh 'uname -a'
                   sh 'mvn --version'
                   //sh 'sudo dseditgroup -o edit -a jenkins -t user docker'
                   sh 'sudo docker ps'
              }
          }

        stage('Test') 
        {
            steps {
                //sh 'mvn test'
                sh 'ifconfig'
            }
            post {
                always {
                    //junit 'target/surefire-reports/*.xml'
                    sh 'uname -a'
                     //sh 'apk add docker'
                     //sh 'service docker start'
                }
            }
        }

        stage('Deliver for development')
        {
                    when {
                        branch 'development'
                    }
                    steps {
                        sh './jenkins/scripts/deliver-for-development.sh'
                        input message: 'Finished using the web site? (Click "Proceed" to continue)'
                    }
        }

        stage('Deploy for production')
        {
            when {
                branch 'production'
            }
            steps {
                sh './jenkins/scripts/deploy-for-production.sh'
                input message: 'Finished using the web site? (Click "Proceed" to continue)'
            }
        }

        stage('Deliver') 
          {

            steps 
            {
                sh 'bash ./jenkins/deliver.sh'
            }
        }
      } //End of stages
     
      
 
}

*/







/* *******************************************************************************************************        */





/*
pipeline 
{ 
 agent any 
  tools
  {
   def dockerHome = tool 'MyDocker'
   def mavenHome  = tool 'MyMaven'
  }
    stages 
          {
               stage('Initialize')
               {
                    steps 
                        {
                          //def dockerHome = '${params.MyDocker}'
                          //def mavenHome  = '${params.MyMaven}'
                          env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                        }

               }

              stage('Build') 
              {
                   steps 
                   {
                     //sh 'mvn -B -DskipTests clean package'
                     sh 'uname -a'
                        sh 'mvn'
                        sh 'docker ps'
                   }
               }

             stage('Test') 
             {
                 steps {
                     //sh 'mvn test'
                     sh 'ifconfig'
                 }
                 post {
                     always {
                         //junit 'target/surefire-reports/*.xml'
                         sh 'uname -a'
                          //sh 'apk add docker'
                          //sh 'service docker start'
                     }
                 }
             }

             stage('Deliver for development')
             {
                         when {
                             branch 'development'
                         }
                         steps {
                             sh './jenkins/scripts/deliver-for-development.sh'
                             input message: 'Finished using the web site? (Click "Proceed" to continue)'
                         }
             }

             stage('Deploy for production')
             {
                 when {
                     branch 'production'
                 }
                 steps {
                     sh './jenkins/scripts/deploy-for-production.sh'
                     input message: 'Finished using the web site? (Click "Proceed" to continue)'
                 }
             }

             stage('Deliver') 
               {

                 steps 
                 {
                     sh 'bash ./jenkins/deliver.sh'
                 }
             }
           } //End of stages
}
*/




/* *******************************************************************************************************        */



