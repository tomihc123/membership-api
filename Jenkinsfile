pipeline {
    agent any

    environment {
        IMAGE_NAME = "membership-api"
        IMAGE_TAG = "1.0.${BUILD_NUMBER}"

        NAMESPACE = "membership"
        RELEASE_NAME = "membership-api"
        CHART_PATH = "membership-api-chart"
        DEPLOYMENT_NAME = "membership-api-membership-api-chart"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew clean test
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    docker build \
                      -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        stage('Load Image into Minikube') {
            steps {
                sh '''
                    minikube image load \
                      ${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Deploy with Helm') {
            steps {

                withCredentials([
                    usernamePassword(
                        credentialsId: 'membership-db',
                        usernameVariable: 'DB_USER',
                        passwordVariable: 'DB_PASSWORD'
                    )
                ]) {

                    sh '''
                        helm upgrade ${RELEASE_NAME} ${CHART_PATH} \
                          --namespace ${NAMESPACE} \
                          --set image.repository=${IMAGE_NAME} \
                          --set image.tag=${IMAGE_TAG} \
                          --set database.username="${DB_USER}" \
                          --set database.password="${DB_PASSWORD}"
                    '''
                }
            }
        }

        stage('Wait for Rollout') {
            steps {
                sh '''
                    kubectl rollout status \
                      deployment/${DEPLOYMENT_NAME} \
                      -n ${NAMESPACE} \
                      --timeout=120s
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                    echo "Deployed image:"
                    kubectl get deployment ${DEPLOYMENT_NAME} \
                      -n ${NAMESPACE} \
                      -o=jsonpath='{.spec.template.spec.containers[0].image}'

                    echo ""
                    echo "Running pods:"
                    kubectl get pods -n ${NAMESPACE}
                '''
            }
        }
    }

    post {

        success {
            echo "Deployment successful: ${IMAGE_NAME}:${IMAGE_TAG}"
        }

        failure {
            echo "Pipeline failed"
        }
    }
}