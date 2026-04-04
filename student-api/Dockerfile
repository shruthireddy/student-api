# STAGE 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# STAGE 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR from the 'build' stage
COPY --from=build /app/target/student-api-0.0.1-SNAPSHOT.jar app.jar

# Run the app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]