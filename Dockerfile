FROM amazoncorretto:21
WORKDIR app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo.jar
COPY .env.dev .env
EXPOSE 8080

#ENV SPRING_DATASOURCE_URL="jdbc:mysql://db:3306/demo?allowPublicKeyRetrieval=true"
#ENV SPRING_DATASOURCE_USERNAME="user"
#ENV SPRING_DATASOURCE_PASSWORD="password"

ENTRYPOINT ["java","-jar","demo.jar"]