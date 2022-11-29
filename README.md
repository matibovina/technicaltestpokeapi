###CONSUMMING POKEMON API

create .env file with this  parameters: 

MYSQLDB_USER=
MYSQLDB_ROOT_PASSWORD=
MYSQLDB_DATABASE=pokedex
MYSQLDB_LOCAL_PORT=3307
MYSQLDB_DOCKER_PORT=3306

SPRING_LOCAL_PORT=6868
SPRING_DOCKER_PORT=8080

RUN docker-compose up -d on your terminal

Navigate to http://localhost:6868/swagger-ui/index.html#/pokedex-controller