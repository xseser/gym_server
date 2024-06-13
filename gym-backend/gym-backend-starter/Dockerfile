FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/gym-backend-starter-0.0.1-SNAPSHOT.jar /app/app.jar

# Declare environment variables
ENV DB_SOURCE=jdbc:mysql://localhost:3306/mydb
ENV DB_LOGIN=root
ENV DB_PASSWORD=secret

EXPOSE 9876
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
