# Facebook Analyze

A little project to facebook analyze from [Graph API](https://graph.facebook.com/), initially is an application that consumes, gets and analyzes posts of a particular page.

## Technologies Requirements  

* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Gradle 3.5](https://services.gradle.org/distributions/gradle-3.5-bin.zip) - when you run with gradle wrapper, it is installed automatically
* [PostgreSQL 9.5](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
## Configuration to Database

You can change the configuration to access database in file: `src/main/resources/application.properties` in fields `spring.datasource.*`

## Configuration to Execute

You must put a token access in class `com.buzzmonitor.facebook.analyze.util.Constants` on field `ACCESS_TOKEN` to can get the posts from Graph API.

## Units Tests

> You can see some tests in class `com.buzzmonitor.facebook.analyze.service.FacebookAnalyzeServiceTest`

