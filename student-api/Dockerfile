# STAGE 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Copy the entire repository into the container
COPY . .

# Search for the pom.xml and build it wherever it is found
RUN mvn -f $(find . -name pom.xml | head -n 1) clean package -DskipTests

# STAGE 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR file from the build stage using a recursive search
COPY --from=build /app/**/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]