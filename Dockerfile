# STAGE 1: Build using Maven
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy everything from the root
COPY . .

# Build the JAR (using 'mvn' because Render's image has it pre-installed)
RUN mvn clean package -DskipTests

# STAGE 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR from the target folder produced in Stage 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]