# STAGE 1: Build
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy everything from the Root Directory
COPY . .

# Force Maven to find the pom.xml even if it's nested
RUN mvn clean package -DskipTests -B

# STAGE 2: Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the built JAR using a wildcard to avoid naming issues
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]