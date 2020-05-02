mvn -Ddocker.env.postgres.jdbc.url=jdbc:postgresql://postgres:5432/${POSTGRES_USER} \
    -Ddocker.env.postgres.user=${POSTGRES_USER} \
    -Ddocker.env.postgres.password=${POSTGRES_PASSWORD} \
    -Ddocker.env.mail.enable=${MAIL_ENABLE} \
    -Ddocker.env.mail.host=${MAIL_HOST} \
    -Ddocker.env.mail.port=${MAIL_PORT} \
    -Ddocker.env.mail.user=${MAIL_USER} \
    -Ddocker.env.mail.password=${MAIL_PASSWORD} \
    -Ddocker.env.liquibase.context=${LIQUIBASE_CONTEXT} \
    spring-boot:run