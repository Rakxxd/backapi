FROM openjdk:20

EXPOSE 8080

ADD target/backapi.jar backapi.jar

CMD ["java" , "-jar" ,  "backapi.jar"]