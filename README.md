<h1>INTRODUCTION</h1>

This is a SpringBoot Web app that provides an http post service to calculate the total commission for a list of trades.

<h2>Running the application </h2>
Navigate to the root directory of the application
<h3>Run the application using maven</h3>
This assumes you have maven installed on your machine.


1. Run the command below (If you are on a Mac, first run `xattr -d com.apple.quarantine ./mvnw`)
```
./mvnw package
```
This also runs the tests.

2. Run the command
```
java -jar target/tradeservice-0.0.1-SNAPSHOT.jar
```
This starts up the application. You can navigate to the section `Make a Post Request` for guidance on making HTTP requests to the application.

<h3>Run the application using Docker</h3>
Run the following commands

```
docker build -t clarivate-app .
docker run -p 8080:8080 clarivate-app
```

(You can use any image name instead of `clarivate-app`).
You can navigate to the section `Make a Post Request` for guidance on making HTTP requests to the application.

<h3>Make a POST request</h3>


There are several tools to make HTTP requests to an application (e.g. Postman). E.g. you can use the parameters

```
Request type : POST
URL : http://localhost:8080/trades/calculate
Body Media Type : JSON
Example body :

http://localhost:8080/trades/calculate
[
 {"securityType":"STO", "transactionType":"BUY", "price":1, "quantity":100,"tradeTime":"2021-01-07 02:02:16.172"},
 {"securityType":"FX", "transactionType":"SELL", "price":2, "quantity":400,"tradeTime":"2021-01-01 03:03:11.111"}
]
```


<h3>Testing the application</h3>
Navigate to the root directory of the application.
(If you are on a Mac, first run `xattr -d com.apple.quarantine ./mvnw`)
Run the command `./mvnw package`. It runs all the tests, and builds a jar file.

<h3>Notes</h3>
I have done some stress testing on this. It can handle > 10 requests at a time.

<h3>Improvements</h3>

There are many things to improve this, but I ran out of time.
* More comprehensive tests are needed.
* Performance can be improved potentially by Spring WebFlux. Or it could be run on the cloud, e.g. AWS. Configure scaling measures like load balancing, replication etc,
* There are some TODO's in the code on potential improvements.
