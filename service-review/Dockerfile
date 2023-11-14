FROM openjdk:17
VOLUME /tmp
EXPOSE 8304
COPY target/service-review-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]