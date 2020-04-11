FROM openjdk:13-alpine
RUN apk add maven
WORKDIR /var/service
EXPOSE 8080
COPY ./pom.xml .
RUN mvn dependency:go-offline
COPY . .
CMD ["sh", "./entrypoint.sh" ]