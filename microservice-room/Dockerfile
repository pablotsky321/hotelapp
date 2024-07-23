FROM sapmachine:22-jdk-headless-ubuntu
COPY target/*.jar microservice-room.jar
EXPOSE 8091
CMD ["java","-jar","microservice-room.jar"]