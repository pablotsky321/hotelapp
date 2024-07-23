FROM sapmachine:22-jdk-headless-ubuntu
COPY target/*.jar gateway.jar
EXPOSE 8080
CMD ["java","-jar","gateway.jar"]