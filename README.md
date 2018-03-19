# LifeTracker (iteration 1)

## Overview
This is the first iteration of the LifeTracker application, used for
demonstration purposes for a talk on the virtues of virtualisation (like
containerisation).

This particular iteration comes without any way of automatically deploying the
application or its database. This is the most manual of the 3 iterations when it
comes to deploying this stack.

## Requirements
You will need the following software installed to build and run the application:

* [PostgreSQL 9.x](https://www.postgresql.org/)
* [Gradle 4.x](https://gradle.org)
* [Java 8](http://openjdk.java.net/)

## Testing Setup
To be able to build the application, you will first need a PostgreSQL database
available for the unit tests to pass.

```bash
# switch to Postgres user
> sudo su postgres

# create the role
> psql -c "CREATE ROLE lifetracker WITH LOGIN PASSWORD 'lifetracker';"

# create the testing database
> psql -c "CREATE DATABASE lifetracker_iter1_test WITH OWNER lifetracker;"
```

## Building
Run the following to download all dependencies and build the application:

```bash
> gradle clean build
```

Will also execute tests against the `lifetracker_iter1_test` database.

## Database Migrations
To run the application locally, you first need to migrate the PostgreSQL
database:

```bash
> sudo su postgres
> psql -c "CREATE DATABASE lifetracker_iter1 WITH OWNER lifetracker;"
> exit

> gradle -Pflyway.url=jdbc:postgresql://localhost:5432/lifetracker_iter1 \
    -Pflyway.user=lifetracker \
    -Pflyway.password=lifetracker \
    flywayMigrate
```

## Run the Application
Now run it:

```bash
> gradle clean bootRun
```

