# 💬 Foro Hub
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)

Proyecto en Spring Boot que implementa un sistema básico de foros, con usuarios, cursos y tópicos. Permite registrar, consultar, actualizar y eliminar tópicos mediante una API REST.

![Badge en Desarollo](https://img.shields.io/badge/STATUS-TERMINADO-green)

## 🚀 Requisitos previos
- Java 17 o superior
- Maven 3.9+
- MySQL 8

## 🛠️ Tecnologías utilizadas
- **Java** 21
- **Spring Boot** 3.5.4
- **Maven** (gestión de dependencias)
- **JPA/Hibernate** (persistencia)
- **MySQL** (base de datos)
- **FlyWay**(migraciones)

## ⚙️ Configuración
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/foro-hub.git
   cd foro-hub

2. Crear la base de datos en MySQL:
   ```bash
   create database foro_hub;
   

3. Configurar la conexión a la base de datos en src/main/resources/application.properties:
   ```bash
   spring.datasource.url=jdbc:mysql://localhost/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password

   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=validate

   spring.flyway.enabled=true

4. Compilar y ejecutar el proyecto:
   ```Bash
   mvn spring-boot:run

La aplicación quedará disponible en:

👉 [http://localhost:8080](http://localhost:8080)

## 📡 Endpoints principales

```http
GET    /topicos        → Listar todos los tópicos
POST   /topicos        → Crear un nuevo tópico
PUT    /topicos/{id}   → Actualizar un tópico existente
DELETE /topicos/{id}   → Eliminar un tópico
```
### 📌 Ejemplo de request para crear un tópico

```json
{
  "titulo": "Duda sobre JPA",
  "mensaje": "No entiendo cómo funciona la anotación @Entity",
  "idUsuario": 1,
  "idCurso": 2
}
