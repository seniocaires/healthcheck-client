FROM maven AS builder

ADD . /build

WORKDIR /build

RUN mvn dependency:resolve verify package

FROM java:8

WORKDIR /app

COPY --from=builder /build/target/healthcheck-client-jar-with-dependencies.jar /app/run.jar

EXPOSE 4567

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-DHOSTS=$HOSTS", "-DSLACK_TOKEN=$SLACK_TOKEN", "-DSLACK_CANAIS=$SLACK_CANAIS", "-DSLEEP=$SLEEP", "-jar", "run.jar"]