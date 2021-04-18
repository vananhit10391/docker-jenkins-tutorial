FROM openjdk:16
#ADD target/*.jar docker-jenkins-maven.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-jenkins-maven.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "docker-jenkins-maven.jar"]