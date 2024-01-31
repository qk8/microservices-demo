FROM openjdk:17-alpine
WORKDIR /app
COPY target/gateway-0.0.1-SNAPSHOT.jar ./gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "./gateway-0.0.1-SNAPSHOT.jar" ]