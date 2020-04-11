mvn -Ddocker.env.postgres.jdbc.url=jdbc:postgresql://postgres:5432/${POSTGRES_USER} \
    -Ddocker.env.postgres.user=${POSTGRES_USER} \
    -Ddocker.env.postgres.password=${POSTGRES_PASSWORD} \
    spring-boot:run