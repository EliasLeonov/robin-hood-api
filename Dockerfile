FROM gradle:7.0-jdk11 AS build

COPY . /home/gradle/src/
WORKDIR /home/gradle/src
RUN gradle assemble

FROM openjdk:11

EXPOSE 8080
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/robin-hood-api-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "/app/spring-boot-application.jar"]