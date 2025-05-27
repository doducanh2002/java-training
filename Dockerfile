FROM maven:3.9.6 AS builder
ARG app_path=/app
WORKDIR $app_path
COPY pom.xml pom.xml
COPY core/pom.xml core/pom.xml
COPY core/core-email/pom.xml core/core-email/pom.xml
COPY core/core-exception/pom.xml core/core-exception/pom.xml
COPY core/core-paypal/pom.xml core/core-paypal/pom.xml
COPY core/core-quartz-job/pom.xml core/core-quartz-job/pom.xml
COPY feature/pom.xml feature/pom.xml
COPY feature/auth/pom.xml feature/auth/pom.xml
COPY feature/customer/pom.xml feature/customer/pom.xml
COPY feature/employee/pom.xml feature/employee/pom.xml
COPY feature/book-service/pom.xml feature/book-service/pom.xml
COPY feature/student/pom.xml feature/student/pom.xml
COPY feature/booking/pom.xml feature/booking/pom.xml
COPY main/pom.xml main/pom.xml

COPY core/core-email/src core/core-email/src
RUN mvn clean install -pl core/core-email -am

COPY core/core-exception/src core/core-exception/src
RUN mvn clean install -pl core/core-exception -am

COPY core/core-paypal/src core/core-paypal/src
RUN mvn clean install -pl core/core-paypal -am

COPY core/core-quartz-job/src core/core-quartz-job/src
RUN mvn clean install -pl core/core-quartz-job -am

COPY feature/auth/src feature/auth/src
RUN mvn clean install -pl feature/auth -am

COPY feature/employee/src feature/employee/src
RUN mvn clean install -pl feature/employee -am

COPY feature/customer/src feature/customer/src
RUN mvn clean install -pl feature/customer -am

COPY feature/booking/src feature/booking/src
RUN mvn clean install -pl feature/booking -am

COPY main/src main/src
RUN mvn -pl main clean install

FROM openjdk:17-alpine
ARG app_path=/app
WORKDIR $app_path
COPY --from=builder $app_path/main/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080