FROM openjdk:17-alpine
ARG JAR_FILE=greenworld_user_service-0.0.1-SNAPSHOT.jar
COPY ./build/libs/${JAR_FILE} greenworld_user_service.jar
ENTRYPOINT ["java","-jar","/greenworld_user_service.jar"]