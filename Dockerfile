FROM docker.io/maven:3.8.6-eclipse-temurin-17-focal as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM docker.io/eclipse-temurin:17-jre-alpine
ENV USER=pgjbz
ENV UID=12345
ENV TZ=America/Sao_Paulo
WORKDIR /app
RUN ln -sf /usr/share/zoneinfo/${TZ} /etc/localtime && \
     adduser \
    --disabled-password \
    --gecos "" \
    --home "$(pwd)" \
    --no-create-home \
    --uid "$UID" \
    "$USER"
USER ${USER}
COPY --from=builder /app/target/app.jar ./app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar