FROM maven:3.9.3-eclipse-temurin-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /usr/src/app/target/*.jar /usr/app/npvet-api-1.0.0-SNAPSHOT.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","/usr/app/npvet-api-1.0.0-SNAPSHOT.jar"]
