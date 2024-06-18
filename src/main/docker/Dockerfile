FROM alpine/java:21-jdk

COPY /target/*.jar server.jar

ENTRYPOINT ["java", "-jar", "server.jar"]

EXPOSE 80
