# League-Service
League Service Demo as per requirements.

## **1. Prerequisites**

1. Java runtime
2. Maven
3. Docker (optional)

## **2. Setup**
    
1. Clone the repo from github:
```
git clone https://github.com/lablouw/leagueservice
```
2. Build the project:
```
mvn clean install
```

## **3. Running the service**
### *3.1. Running from the command line*
Run the jar file from the command line with the league data file's path as the only parameter. Eg: using the demo file provided, and running from the project's root directory, the command would be
```
java -jar target/league-service-1.0.0-SNAPSHOT.jar src/main/resources/LeagueData.txt
```
Note: No input validation is performed in this mode.

### *3.2. Running as a REST microservice*
Start the service as a Spring Boot microservice:
```
java -jar target/league-service-1.0.0-SNAPSHOT.jar
```
or in your favorite IDE.
The server runs on port 9000 by default.

Alternatively it can be run in a Docker container by executing the following from the project root directory:
```
mvn clean install
docker build --tag=league-service:latest .
docker run -p9000:9000 league-service
```

## *4. Using the service*
Two entry points are provided for processing league data.

###4.1. Processing raw data
URL: http://localhost:9000/league-service/v1/rankingService/processRawLeagueData

HEADERS: content-type: text/plain

BODY: (plaintext data)

```
curl --request POST \
  --url http://localhost:9000/league-service/v1/rankingService/processRawLeagueData \
  --header 'content-type: text/plain' \
  --data 'Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0'
```
_Note: No input validation is performed in this mode._

### *4.2. Processing json formatted data*
URL: http://localhost:9000/league-service/v1/rankingService/processLeagueData

HEADERS: content-type: application/json

BODY: (json data)

```
curl --request POST \
  --url http://localhost:9000/league-service/v1/rankingService/processLeagueData \
  --header 'content-type: application/json' \
  --data '{
  "matches":[
    {
      "team1": "Lions",
      "team2": "Snakes",
      "score1": 3,
      "score2": 3
    },
    {
      "team1": "Tarantulas",
      "team2": "FC Awesome",
      "score1": 1,
      "score2": 0
    },
    {
      "team1": "Lions",
      "team2": "FC Awesome",
      "score1": 1,
      "score2": 1
    },
    {
      "team1": "Tarantulas",
      "team2": "Snakes",
      "score1": 3,
      "score2": 1
    },
    {
      "team1": "Lions",
      "team2": "Grouches",
      "score1": 4,
      "score2": 0
    }
  ]
}'
```
_Note: Validation is performed on input data:_
1. _Team names may not be blank or null_
2. _Scores must be >=0_
3. _At least one matchResult is required in the list_

## **5. Documentation**
### **5.1. Yaml**
http://localhost:9000/league-service/v2/api-docs
### **5.2. Swagger UI**
http://localhost:9000/league-service/swagger-ui.html