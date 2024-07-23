FROM sapmachine:22-jdk-headless-ubuntu
RUN apt-get update && apt-get install -y curl
COPY target/*.jar config-server.jar
EXPOSE 8888
CMD ["java","-jar","config-server.jar"]