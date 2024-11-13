# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download all dependencies. Dependencies will be cached if the pom.xml file is not changed 
RUN mvn dependency:go-offline -B

# Copy the project source
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/serviceBookingSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-jar","app.jar"]