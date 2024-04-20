
# docker build -t api-image-sample .

# docker run -it -p 80:80 --name=api-container api-image-sample


FROM openjdk:17

ARG FILE_JAR=target/*.jar

ADD ${FILE_JAR} api-service.jar

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 80

