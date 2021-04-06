FROM openjdk:8-jre-alpine

RUN wget https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.27.2/wiremock-standalone-2.27.2.jar

RUN mkdir /mappings

COPY /mappings /mappings

CMD java -jar wiremock-standalone-2.27.2.jar --verbose --global-response-templating
