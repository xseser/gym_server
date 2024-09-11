FROM eclipse-temurin:21-jre-alpine
WORKDIR /mail
COPY target/gym-mail-starter-0.0.1-SNAPSHOT.jar /mail/mail.jar

ENV DB_SOURCE=jdbc:postgresql://postgres:5432/gym_database
ENV DB_LOGIN=postgres
ENV DB_PASSWORD=mysecretpassword

EXPOSE 5443
ENTRYPOINT ["java", "-jar", "/mail/mail.jar"]
