FROM amazoncorretto:11-alpine-jdk
ARG TARGET_JARFILE=build/libs/checkout-0.0.1-SNAPSHOT.jar
COPY ${TARGET_JARFILE} dio-checkout-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/dio-checkout-api-0.0.1.jar"]