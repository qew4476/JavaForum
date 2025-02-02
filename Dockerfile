FROM maven:3.8.5-openjdk-17-slim AS build

WORKDIR /app

COPY . .

# use maven to build the project
RUN mvn clean package -DskipTests

#use openjdk(slim) to run the project
FROM openjdk:17-jdk-slim

VOLUME /tmp

# from build stage copy the jar file to the root directory
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
