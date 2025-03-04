FROM maven:3.9-amazoncorretto-21-alpine  AS  build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/Shoppy-0.0.1-SNAPSHOT.jar Shoppy.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","Shoppy.jar" ]