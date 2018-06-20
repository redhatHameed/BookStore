
node
{
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
                //sh  'oc login -u system -p admin https://192.168.64.3:8443 --insecure-skip-tls-verify'
                sh 'minishift start'
              //sh 'bash ./jenkins/deliver.sh'
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

