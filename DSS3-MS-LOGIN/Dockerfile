FROM openjdk:8-jdk-alpine

ADD target/login.jar app.jar

EXPOSE 9003

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
