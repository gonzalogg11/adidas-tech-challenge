# Adidas Tech Challenge

---

## 1. Clone the repository
You can easily clone the repository from this url https://github.com/gonzalogg11/adidas-tech-challenge.git

---

## 2. Run the APP locally
Before dealing with the microservices, firstly you'll need to open your MySQL Workbench program and run the 
**adidas_database.sql** script located in **scripts** folder, then you'll have your database configured for the App.

Now, make sure every microservice is imported with Maven and run the command **mvn clean install** (skipping tests) on 
each one. Then you just have to run all services and enjoy! If you want the almighty oracle which knows it all 
(Oracle ms 😄) to hear your petitions you'll need an auth token.
### Authorization token for oracle: 0F1I1B2O3N5A8C1C3I21345589144
The services are configured with swagger, so you can simply go to http://localhost:{port}/swagger-ui.html and try the
requests or either do the calls to http://localhost:{port}/{path} with Postman. The ports of the services are located in
each application.yml files.

---

## 3. Test the APP
Notification and Oracle ms have JUnit tests that you can simply run with your IDE accessing the service test classes, 
however Subscription ms is covered by Spring Cloud Contracts that are generated with any of these commands: 
- **mvn spring-cloud-contract:generateTests**
- **mvn clean install** (this will also start running the tests, so you can exit the process before the tests start
and run them manually)

The Spring Cloud Contracts are generated in a class named ContractVerifierTests, if you don't see this file then
reload the project with the Maven instruction.

In addition, Spring Cloud Contracts read directly from the database, but they don't modify it thanks to the 
**@Transactional** annotation that we have in our **BaseClass** file. The tests should pass correctly if you created 
your database correctly at the beginning.

---

## 4. Deploy the APP?
If you have an Amazon Web Services cloud environment with a Jenkins server configured there are CI pipelines configured 
in each microservice, so it could be possible to deploy all the services into a Kubernetes cluster and use the App in 
your cloud environment.

---

## Pom Dependencies and libraries
- **spring-boot-starter**, **spring-boot-starter-test** and spring-context-support: Basic Spring Boot stuff.
- **spring-boot-starter-web**: Needed to create the controller API endpoints.
- **spring-boot-starter-data-jpa**: Needed to get/save/update/delete data from database.
- **spring-cloud-starter-netflix-ribbon** and **spring-cloud-starter-netflix-eureka-client**: Needed to connect all 
microservices and make requests between them via http.
- **spring-boot-starter-validation**: Needed to add validation to the requestDTOs and handle validation exceptions.
- **spring-cloud-starter-contract-verifier** and **spring-cloud-starter-contract-stub-runner**: Needed to create and 
generate Spring Cloud Contracts.
- **lombok**: Simplifies the constructors, getters and setters of DTOs with automatic generation.
- **springfox-swagger2** and **springfox-swagger-ui**: Needed for Swagger UI and Swagger documentation.
- **mysql-connector-java**: Needed to connect a Java microservice to a MySQL database.
- **junit-jupiter-engine** and **junit**: Frameworks to create Java Spring Boot unit tests.
- **commons-lang**: Useful library that simplifies checks or operations with Java objects like Strings, Booleans, 
Integers, etc.
- **modelmapper**: Library that simplifies mapping of DTOs and Entities.