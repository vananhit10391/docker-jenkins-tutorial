FROM openjdk:16
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} docker-jenkins-gradle.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","docker-jenkins-gradle.jar"]