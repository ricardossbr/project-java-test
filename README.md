# Java Test Back end

---

## Project description 
This project is a test of knowledge in java with SpringBoot

---

## Set up

To run this project, please using this technologies bellow:
```yaml
1. JDK 17
2. Spring boot 3.0.0
3. lombok 1.18.x
4. Apache Maven 3.8.6
5. MongoDB 3.8 or Docker Compose to v2. 11.2
```

---

---

## To Build

To build the project use this command bellow:
```yaml
1- mvn clean install
```


## Configuration

Before to set up the spring boot, it´s necessary to up the docker-compose in /infrastructure using the command bellow:
```yaml
docker-compose up
````

if the MongoDB is local,  please to configure this file: **application.properties** in the project: **src/main/resources**, following the example:

```yaml
spring.data.mongodb.database=${SPRING_MONGODB_DATABASE:ubs-bank}
spring.data.mongodb.host=${SPRING_MONGODB_HOST:localhost}
spring.data.mongodb.port=${SPRING_MONGODB_PORT:27017}
spring.data.mongodb.username=${SPRING_MONGODB_USERNAME:ubs-bank}
spring.data.mongodb.password=${SPRING_MONGODB_PASSWORD:ubs-bank}
````
---

## To run test
using this command bellow to run all test:
```yaml
mvn test
```

## Links
```yaml
1 - localhost:8080/up {Get - To populate the databases, It´s necessary when start}
2 - localhost:8080/teams-users {Get - All Element in database }
3 - localhost:8080/team-user?idTeam= {Get - find the element with idTeam }
4 - localhost:8080//team-user {Post - It´s necessary to pass TeamAndUserDTO in the body example bellow:}
5 - localhost:8080//team-user?idTeam= {Put - It´s necessary to pass TeamAndUserDTO in the body example bellow and idTeam for change for the new teamAndUser}
6 - localhost:8080/team-user?idTeam= {Delete -  It´s necessary to pass idTeam to delete the TeamAndUser}

```

```yaml
TeamAndUserDTO =  {
    "id": "7676a4bf-adfe-415c-941b-1739af07039b",
    "name": "Ordinary Coral Lynx",
    "users": [
        {
            "id": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
            "displayName": "gianniWehner"
        },
        {
            "id": "aa569071-6ade-4ff6-b567-6466fcbae74a",
            "displayName": "marionKertzmann"
        },
        {
            "id": "fa1529de-5f20-49a7-ad25-a494008dd322",
            "displayName": "jarenKerluke"
        },
        {
            "id": "1b140966-5a01-49da-872e-71a769f98941",
            "displayName": "carmeloStark"
        },
        {
            "id": "fddcde65-70b2-49f9-8b4d-5126adc345c1",
            "displayName": "brendenVolkman"
        }
    ],
    "teamLead": {
        "id": "fddcde65-70b2-49f9-8b4d-5126adc345c1",
        "displayName": "brendenVolkman"
    }
}
```