blog-service-api

This blog-service-api application is developed using springboot framework. It facilitates two REST services (GET & POST).

POST: /posts

Description: It saves the posts in H2 DB for the user id passed 
Mapping: http://localhost:8080/posts 
Body: "String in JSON format" 
Security: BASIC AUTHENTICATION

GET: /posts, /users

Description: It returns the users/posts from the end point
Mapping: http://localhost:8080/users, http://localhost:8080/posts
Security: BASIC AUTHENTICATION

This application is created using Spring Boot. Test cases for controller and data layers have been added. The dependencies required for this application were added in the pom.xml file. And the postman collections is present in the src/main/resources folder, which can be used for testing.

To run this application:

1. mvn clean install
2. import the project and use the postman collection to hit get and post endpoints

