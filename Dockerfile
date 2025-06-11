# Etapa 1: Build
FROM gradle:8.5-jdk17 AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY build.gradle settings.gradle ./
COPY gradle/ gradle/

# Descargar dependencias (optimización de cache)
RUN gradle dependencies --no-daemon

# Copiar código fuente
COPY src/ src/

# Construir la aplicación
RUN gradle bootJar --no-daemon

# Etapa 2: Runtime
FROM openjdk:17-jre-slim

# Instalar herramientas útiles y crear usuario no-root
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    useradd -r -s /bin/false appuser

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=builder /app/build/libs/*.jar app.jar

# Cambiar propietario del directorio
RUN chown -R appuser:appuser /app

# Cambiar a usuario no-root
USER appuser

# Exponer el puerto (por defecto Spring Boot usa 8086)
EXPOSE 8086

# Variables de entorno por defecto
ENV JAVA_OPTS="-Xms256m -Xmx512m" \
    SPRING_PROFILES_ACTIVE=prod

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]