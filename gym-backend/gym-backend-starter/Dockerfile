FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/gym-backend-starter-0.0.1-SNAPSHOT.jar /app/app.jar

ENV DB_SOURCE=jdbc:postgresql://db:5432/gym_database
ENV DB_LOGIN=postgres
ENV DB_PASSWORD=mysecretpassword

EXPOSE 9876
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
