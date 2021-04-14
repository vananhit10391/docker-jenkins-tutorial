FROM openjdk:16
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} docker-spring-mysql.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","docker-spring-mysql.jar"]