FROM maven:3-jdk-8

RUN mkdir /backend

WORKDIR /backend

COPY . /backend/

RUN mvn package

ENTRYPOINT java -jar target/backend-0.0.1-SNAPSHOT.jar