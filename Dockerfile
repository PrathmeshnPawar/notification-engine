# Stage 1: Build the entire project
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the parent and all module poms to cache dependencies
COPY pom.xml .
COPY common/pom.xml common/
COPY producer/pom.xml producer/
COPY worker/pom.xml worker/

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code and build all modules
COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Accept an argument to determine which service to package
ARG MODULE_NAME=producer
COPY --from=build /app/${MODULE_NAME}/target/${MODULE_NAME}-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]