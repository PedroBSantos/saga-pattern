FROM maven:latest as builder
WORKDIR /app/
COPY . .
RUN mvn -DskipTests package

FROM eclipse-temurin:17.0.8_7-jre as final
WORKDIR /app/
COPY --from=builder /app/target/stock-service-1.0-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT exec java -jar stock-service-1.0-SNAPSHOT.jar
