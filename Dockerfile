FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:10.1-jre17
WORKDIR /app

# Copy the WAR file
COPY --from=build /app/target/prometheus-web-1.0.0.war /usr/local/tomcat/webapps/ROOT.war

# Set environment variables
ENV PORT=8080

# Create startup script
RUN echo '#!/bin/sh' > /start.sh && \
    echo 'export CATALINA_OPTS="-Dserver.port=$PORT"' >> /start.sh && \
    echo 'exec catalina.sh run' >> /start.sh && \
    chmod +x /start.sh

EXPOSE 8080
CMD ["/start.sh"]
