# Step 1: Use a Java 20 "Base" Image
FROM openjdk:20-jdk-slim

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the JAR file from your target folder into the container
COPY target/student-api-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]