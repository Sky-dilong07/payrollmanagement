# Use official lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies first (for caching)
RUN ./mvnw dependency:go-offline

# Copy all project files
COPY . .

# Build the Spring Boot JAR
RUN ./mvnw clean package -DskipTests

# Expose Render’s port
EXPOSE 8080

# Run the built JAR file
CMD ["java", "-jar", "target/payrollmanagement-0.0.1-SNAPSHOT.jar"]
