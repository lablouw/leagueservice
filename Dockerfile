FROM java:8-jdk-alpine
COPY ./target/league-service-1.0.0-SNAPSHOT.jar /usr/league-service/
WORKDIR /usr/league-service
ENTRYPOINT ["java","-jar","league-service-1.0.0-SNAPSHOT.jar"]