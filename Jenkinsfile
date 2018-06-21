def oc='/Users/pjadda/.minishift/cache/oc/v3.9.0/darwin'
def miniShiftHome='/usr/local';

node
{
    stage('Initialize')
    {
        def dockerHome = tool 'MyDocker'
        def mavenHome  = tool 'MyMaven'
        env.PATH = "${oc}:${dockerHome}/bin:${mavenHome}/bin:${miniShiftHome}/bin:${env.PATH}"
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
            sh 'sudo cat /Users/pjadda/.ssh/known_hosts '
            sh 'sudo ssh docker@192.168.64.3 "pwd"'
        }

        stage('Deliver') 
          {
              sh 'bash ./jenkins/deliver.sh'
        }
}

//Not using this
def imagePrune(containerName)
{
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

