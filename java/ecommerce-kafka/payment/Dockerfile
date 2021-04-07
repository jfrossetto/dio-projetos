FROM amazoncorretto:11-alpine-jdk
ARG TARGET_JARFILE=build/libs/payment-0.0.1-SNAPSHOT.jar
COPY ${TARGET_JARFILE} dio-payment-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/dio-payment-api-0.0.1.jar"]