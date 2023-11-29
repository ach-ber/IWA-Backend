FROM amazoncorretto:17-alpine
VOLUME /tmp
EXPOSE 7000
COPY target/service-user-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]