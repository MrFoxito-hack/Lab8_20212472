FROM openjdk:25-ea-17-jdk
VOLUME /tmp
EXPOSE 8080
ADD ./target/Lab8_20212472-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar" ]