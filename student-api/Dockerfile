# Step 1: Use a stable Java 21 image
FROM eclipse-temurin:21-jdk-jammy

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from your target folder
# Note: Render uses the Root Directory setting, so 'target/...' is correct
# if your Root Directory is set to 'student-api'
COPY target/student-api-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]