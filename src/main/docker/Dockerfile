FROM alpine/java:21-jdk

COPY /target/*.jar server.jar

ENTRYPOINT ["java", "-jar", "-Dspring.config.location=./configurations/", "server.jar"]

EXPOSE 8443
