FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN mvn -q -DskipTests package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "mvm2-rest-simulator/target/*.jar"]
