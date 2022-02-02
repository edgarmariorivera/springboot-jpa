# retospadel API's

This is simple SpringBoot application to connect to a Postgres database and expose simple RESTful APIs.

The application use a docker-compose file to start Postrgess and create the user and database

## Application technical stack
- Java 17
- Spring Boot 2.6.3
- Spring Web
- Spring Data
- Lombok
- Postgre SQL


### Running the application locally

*Requirements*

- Java 17 SDK installed
- Maven
- Docker

*Running docker images*

docker-compose.yml
```
version: "3.7"

services:

  postgres14:
    image: postgres
    ports:
      - 5432:5432
    expose:
      - 5432
    environment:
      - POSTGRES_USER=test_user
      - POSTGRES_PASSWORD=test_user_password
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
      - postgres_data:/var/lib/postgres14/data

volumes:
  postgres_data:
    driver: local
```

This file has a dependency on another file that creates the required DB. You can always create this DB manually anytime.

init.sql
```
CREATE DATABASE test_db;
```

Open a terminal window ([iTerm2](https://iterm2.com/downloads.html)) and run docker-compose up

```
$ docker-compose up
```

This command will download the PostgreSQL and start the images

You can now connect to your local PostgreSQL by using PgAdmin app, use localhost and port 5432. Us the below credentials to connect.

```
- POSTGRES_USER=test_user
- POSTGRES_PASSWORD=test_user_password
```

*Compiling all source code*

You can use standard maven command like

```
$ mvn clean package
```

This script will compile all the source 

*Running the application locally*

I highly recommend to use [IntelliJ IDEA](https://www.jetbrains.com/idea/download) as IDE. There is not configuration files pushed to the repository, so it's up to you what tool to use. IntelliJ has great support for maven adn spring, it will create your project structure by just opening de folder that contains the pom.xml file.

There is 2 ways to run the application. One way is with maven another from IntelliJ. Let's see both:

```
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DPOSTGRES_HOST=localhost -DPOSTGRES_DB_NAME=test_db -DPOSTGRES_USER=test_user -DPOSTGRES_PASSWORD=test_user_password"
```

If you want to use IntelliJ and lunch the apps from the code, just simple open IntelliJ, then File -> Open and point to this folder `~/springboot-jpa`. Check auto-import option and this will import the project and recognise as maven project. From here expand the `src/main/java/com.example.demo` and right click on the main application `DemoApplication` and select "Run DemoApplication". This will start the Java process inside the IDE. You can also run in debug mode.

Open a browser and try this URL

http://localhost:8080/countries to see the list of countries

You can also POST, PUT and DELETE country entities

POST http:///localhost:8080/countries --> JSON body
```
{
  "name": "Colombia"
}
```

This will return a new entity with a unique UUID

PUT http:///localhost:8080/countries/{id} --> JSON body
```
{
  "name": "Colombia - MOD"
}
```

This will return the modified entity

DELETE http:///localhost:8080/countries/{id}
This will return HttpCode 200 if everything goes fine.


*Building a container*

You can use standard maven command and jib plugin. Be aware that you need to authenticate on a GCP project and passed the id like this

```
$ mvn clean package -P GCLOUD_PROJECT=your_google_project_id jib:build
```

This will create a docker image base on Temerium and push the image to eu.gcr.io/${GCLOUD_PROJECT}/${project.artifactId}

From there you can deploy to Cloud Run or a kubernetes cluster and pass the vlaues for these properties

```
POSTGRES_HOST=localhost;
POSTGRES_DB_NAME=test_db;
POSTGRES_USER=test_user;
POSTGRES_PASSWORD=test_user_password
```

**Folder structure**

- *src/main/java*
    - This folder contains all Java source code
- *src/main/resources*
    - This folder contains configuration properties and DB migration scripts