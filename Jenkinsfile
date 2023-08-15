pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }



        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/mdhaseebpes/socialNetworkAPIWithCucumber.git'
                    sh "mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@regression -Xlint:deprecation"

                }
            }
        }


        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }


        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'output',
                                  reportFiles: 'SocialNetworkAPIReport.html',
                                  reportName: 'HTML Regression Extent Report',
                                  reportTitles: ''])
            }
        }

        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/mdhaseebpes/socialNetworkAPIWithCucumber.git'
                    sh "mvn clean test -Denv=jsonplaceholder -Dcucumber.filter.tags=@sanity -Xlint:deprecation"

                }
            }
        }

        stage('Publish sanity Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'output',
                                  reportFiles: 'SocialNetworkAPIReport.html',
                                  reportName: 'HTML Sanity Extent Report',
                                  reportTitles: ''])
            }
        }

    }
}
