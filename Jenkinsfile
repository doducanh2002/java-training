pipeline {
    agent any

    environment {
        GIT_CREDENTIALS = 'git-credentials-id'
        GHCR_CREDENTIALS = 'ghcr-credentials-id'
        SSH_CREDENTIALS = 'ssh-credentials-id'
        GIT_REPO = ''
        SERVER = ''
        SERVER_USER = ''
        CONTAINER_NAME = ''
        GHCR_REPO = ''
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Pull from GitHub') {
            steps {
                script {
                    git url: "${env.GIT_REPO}", branch: 'develop', credentialsId: "${env.GIT_CREDENTIALS}"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.GHCR_REPO}:${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Push to GHCR') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${env.GHCR_CREDENTIALS}", passwordVariable: 'DOCKER_REGISTRY_PWD', usernameVariable: 'DOCKER_REGISTRY_USER')]) {
                          sh """
                          docker login ghcr.io -u ${DOCKER_REGISTRY_USER} -p ${DOCKER_REGISTRY_PWD}
                          docker push "${env.GHCR_REPO}:${env.BUILD_NUMBER}"
                          """
                    }
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                script {
                    sshagent(["${env.SSH_CREDENTIALS}"]) {
                        sh """
                        ssh -o StrictHostKeyChecking=no ${env.SERVER_USER}@${env.SERVER} <<EOF
                        cd docker-compose
                        export TAG=${env.BUILD_NUMBER}
                        docker rm -f ${env.CONTAINER_NAME}
                        docker images ${env.GHCR_REPO} --format '{{.ID}}' | xargs docker rmi -f || true
                        docker compose -f nginx.yml -f redis.yml -f mysql.yml -f ams.yml up -d
EOF
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}