# Use the official OpenJDK 17 image as the base image
FROM openjdk:17
# Set the working directory to /app
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY . .
# Build the application with Maven
RUN ./mvnw package
# Expose the port the app runs on
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "target/LMS-0.0.1-SNAPSHOT.jar"]
