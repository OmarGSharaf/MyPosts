#  MyPosts

[![Generic badge](https://img.shields.io/badge/version-1.0-<COLOR>.svg)]() [![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/jonashackt/spring-boot-vuejs/blob/master/LICENSE)


MyPosts is a small web application & backend for a mobile application where users can sign in, create public & private posts and see other users public posts.

## Getting Started

Docker is a containerization platform that is used to package MyPosts application and all its dependencies together in the form called a docker container to ensure that MyPosts works seamlessly in any environment. This environment might be a production or staging server. Docker pulls the dependencies needed for the application to run from the cloud and configures them automatically.

### Prerequisites

#### macOS

```
$ brew update
$ brew tap caskroom/cask
$ brew cask install docker
```

#### Linux

```
sudo apt update
sudo apt install docker.io
```

## Build and run with Docker

Docker will expose port 8080, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```sh
cd MyPosts
docker build -t myposts .
```
This will create the myposts image and pull in the necessary dependencies. 

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8000 of the host to port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart="always" myposts
```

Verify the deployment by navigating to your server address in your preferred browser.

`http://localhost:8000/`

## JWT Authentication

In authentication, when the user successfully logs in using their credentials, a JSON Web Token will be returned and must be saved locally (typically in local storage, but cookies can be also used), instead of the traditional approach of creating a session in the server and returning a cookie.

Whenever the user wants to access a protected route or resource, the user agent should send the JWT, typically in the Authorization header using the Bearer schema. The content of the header should look like the following:

`Authorization: Bearer <token>`

This is a stateless authentication mechanism as the user state is never saved in server memory. The server's protected routes will check for a valid JWT in the Authorization header, and if it's present, the user will be allowed to access protected resources. As JWTs are self-contained, all the necessary information is there, reducing the need to query the database multiple times.

The following diagram shows this process:

![](https://camo.githubusercontent.com/5871e9f0234542cd89bab9b9c100b20c9eb5b789/68747470733a2f2f63646e2e61757468302e636f6d2f636f6e74656e742f6a77742f6a77742d6469616772616d2e706e67) 

## Testing

### Login

In order to authenticate the user, a POST request is sent to the login endpoint `/v1/users/login`, where the email and password are sent as a JSON object in the body:
```
curl -X POST "http://localhost:8080/v1/users/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"email\": \"test@pharos.de\", \"password\": \"Ph@ros-90F\"}"
```
After authentication a JSON Web Token (JWT) is returned. The response should look as follow:
```
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHBoYXJvcy5kZSIsImV4cCI6MTU2NjM3MjQwMywiaWF0IjoxNTY2MzU0NDAzfQ.pnrYwU3F-37H_OmwXz9qzsmoZwWIC9sRA-mQGCgPYQ4svT0Uu_UWCPPXPLYBaBBCNVdl3Kg8wkMAQYL44elP4A"
}
```

### Creating a new Post

In order to be able to publish a new post, the JSON Web Token (JWT) **must** be included in the headers as descibed in the JWT Athentication section:
```
curl -X POST "http://localhost:8080/v1/posts" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHBoYXJvcy5kZSIsImV4cCI6MTU2NjM3MjQwMywiaWF0IjoxNTY2MzU0NDAzfQ.pnrYwU3F-37H_OmwXz9qzsmoZwWIC9sRA-mQGCgPYQ4svT0Uu_UWCPPXPLYBaBBCNVdl3Kg8wkMAQYL44elP4A" -H "Content-Type: application/json" -d "{ \"content\": \"string\", \"status\": \"PUBLIC\"}"
```
Note: Post content cannot be either null or empty, status is a string that should equal to either "PUBLIC" or "PRIVATE".

### Searching public posts
To search for a specified pattern in a post convent:

```
curl -X GET "http://localhost:8080/v1/posts/search?text=string" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHBoYXJvcy5kZSIsImV4cCI6MTU2NjM3MjQwMywiaWF0IjoxNTY2MzU0NDAzfQ.pnrYwU3F-37H_OmwXz9qzsmoZwWIC9sRA-mQGCgPYQ4svT0Uu_UWCPPXPLYBaBBCNVdl3Kg8wkMAQYL44elP4A"
```


###   Test credentials

`email: test@pharos.de`
`password: Ph@ros-90F`

## API documentation

#### SpringFox

The Springfox suite of java libraries are all about automating the generation of machine and human readable specifications for JSON APIs written using the spring family of projects. Springfox works by examining an application, once, at runtime to infer API semantics based on spring configurations, class structure and various compile time java Annotations.

To access the API documentation, access the following endpoint through your favorite browser

`/swagger-ui.html`

#### Specs:
1. `Authentication` spec contains all the endpoints that do not need authentication with JWT, ie: login, registration.
2. `Posts` spec contains all the endpoints related to the post, these endpoint need authentication with a JWT.

Note: When using the swagger UI, the word `Bearer` should be added before the JWT in the `Authorize` popup, example :
```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHBoYXJvcy5kZSIsImV4cCI6MTU2NjM3MjQwMywiaWF0IjoxNTY2MzU0NDAzfQ.pnrYwU3F-37H_OmwXz9qzsmoZwWIC9sRA-mQGCgPYQ4svT0Uu_UWCPPXPLYBaBBCNVdl3Kg8wkMAQYL44elP4A
```

## Stack

MyPosts uses a number of open source projects to work properly:

* [Docker] - makes it easier to create, deploy, and run applications by using containers. 
* [Maven] - software project management and comprehension tool.
* [Spring boot] - java-based framework used to create a micro Service.
* [H2] - a lightweight in-memory Java database.
* [Swagger] - helps design, build, document, and consume RESTful Web services.

[Docker]: <https://www.docker.com/>
[Maven]: <https://maven.apache.org/>
[Spring boot]: <https://spring.io/projects/spring-boot>
[H2]: <https://www.h2database.com/html/main.html>
[Swagger]: <https://swagger.io/>