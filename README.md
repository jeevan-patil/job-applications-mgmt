# job-applications-mgmt

This is a spring-boot application which uses maven.
To build and run the project, on terminal go to the root location of project where pom.xml resides.
To build application ```mvn clean package```
To run the application ```mvn spring-boot:run```
To test the application ```mvn test```

I've used in-memory MongoDB database to store the offers and job applications.
CommandLineRunner has been used to load the load before application starts.

To understand the APIs, go to following swagger url.
http://localhost:8080/swagger-ui.html