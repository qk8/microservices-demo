FROM openjdk:17-alpine
WORKDIR /app
COPY target/item-service-0.0.1-SNAPSHOT.jar ./item-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m", "-jar", "./item-service-0.0.1-SNAPSHOT.jar" ]
