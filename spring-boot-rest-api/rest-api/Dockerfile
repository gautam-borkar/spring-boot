# Build jar file
FROM maven:3.9.11-ibm-semeru-21-noble AS build
WORKDIR /home/app
COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/gborkar/rest_api/RestApiApplication.java /home/app/src/main/java/com/gborkar/rest_api/RestApiApplication.java
RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# Build docker image
FROM ibm-semeru-runtimes:open-jdk-25.0.0_36-jre-noble
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]