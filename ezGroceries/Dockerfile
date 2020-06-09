#FROM maven:3.6.1-jdk-11-slim AS builder
#COPY . /source
#WORKDIR /source
#RUN mvn -B package -DskipTests
#FROM openjdk:8-jdk-alpine
#COPY --from=builder /source/target/*.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar", "--spring.profiles.active=hsqldb"]
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
