FROM alpine/java:21-jdk

ADD . /tmp
WORKDIR /tmp

RUN apk update && \
    apk add maven &&  \
    apk cache clean \
    mvn install \

CMD ["mvn", "spring-boot:run"]

EXPOSE 80
