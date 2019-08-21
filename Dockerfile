FROM maven:3-jdk-8

RUN mkdir /MyPosts

WORKDIR /MyPosts

ADD . /MyPosts/

RUN ["chmod", "+x", "/MyPosts/docker-entrypoint.sh"]

RUN ["mvn", "package"]

ENTRYPOINT /MyPosts/docker-entrypoint.sh