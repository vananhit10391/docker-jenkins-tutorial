# DOCKER JENKINS TUTORIAL
### 1. Build CI/CD pipeline for microservice
* Spring framework
* Mysql
* Jenkin
* Docker
* CI/CD pipeline
* Ngrok
* Setting GitHub hook trigger for GITScm polling
### 2. Build CI/CD pipeline with AWS
### 3. List module:
* docker-jenkins-maven module (Example about CI/CD pipeline with github, jenkin, docker hub for maven)
* docker-jenkins-gradle module (Example about CI/CD pipeline with github, jenkin, docker hub for gradle)
* docker-spring-mysql module (Example about build spring, mysql on docker)
# 1. BUILD CI/CD PIPEPLINE FOR MICROSERVICE
## 1.1. Clear cache docker
* Stop and remove all process on docker
  - docker stop $(docker ps -a -q)
  - docker rm $(docker ps -a -q)
* Remove all image on docker
  - docker rmi $(docker images -a -q)
* Clean cache
  - docker builder prune
* Clean network
  - docker network prune
## 1.2. Check out source code
* Checkout source code
* Check all file and change content:
  - "anhtruong10391" -> {your-docker-hub-id}
## 1.3. Setting, build and run docker images
### Option 1: Build and run docker images by command
    - At each module, change content Dockerfile:
      Example: "ARG JAR_FILE=build/libs/*.jar" -> "ARG JAR_FILE=docker-jenkins-gradle/build/libs/*.jar" 
    - At employee-mysql/Dockerfile, change content:
      "COPY ./init.sql /docker-entrypoint-initdb.d/init.sql" -> "COPY ./employee-mysql/init.sql /docker-entrypoint-initdb.d/init.sql"
    - Build source
      $ mvn -f docker-jenkins-maven/pom.xml clean install
      $ gradle clean build --build-file docker-jenkins-gradle/build.gradle
      $ gradle clean build --build-file docker-spring-mysql/build.gradle
    - Build images
      $ docker image build -f docker-jenkins-maven/Dockerfile -t anhtruong10391/docker-jenkins-maven .
      $ docker image build -f docker-jenkins-gradle/Dockerfile -t anhtruong10391/docker-jenkins-gradle .
      $ docker image build -f employee-mysql/Dockerfile -t anhtruong10391/employee-mysql .
      $ docker image build -f docker-spring-mysql/Dockerfile -t anhtruong10391/employee-service .
    - Build and run mysql image
      > Create new network
        $ docker network create employee-mysql
      > Run
        $ docker container run --name mysqldb --network employee-mysql -d anhtruong10391/employee-mysql
      > Check connection
        $ docker container exec -it mysqldb bash
        $ mysql -uroot -proot
      > Run containers
        $ docker container run --name docker-jenkins-maven-container -p 9000:9000 -d anhtruong10391/docker-jenkins-maven
        $ docker container run --name docker-jenkins-gradle-container -p 9001:9001 -d anhtruong10391/docker-jenkins-gradle
        $ docker container run --network employee-mysql --name employee-service-container --link mysqldb:anhtruong10391/employee-mysql -p 9002:9002 -d anhtruong10391/employee-service
      > Check log
        $ docker container logs -t mysqldb
        $ docker container logs -t docker-jenkins-maven-container
        $ docker container logs -t docker-jenkins-gradle-container 
        $ docker container logs -t employee-service-container
### Option 2: Build and run docker images by docker compose
* Build source
  - mvn -f docker-jenkins-maven/pom.xml clean install
  - gradle clean build --build-file docker-jenkins-gradle/build.gradle
  - gradle clean build --build-file docker-spring-mysql/build.gradle
* Build and run with docker compose
  - docker compose up
## 1.4. Testing
* curl -H "Content-Type: application/json" -XGET localhost:9000/home/
* curl -H "Content-Type: application/json" -XGET localhost:9001/home/
* curl -H "Content-Type: application/json" -XGET localhost:9002/employee/
## 1.5. Build CI/CD pipeline with jenkins (auto build)
### 1.5.1. Config job jenkin (run with docker compose)
* Refer scrip config jenkins at <jenkin_scrip.txt> file
### 1.5.2. Setting GitHub hook trigger for GITScm polling (auto build source when have event push source code on git)
* Install ngrok
* Use ngrok to expose jenkins server to the internet
* Configure GITHUB Webhook
### 1.5.3 Check
* Push source code into git
* Jenkin will auto build and run this application

