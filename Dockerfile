# Use an OpenJDK base image (adjust version if needed)
FROM openjdk:17-jdk-slim

# Create a volume to store temporary files
VOLUME /tmp
# Grant execute permissions to Gradle
# Build argument for the JAR file location
ARG JAR_FILE=build/libs/ludito-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Expose port 8080 (or your application's port)
EXPOSE 8080

# Define the entry point to run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
