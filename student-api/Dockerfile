# STAGE 1: Build
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# We are at the root of GitHub, so we copy everything
COPY . .

# Step into the folder where the pom.xml actually lives to build it
RUN mvn -f student-api/student-api/pom.xml clean package -DskipTests

# STAGE 2: Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR from the nested target folder
COPY --from=build /app/student-api/student-api/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]