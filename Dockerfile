FROM eclipse-temurin:17

LABEL maintainer="akashhm@gmail.com"

WORKDIR /app

COPY target/Rest-Demo.jar /app/Rest-Demo-Docker.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","Rest-Demo-Docker.jar"]

