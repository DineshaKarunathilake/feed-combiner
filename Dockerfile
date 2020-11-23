FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILE=target/feedcombiner-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
CMD java -jar app.jar
