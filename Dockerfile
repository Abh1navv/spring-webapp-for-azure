FROM azul/zulu-openjdk-alpine:8
VOLUME /tmp
EXPOSE 8080
COPY target/spring-boot-starter-parent-2.4.5.jar app.jar
ENTRYPOINT java -jar /app.jar