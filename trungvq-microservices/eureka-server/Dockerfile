FROM openjdk:17

EXPOSE 8761

ADD ./target/eureka-server-1.0-SNAPSHOT.jar /eureka-server-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "eureka-server-1.0-SNAPSHOT.jar"]
