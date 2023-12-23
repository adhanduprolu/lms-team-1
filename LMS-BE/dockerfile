FROM openjdk:17
WORKDIR /app
COPY . .
RUN ./mvnw package
EXPOSE 8080
CMD ["java", "-jar", "target/LMS-0.0.1-SNAPSHOT.jar"]
