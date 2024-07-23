FROM sapmachine:22-jdk-headless-ubuntu
COPY target/*.jar microservice-booking.jar
EXPOSE 8092
CMD ["java","-jar","microservice-booking.jar"]