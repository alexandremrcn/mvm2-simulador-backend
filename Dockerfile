FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o JAR gerado pelo módulo REST
COPY mvm2-rest-simulator/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
