# Stage 1: Build the application with Maven
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory to the project folder containing pom.xml
WORKDIR /app/serviceBookingSystem

# Copy the pom.xml from the serviceBookingSystem directory to the container's working directory
COPY serviceBookingSystem/pom.xml .

# Download dependencies to cache them in a separate layer
RUN mvn dependency:go-offline -B

# Copy the entire project directory to the container's working directory
COPY serviceBookingSystem/ .

# Run Maven build with debug output
RUN mvn clean package -DskipTests -X
