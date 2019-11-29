# Distributed Systems Part 2 - RESTful User Account API Service

A RESTful API for a User Account web service which uses the utility methods provided by the [gRPC Password Service](https://github.com/SerjiVutinss/gRPC-password-service-DS2019).

This application is based on my own Swagger API design which can be found [here](https://app.swaggerhub.com/apis/SerjiVutinssGit/Users/1).

## Instructions

### Prerequisites

This project relies on Part 1 - [gRPC Password Service](https://github.com/SerjiVutinss/gRPC-password-service-DS2019).  

Ensure that the gRPC Password Service is running locally - by default this runs on localhost:8080.

1. Clone this repo: `git clone https://github.com/SerjiVutinss/ds-restful-account-service`
2. Install Maven dependencies: `mvn install`
3. Compile using Maven: `mvn compile `
4. Package using Maven: `mvn package`
5. Run the server using the provided config: `java -jar restful-account-service-1.0-SNAPSHOT.jar server rest-account-service.yaml`
6. Check that the REST service is running by testing the endpoint `localhost:8081/users` from your browser or Postman, Fiddler, etc

## Design Notes

### Seeded Database

The database is automatically seeded with 5 users upon running the application.  These users should be returned by sending a GET request to  `localhost:8081/users`.

All of the seeded Users have been assigned the same password for ease of testing: `Hello123!`.

### Login

* The login endpoint is located in a separate resource (LoginResource) to the User resources.  As such, the endpoint used to login is `localhost:8081/login`.

* The login endpoint expects a POST request containing a User's email address and password in the body.  This is a design choice I made but I could have also used username/password or userId/password.  I decided on email/password because I reasoned that email addresses should be unique within the system.

* When a valid request has been sent and authenticated against this endpoint, the details of the logged in user are returned in the response.

* If authentication fails, a `401` response is sent.

### Users

* Two different User models have been used - one model for responses and another for POST/PUT requests.  This was done to facilitate the difference in data expected in POST/PUT requests and GET requests, e.g. a User's password is not returned in a GET request (nor is it stored within the system) but a password is expected in both POST and PUT requests.

* Although a `userId` can be sent as part of a PUT request, this value is never used because the route parameter should be the value used when following REST best practices.  This can be shown in testing by sending a request to `localhost:8081/users/1` with a `userId` of 2 present in the body.  The resource with `userId` 1 in the system will be updated while the resource with ID 2 will remain unchanged.

### Error Handling

* Should a badly formed request be sent to any endpoint, an error message is returned detailing the error, e.g. not a valid email address.

### Dependency Injection

* I have used the [Google Dagger](https://github.com/google/dagger) framework to provide dependency injection of certain services to the resources and other services.

* While DI is not strictly necessary, it would allow for easier testing should this project be expanded upon at a later time.
