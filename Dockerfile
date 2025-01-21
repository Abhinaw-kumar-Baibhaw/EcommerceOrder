FROM openjdk:17-jdk-slim AS base
WORKDIR /app
COPY target/FullFledgedOrderPart-0.0.1-SNAPSHOT.jar /app/FullFledgedOrderPart-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/FullFledgedOrderPart-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
