FROM sapmachine:22-jdk-headless-ubuntu
COPY target/*.jar microservice-user.jar
EXPOSE 8091
CMD ["java","-jar","microservice-user.jar"]