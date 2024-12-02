# Step 1: Build the app using Maven and JDK 17
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .

# Download the dependencies (to cache dependencies for faster rebuilds)
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application (build the JAR file)
RUN mvn clean package -DskipTests

# Use official OpenJDK image from Docker Hub as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the target directory to the container
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Define the command to run the app using Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
