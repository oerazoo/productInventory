# Usamos una imagen ligera de Java 21
FROM eclipse-temurin:21-jdk-alpine
# Exponer el puerto (8080 para el API, 8081 para el Processor)
EXPOSE 8080
# Copiar el JAR que genera Maven
COPY target/*.jar app.jar
# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]