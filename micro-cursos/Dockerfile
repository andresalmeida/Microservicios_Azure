## Usar una imagen de OpenJDK
#FROM openjdk:17-jdk-slim
#
## Setear el directorio de trabajo
#WORKDIR /app
#
## Volumen montado
#VOLUME /tmp
#
### Copiar el JAR del proyecto al contenedor
##COPY target/micro-cursos-0.0.1-SNAPSHOT.jar app.jar
#
## Inicio Modificacion
#
#COPY ./.mvn ./.mvn
#COPY ./mvnw ./mvnw
#COPY ./pom.xml ./pom.xml
#
#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#
#COPY ./src ./src
#
#RUN ./mvnw clean package -DskipTests
#
## Fin Modificacion
#
## Exponer el puerto del servicio
#EXPOSE 8003
#
### Ejecutar la aplicación Spring Boot
##ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#
## Ejecutar la aplicación con la modificación
#ENTRYPOINT ["java", "-jar", "./target/micro-cursos-0.0.1-SNAPSHOT.jar"]



# NUEVO ARCHIVO

# ETAPA 1: COMPILATION

# Usar una imagen de OpenJDK
FROM openjdk:17-jdk-slim AS builder
# Setear el directorio de trabajo
WORKDIR /app
# Copiar solo lo necesario para instalar dependencias
COPY ./.mvn ./.mvn
COPY ./mvnw ./mvnw
COPY ./pom.xml ./pom.xml
# Descargar dependencias en cache
RUN ./mvnw dependency:go-offline
# Copiar el codigo fuente y compilar
COPY ./src ./src
RUN ./mvnw clean package -DskipTests

# ETAPA 2: IMAGEN DE PRODUCCION

# Usar una imagen de OpenJDK
FROM openjdk:17-jdk-slim
# Setear el directorio de trabajo
WORKDIR /app
# Copiar el JAR desde la etapa de compilation
COPY --from=builder /app/target/micro-cursos-0.0.1-SNAPSHOT.jar .
# Exponer el puerto
EXPOSE 8003
# Comando de inicio
ENTRYPOINT ["java", "-jar", "micro-cursos-0.0.1-SNAPSHOT.jar"]