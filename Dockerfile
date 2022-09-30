FROM maven:3.8.3-openjdk-17
COPY target/profile_backend.jar app/profile_backend.jar
ENTRYPOINT java -jar app/profile_backend.jar

