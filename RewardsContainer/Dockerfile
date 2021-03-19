FROM openjdk:8-jdk-alpine as build 
ARG JAR_FILE=target/*.jar 
COPY Project08-Service-Rewards-1.0.0.jar Service-Rewards-1.0.0.jar 
ENTRYPOINT ["java","-jar","/Service-Rewards-1.0.0.jar"]
