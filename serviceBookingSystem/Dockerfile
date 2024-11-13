FROM maven:3.8.5-openjdk-17 AS build
COPY . .

# Build the application and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Copy the jar file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your application runs on (optional, replace with actual port if needed)
EXPOSE 8080

# Run the application 
ENTRYPOINT ["java", "-jar", "app.jar"]