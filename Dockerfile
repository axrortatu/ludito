# Build Stage
FROM gradle:8.12-jdk17 AS BUILD
WORKDIR /usr/app
COPY . .
RUN gradle build --no-daemon -x test

# Package Stage
FROM openjdk:17-jdk-slim
ENV JAR_NAME=app.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD /usr/app/build/libs/*.jar $JAR_NAME
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
