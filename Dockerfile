FROM openjdk:11
EXPOSE 8080
ADD target/ea-project-g3.jar ea-project-g3.jar
ENTRYPOINT ["java","-jar","/ea-project-g3.jar"]