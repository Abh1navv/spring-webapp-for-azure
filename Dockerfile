FROM azul/zulu-openjdk-alpine:8
VOLUME /tmp
EXPOSE 8081
COPY target/spring-webapp-for-azure-*.*.*-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar /app.jar