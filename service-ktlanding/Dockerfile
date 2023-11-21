FROM amazoncorretto:17-alpine
VOLUME /tmp
EXPOSE 8305
COPY target/service-ktlanding-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]