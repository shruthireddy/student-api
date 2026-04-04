# STAGE 1: Build
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# This 'COPY . .' pulls everything from your Root Directory
COPY . .

# We use a trick to find the pom.xml regardless of where it is
RUN mvn -f $(find . -name pom.xml | head -n 1) clean package -DskipTests

# STAGE 2: Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR from wherever Maven just built it
COPY --from=build /app/**/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]