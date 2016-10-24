FROM maven:3.3-jdk-8
EXPOSE 8080
ADD settings.xml /root/.m2/settings.xml

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn install