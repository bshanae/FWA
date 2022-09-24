# FWA

Small Java Web project for learning Java Servlets.
The required functionlaity is following:
* Registration,
* Authorization,
* Adding image to profile,
* Display date and time of each login.

## Instructions

### Prerequisites

To run this project you'll need following tools:
* Maven,
* Tomcat,
* Some database manager, for example PostgreSQL.

### 1. Setup database

Create a database.
Configure it by running SQL commands from `src/resources/sql/schema.sql`.

### 2. Configure the project

Set images directory and credentials for accessing database in `src/resources/application.properties`.

### 3. Build web archive

Use following command in the root the project to build the WAR:
``` shell
mvn clean install
```

### 4. Deploy web archive 

First, you need to launch Tomcat:
``` shell
[tomcat root]/libexec/bin/shutdown.sh
```

Then deploy the archive by placing it in `webapps` directory:
``` shell
cp target/fwa.war [tomcat root]/libexec/webapps/ROOT.war 
```
