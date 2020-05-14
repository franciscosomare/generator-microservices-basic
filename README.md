# generator-microservices

## How to use?

```
> npm install -g yo
> npm install -g generator-microservices
> yo microservices
```
## Local Development Setup

```
> cd generator-microservices
> npm install 
> npm link
> yo microservices
```

## Note

```
If you have some errors to run the commands above try:
1- Remove:
  - node_modules
  - .yo-repository
  - package-lock.json
2- run:
   > npm install -g generator-microservices
   > cd /generator-microservices
   > npm link
   > yo microservices
```

## Features

1. Microservice

    * SpringBoot REST API with jar type packaging
    * CORS configuration
    * Swagger UI Integration
    * Spring Data JPA integration with option to select databases like MySQL, Postgresql

### Generate REST API with CRUD operations
Run the following command from within the generated project folder. 

`myservice> yo microservices:controller Cuenta --base-path /api/cuenta`

This will generate:
* JPA entity
* Spring Data JPA Repository
* Service
* Spring MVC REST Controller with CRUD operations
* Unit and Integration Tests for REST Controller
* Flyway or Liquibase migration to create table

### Generate Entity
Run the following command from within the generated project folder. 

`myservice> yo microservices:entity Saldo`

This will generate:
* JPA entity
* Spring Data JPA Repository
* Service
