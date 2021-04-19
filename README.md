# docker-jenkins-tutorial (Tutorial about docker, jenkins with spring).
 1. Build CI/CD pipeline for microservice
 2. Setting GitHub hook trigger for GITScm polling
 3. Build CI/CD pipeline with AWS
  * List module:
    - docker-jenkins-maven module (Example about CI/CD pipeline with github, jenkin, docker hub for maven)
    - docker-jenkins-gradle module (Example about CI/CD pipeline with github, jenkin, docker hub for gradle)
    - docker-spring-mysql module (Example about build spring, mysql on docker)
# 1. Build CI/CD pipeline for microservice
## 1.1. Clear cache docker
        * Stop and remove all process on docker
          $ docker stop $(docker ps -a -q)
          $ docker rm $(docker ps -a -q)
        * Remove all image on docker
          $ docker rmi $(docker images -a -q)
        * Clean cache
          $ docker builder prune
        * Clean network
          $ docker network prune
## 1.2. Check out source code
        Checkout source code
        At docker-compose.yml file:
          - Update content "image: anhtruong10391/..." -> "image: ${your_docker_hub_id}/..."
## 1.3. Setting, build and run docker images
        * Option 1: build and run docker images by command
          * At each module, change content Dockerfile
            - Example:
              * "ARG JAR_FILE=build/libs/*.jar" -> "ARG JAR_FILE=docker-jenkins-gradle/build/libs/*.jar" 
          * Build source
            - $ mvn -f docker-jenkins-maven/pom.xml clean install
            - $ gradle clean build --build-file docker-jenkins-gradle/build.gradle
            - $ gradle clean build --build-file docker-spring-mysql/build.gradle
          * Build images
            - $ docker image build -f docker-jenkins-maven/Dockerfile -t anhtruong10391/docker-jenkins-maven .
            - $ docker image build -f docker-jenkins-gradle/Dockerfile -t anhtruong10391/docker-jenkins-gradle .
            - $ docker image build -f docker-mysql/Dockerfile -t anhtruong10391/mysql-server .
            - $ docker image build -f docker-spring-mysql/Dockerfile -t anhtruong10391/employee-service .
          * Build and run mysql image
            * Create new network
               - $ docker network create employee-mysql
            * Run
               - $ docker container run --name mysqldb --network employee-mysql -d anhtruong10391/mysql-server
            * Check connection
               - $ docker container exec -it mysqldb bash
               - $ mysql -uroot -proot
            * Run containers
               - $ docker container run --name docker-jenkins-maven-container -p 9000:9000 -d anhtruong10391/docker-jenkins-maven
               - $ docker container run --name docker-jenkins-gradle-container -p 9001:9001 -d anhtruong10391/docker-jenkins-gradle
               - $ docker container run --network employee-mysql --name employee-service-container --link mysqldb:anhtruong10391/mysql-server -p 9002:9002 -d anhtruong10391/employee-service
            * Check log
               - $ docker container logs -t mysqldb
               - $ docker container logs -t docker-jenkins-maven-container
               - $ docker container logs -t docker-jenkins-gradle-container 
               - $ docker container logs -t employee-service-container
        * Option 2: Build and run docker images by docker compose
          * Build source
            - $ mvn -f docker-jenkins-maven/pom.xml clean install
            - $ gradle clean build --build-file docker-jenkins-gradle/build.gradle
            - $ gradle clean build --build-file docker-spring-mysql/build.gradle
          * Run
            - $ docker compose up
## 1.4. Testing
        * curl -H "Content-Type: application/json" -XGET localhost:9000/home/
        * curl -H "Content-Type: application/json" -XGET localhost:9001/home/
        * curl -H "Content-Type: application/json" -XGET localhost:9002/employee/
## 1.5 Setting and run jenkin
### 1.5.1 Config job jenkin
          * Refer scrip config jenkins at <jenkin_scrip.txt> file
### 1.5.2 Setting GitHub hook trigger for GITScm polling
          * Use ngrok to expose jenkins server to the internet
          * Configure GITHUB Webhook
