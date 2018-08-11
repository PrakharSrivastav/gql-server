# COMPILE
FROM gradle:4.9.0-jdk8-alpine as compile
COPY . /home/source/java
WORKDIR /home/source/java
USER root
RUN chown -R gradle /home/source/java
USER gradle
RUN gradle clean build

# RUNTIME
FROM gradle:4.9.0-jre-alpine
WORKDIR /home/application/java
COPY --from="compile" "/home/source/java/build/libs/gql-server-1.0-SNAPSHOT.jar" .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/home/application/java/gql-server-1.0-SNAPSHOT.jar"]