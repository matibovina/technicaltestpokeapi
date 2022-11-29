FROM openjdk:11

WORKDIR /app

COPY ./target/alea-pokedex-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD mvn spring-boot:run

ENTRYPOINT ["java", "-jar", "alea-pokedex-0.0.1-SNAPSHOT.jar"]
