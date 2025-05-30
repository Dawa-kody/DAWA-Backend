FROM openjdk:17-slim

COPY ./build/libs/dawa-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar" ]