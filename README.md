# mobile WS

- create a spring project and add spring web dependency.

after add ``spring-boot-starter-data-jpa`` & ``mysql-connector-java`` dependencies, enable our application to be able to communicate with the database inside *application.properties file*. 

Inside *application.properties* file can add different properties that my code can read from, *like database connection details*

## add spring security and Json WebToken dependencies in project
**after add security dependency if how create a request not Authorized will not success, API will Return 401 Unauthorized status response, in web browser will redirect the request to default login page, this page is from spring security dependency**

## add WebSecurity class to activate Authorization,

if the request is not allowed to access without authentication, server response error status 403 forbidden:  

```
403 Forbidden message:
	The HTTP 403 Forbidden client error status response code indicates that the server understands the request but refuses to authorize it. 
	This status is similar to 401 , but in this case, re-authenticating will make no difference
```

