FROM eclipse-temurin:21-jdk-ubi9-minimal

LABEL authors="PlayBeach-api"

RUN #gradlew clean bootJar
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]