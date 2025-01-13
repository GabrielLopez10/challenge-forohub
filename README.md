<p align="center">
  <img src="https://res.cloudinary.com/dfzw74nlk/image/upload/v1736709213/forohub/v0k2xoxfqsza4zm72j17.png" alt="ForoHub Banner">
</p>

# ForoHub API 
**ForoHub API** es una aplicación backend desarrollada en **Java** con **Spring Boot**, que permite
la gestión de tópicos, respuestas y usuarios con roles y autenticación basada en tokens JWT.

# Índice
* [Características](#características-)
* [Tecnologías utilizadas](#tecnologías-utilizadas-)
* [Configuración](#configuración-)

## Características ✨
- Gestión de usuarios con perfiles (**ADMIN, USER**).
- CRUD para tópicos, cursos, usuarios y respuestas.
- Autenticación y autorización con JWT.
- Persistencia de datos mediante **Flyway** y bases de datos **MariaDB**
- Pruebas automatizadas para endpoints críticos usando **JUnit** y **MockMvc**

## Tecnologías utilizadas 🛠️
- **Lenguaje:** Java 17 o superior.
- **Framework:** Spring Boot
    - Spring Data JPA
- **Autenticación y Autorización**: Spring Security, JWT, Bcrypt.
- **Documentación**: Swagger/OpenAPI
- **Base de Datos:** MariaDB.
- **Postman** o **Insomnia** para probar los endpoints.


## Configuración 🚀

### Paso 1: Clonar el repositorio

Clona el repositorio en tu máquina local:

```
git clone https://github.com/GabrielLopez10/challenge-forohub.git
cd challenge-forohub
```

### Paso 2: Configurar la base de datos

Crea una base de datos MySQL o MariaDB llamada `forohub_api` (o el nombre que prefieras).
Luego, configura las credenciales en el archivo `application.properties` ubicado en `src/main/resources`:

```
spring.application.name=api
spring.datasource.url=jdbc:mysql://${DB_HOST}/forohub_api
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDiale


spring.flyway.enabled=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-stacktrace=never
spring.flyway.locations=classpath:db/migration


api.security.secret=${JWT_SECRET:root}
```
Las variables de entorno están configuradas en el propio Intellij, si lo prefieres podés incluirla
en el propio sistema.

Otra de manera es crear un archivo `application-prod.properties` con las siguientes
credenciales

```
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}

spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```
Ejecutar en la terminal
```
java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://localhost:3306/forohub_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=root -jar target/api-0.0.1-SNAPSHOT.jar
```
También hay un archivo properties para tests:
```
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_api_test

spring.flyway.locations=classpath:db/migration
```
### Paso 3: Compilar y ejecutar el proyecto en tu terminal ▶️

Ejecutar el siguiente comando para empaquetar y ejecutar la aplicación en un solo paso:

```
mvn spring-boot:run
```

o si lo prefieres podés ejecutarlo directo en el IntelliJ.