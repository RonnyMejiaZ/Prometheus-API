FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Download webapp-runner from Maven Central
RUN wget -O webapp-runner.jar https://repo1.maven.org/maven2/com/github/jsimone/webapp-runner/9.0.70.0/webapp-runner-9.0.70.0.jar

COPY --from=build /app/target/prometheus-web-1.0.0.war ./ROOT.war

EXPOSE 8080
ENV PORT=8080
CMD ["sh", "-c", "java -jar webapp-runner.jar --port $PORT ./ROOT.war"]
