FROM maven:latest as builder
WORKDIR /app/
COPY . .
RUN mvn -DskipTest package

FROM eclipse-temurin:17.0.8_7-jre as final
WORKDIR /app/
COPY --from=builder /app/target/delivery-service-1.0-SNAPSHOT.jar .
EXPOSE 8082
ENTRYPOINT exec java -jar delivery-service-1.0-SNAPSHOT.jar
