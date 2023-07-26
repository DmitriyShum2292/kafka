FROM maven:3.8-openjdk-17 as builder
FROM openjdk:17

COPY target/kafka-0.0.1-SNAPSHOT.jar kafka-app.jar
ENTRYPOINT ["java","-jar","kafka-app.jar"]