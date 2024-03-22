# Scope of work
- [ ] Implement a load test to check how the system performs with 100+ concurrent users, which are sending HTTP requests to the backend.
- [ ] Show how the test results need to be interpreted.
- [ ] Show which changes need to be done to the system to improve the results of the load test (optional).
- [x] Add enpoints for managing (at least create, read and delete, if there's enough time add update) Blogs, Comments and Authors.
- [x] Refactor the Text-Validator service to be generic.
- [x] Refactor boundry to be reactive.
    - Making the boundry resources of the REST API reactive is out of scope.

Take a look at JMeter [here](./JMeter.md).

# Load tests and reports
The load tests are located:
- for [100 users](./load-tests/test-plans/Quarkus-API-Load-Test-100-Users.jmx)
- for [1000 users](./load-tests/test-plans/Quarkus-API-Load-Test-1000-Users.jmx)

The reporst are located:
- for [100 users](./load-tests/reports/Report-100-Users-Prod/index.html)
- for [1000 users](./load-tests/reports/Report-1000-Users-Prod/index.html)

Possible solutions:
- Add paging so only a limited amount of blogs can be fetched per request.

# How to run the services
To run the production services, execute the following command:
```bash
docker-compose --file docker-compose.prod.yaml up -d
```

The Swagger UI of the Backend Service is available under http://localhost:9090/q/swagger-ui

To verify the functionality of the services create a Blog by using the POST endpoint.
The Blogs can be retrieved using the GET endpoint.
If the Blog content contains one of the following words `bad`, `invalid` or `sucks` the Blog's valid property will be set to `false`
otherwise it will be set to `true`.

# quarkus-simple-blog

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-simple-blog-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - MySQL ([guide](https://quarkus.io/guides/datasource)): Connect to the MySQL database via JDBC
