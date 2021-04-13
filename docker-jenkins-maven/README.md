# docker-jenkins-maven
  Example about CI/CD pipeline with github, jenkin, docker hub for maven
  CI/CD pipeline include:
   1. Github
   2. Jenkin
   3. Docker Hub
## Step by step
1. Install and run docker, jenkin
2. Git
   - Checkout source code
   - At docker-compose.yml file:
     Update content "image: anhtruong10391/docker-jenkins-maven" -> "image: ${your_docker_hub_id}/docker-jenkins-maven"
   - Commit into your repository github
3. Jenkin
   - Create new pipeline job with script at jenkin_scrip.txt
   - Run pipeline job to test result at repository of docker hub (exited ${your_docker_hub_id}/docker-jenkins-maven repository on your docker hub)
4. Testing
   - Pull and run image from repository docker hub
   - Check API with command:
     curl -H "Content-Type: application/json" -XGET localhost:8081/home/
## Keyword refer
1. CLI jenkin
2. CLI docker compose
3. CLI docker
## Others
