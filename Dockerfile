FROM java:8
ADD /*.jar app.jar
EXPOSE 8662
ENTRYPOINT ["java","-jar","/app.jar"]