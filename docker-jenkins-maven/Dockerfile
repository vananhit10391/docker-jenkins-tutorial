FROM openjdk:16
#WORKDIR /docker-jenkins-maven
ADD docker-jenkins-maven/target/docker-jenkins-maven.jar docker-jenkins-maven/docker-jenkins-maven.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/docker-jenkins-maven/docker-jenkins-maven.jar"]