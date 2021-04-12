FROM openjdk:16
ADD target/docker-jenkins-maven.jar docker-jenkins-maven.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "docker-jenkins-maven.jar"]