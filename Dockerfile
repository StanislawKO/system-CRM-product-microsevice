FROM maven:3.9.8-amazoncorretto-21-al2023 AS build

COPY product-service-app/src product-service-app/src
COPY product-service-app/pom.xml product-service-app/pom.xml
COPY pom.xml .
RUN mvn -B dependency:resolve
RUN mvn -B dependency:resolve-plugins

RUN mvn -B clean package -Dcheckstyle.skip && \
    VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout) && \
    cp product-service-app/target/product-service-app-${VERSION}.jar product-service-app.jar

FROM openjdk:21-jdk-slim

RUN adduser --system product-service

USER product-service

WORKDIR /product-service-app

COPY --from=build ./product-service-app.jar ./product-service-app.jar

EXPOSE 8080
EXPOSE 8043

ENTRYPOINT ["java", "-jar", "product-service-app.jar"]
