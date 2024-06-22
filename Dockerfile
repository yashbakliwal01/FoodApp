FROM openjdk:11

EXPOSE 8080

ADD target/foodapp-0.0.1-SNAPSHOT.jar /app/foodapp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/foodapp-0.0.1-SNAPSHOT.jar"]
