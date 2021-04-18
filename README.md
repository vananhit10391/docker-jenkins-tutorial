# docker-jenkins-tutorial
  * Technical points:
    * CI/CD pipeline
    * Build multip container docker with docker compose
    * Setting trigger build between jenkin and github (GitHub hook trigger for GITScm polling)
    * Build CI/CD pipeline for microservice
    * Build CI/CD pipeline with AWS
  * List module:
    - docker-jenkins-maven module (Example about CI/CD pipeline with github, jenkin, docker hub for maven)
    - docker-jenkins-gradle module (Example about CI/CD pipeline with github, jenkin, docker hub for gradle)
    - docker-spring-mysql module (Example about build spring, mysql on docker)
# Step by step build and run
1. Install and run docker
2. Git
   - Checkout source code
   - At docker-compose.yml file:
     Update content "image: anhtruong10391/..." -> "image: ${your_docker_hub_id}/..."
3. Setting, build and run docker images
   * Option 1: build and run docker images by command
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
          - $ docker container run --name docker-jenkins-maven-container -d  anhtruong10391/docker-jenkins-maven
          - $ docker container run --name docker-jenkins-gradle-container -d anhtruong10391/docker-jenkins-gradle
          - $ docker container run --network employee-mysql --name employee-service-container --link mysqldb:anhtruong10391/mysql-server -p 9002:9002 -d anhtruong10391/employee-service
       * Check log
          - $ docker container logs -t mysqldb
          - $ docker container logs -t docker-jenkins-maven-container
          - $ docker container logs -t docker-jenkins-gradle-container 
          - $ docker container logs -t employee-service-container
   * Option 2: Build and run docker images by docker compose
     - $ docker compose up
4. Testing
   * curl -H "Content-Type: application/json" -XGET localhost:9000/home/
   * curl -H "Content-Type: application/json" -XGET localhost:9001/home/
   * curl -H "Content-Type: application/json" -XGET localhost:9002/employee/
## Keyword refer
1. CLI docker compose
2. CLI docker
3. CLI mysql

