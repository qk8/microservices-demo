FROM openjdk:17-alpine
WORKDIR /app
COPY target/discovery-service-0.0.1-SNAPSHOT.jar ./discovery-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "./discovery-service-0.0.1-SNAPSHOT.jar" ]