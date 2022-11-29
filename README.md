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