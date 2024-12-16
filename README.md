![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

# Spring Boot Boilerplate

This is a boilerplate that can be used to set up Spring Boot projects.

## Technologies & Dependencies

- Java
- MySQL
- Maven
- Spring Boot - Web, Security
- Spring Data JPA
- Jakarta Validation API
- JUnit, Mockito
- Apache Kafka, Zookeeper
- Docker, Docker Compose
- Lombok
- MapStruct
- Swagger

## Build

There are two environment variable files: `.env.dev` for the `application.yaml` file, and `.env` inside the `docker` folder, which you need to set up manually. The `.env` file contains properties required to run the MySQL image. Set up the environment in your IDE so the files will be read properly.

You will want to build the application image from the JAR file, as well as manually pull some other images (MySQL, Kafka, Zookeeper).

Navigate to the root directory and run the following command to build the JAR file:

```
mvn clean package
```

Then, build the image from the JAR file:

```
docker build --tag app .
```

## Run

Navigate to the `docker` directory and start the containers with:

```
docker compose up -d
```
