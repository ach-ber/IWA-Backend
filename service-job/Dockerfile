FROM amazoncorretto:17-alpine
VOLUME /tmp
EXPOSE 8301
COPY target/service-job-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]