FROM eclipse-temurin:17-jdk
COPY forum-0.0.1-SNAPSHOT.jar app.jar


#FROM maven:3.8.4-openjdk-17-slim as builder
#WORKDIR /app
#
#COPY pom.xml .
#COPY src ./src
#
#RUN mvn clean package -DskipTests
#
#FROM eclipse-temurin:17-jdk
#
#COPY --from=builder /app/target/forum-0.0.1-SNAPSHOT.jar app.jar


