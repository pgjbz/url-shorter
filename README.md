# url-shorter

Basic url shorter using hash-ids

Dependencies:
 - Java 17
 - Maven 3.8.6
 - Docker


To run local database:

```shell
docker compose up -d
```

Run application:

```shell
mvn spring-boot:run
```

Build native image:

```shell
mvn spring-boot:build-image -Pnative
```

Build binary:

```shell
mvn native:compile -Pnative
```

Access: localhost:8080/swagger-ui.html to see swagger

Configuration:

Environment Variables:

```bash
# database config
PG_HOST=host_postgres
PG_PORT=port_postgres
PG_DB=database_name
PG_USER=database_username
PG_PASS=database_password
#hashids config
ID_SALT=hashids_salt
#rate limiter
RATE_LIMIT_DURATION=500 #in ms
RATE_LIMIT_REFRESH_PERIOD=100 #in ms
RATE_LIMIT_REQUESTS_PER_PERIOD=50 #requests in refresh period
```

Goals:

- [X] Short URL with Opaque id
- [X] Limit rate
- [X] Add Swagger
- [ ] k6 tests
