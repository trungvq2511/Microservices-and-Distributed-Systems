FROM openjdk:17

EXPOSE 8083

ADD ./target/apigateway-1.0-SNAPSHOT.jar apigateway-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "apigateway-1.0-SNAPSHOT.jar"]
