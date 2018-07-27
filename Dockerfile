FROM openjdk:8-jdk-alpine

LABEL maintainer=jeevanpaatil@gmail.com

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/job-application-mgmt-1.0.jar

ADD ${JAR_FILE} job-application-mgmt-1.0.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/job-application-mgmt-1.0.jar"]