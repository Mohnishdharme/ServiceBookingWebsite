# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the entire project
COPY . .

# Debug: List contents after copy
RUN pwd && ls -la

# Change to the correct directory
WORKDIR /app/serviceBookingSystem

# Debug: List contents of the project directory
RUN pwd && ls -la

# Verify pom.xml exists and show its contents
RUN if [ -f "pom.xml" ]; then cat pom.xml; else echo "pom.xml not found"; exit 1; fi

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR file from the build stage
# Adjust the path to match your project structure
COPY --from=build /app/serviceBookingSystem/target/*.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the startup command
ENTRYPOINT ["java", "-jar", "app.jar"]