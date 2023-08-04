FROM maven:latest as builder
WORKDIR /app/
COPY . .
RUN mvn -DskipTest package

FROM eclipse-temurin:17.0.8_7-jre as final
WORKDIR /app/
COPY --from=builder /app/target/order-saga-orchestrator-1.0-SNAPSHOT.jar .
ENTRYPOINT exec java -jar order-saga-orchestrator-1.0-SNAPSHOT.jar
