# League-Service
League Service Demo as per requirements.

## **1. Prerequisites**

1. Java runtime
2. Maven

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
There are three ways to process league data with the league-service
### *3.1. From the command line*
Run the jar file from the command line with the league data file's path as the only parameter. Eg: using the demo file provided, and running from the project's root directory, the command would be
```
java -jar target/league-service-1.0.0-SNAPSHOT.jar src/main/resources/LeagueData.txt
```
Note: No input validation is performed in this mode.

### *3.2. As a rest microservice*
Start the service as a spring boot microservice:
```
java -jar target/league-service-1.0.0-SNAPSHOT.jar
```
or in your favorite IDE.
The server runs on port 9000 by default.

#### *3.2.1 Processing raw data*
The endpoint for processing raw league data is http://localhost:9000/league-service/v1/rankingService/processRawLeagueData. The raw data passed as a plaintext body.

Note: No input validation is performed in this mode.
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

#### *3.2.2 Processing json formatted data*
Endpoint: http://localhost:9000/league-service/v1/rankingService/processLeagueData

This endpoint consumes and produces json data.

Note: Validation is performed on input data:
1. Team names may not be blank or null
2. scores must be >=0
3. At least one matchResult is required in list
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

## **4. Documentation**
### **4.1 Yaml**
http://localhost:9000/league-service/v2/api-docs
### **4.2 Swagger UI**
http://localhost:9000/league-service/swagger-ui.html