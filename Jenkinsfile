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
            sh 'whoami'
            sh 'mvn -B -DskipTests clean package'  
          }

        stage('Test') 
        {
            //sh 'mvn test'
            //sh 'ifconfig'
            sh 'uname -a'
        }

        stage('Deliver') 
          {
            sh 'bash ./jenkins/deliver.sh'
            sh 'sudo docker build -f Dockerfile -t 172.30.1.1:5000/cicd-project/bookstore:latest .'
            sh 'sudo docker tag bookstore 172.30.1.1:5000/cicd-project/bookstore:latest'

            //docker login -u duppoc -p Bcmc@2018
            sh 'sudo /usr/local/bin/oc login https://192.168.64.7:8443 --token=kEXVPDLicC7G8k2EZoIjDsd1U2aWK5OhknZRPjlr4is  --insecure-skip-tls-verify'

            //docker push duppoc/bookstore:latest
            sh 'sudo docker push 172.30.1.1:5000/cicd-project/bookstore:latest'
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

