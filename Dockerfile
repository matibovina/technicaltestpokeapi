FROM openjdk:11.0.2

WORKDIR /app

COPY ./target/alea-pokedex-0.0.1-SNAPSHOT.jar .

RUN mvnw clean install

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "alea-pokedex-0.0.1-SNAPSHOT.jar"]