FROM openjdk:17
VOLUME /tmp
EXPOSE 8302
COPY target/service-recruiter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]