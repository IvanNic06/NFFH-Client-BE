FROM openjdk:17

COPY target/taskman-0.0.1-SNAPSHOT.jar taskman-0.0.1-SNAPSHOT.jar 

ENTRYPOINT [ "java", "-jar", "taskman-0.0.1-SNAPSHOT.jar" ]
EXPOSE 9702