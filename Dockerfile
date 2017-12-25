FROM openjdk:8-jdk-alpine

RUN apk add --update ttf-dejavu && rm -rf /var/cache/apk/*
RUN apk add --no-cache tzdata
ENV TZ America/Mexico_City
VOLUME /tmp
ADD target/representacionImpresa-0.0.1.jar app.jar
ENV JAVA_OPTS="-Xmx300m"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=prd app.jar
