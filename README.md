### CONSUMMING POKEMON API

RUN mvn clean package

create _.env_  file with parameters: 

MYSQLDB_USER= \
MYSQLDB_ROOT_PASSWORD= \
MYSQLDB_DATABASE=pokedex \
MYSQLDB_LOCAL_PORT=3307 \
MYSQLDB_DOCKER_PORT=3306 \
SPRING_LOCAL_PORT=6868 \
SPRING_DOCKER_PORT=8080 \
MARIADB_ROOT_PASSWORD= \
MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=TRUE

RUN:  **_docker-compose up -d_** on your terminal

Navigate to [Pokedex](http://localhost:6868/swagger-ui/index.html#/pokedex-controller "Pokedex")