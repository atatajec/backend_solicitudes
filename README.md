# 📦 API de Solicitudes - WebFlux Reactive Service

Proyecto backend desarrollado con **Spring Boot 3.5** y arquitectura limpia para la gestión de solicitudes, marcas, tipos de solicitud y contactos. Totalmente reactivo con soporte para migraciones, documentación OpenAPI y PostgreSQL como base de datos principal.

---

## 🚀 Tecnologías

- ⚙️ **Java 17**
- ⚡ **Spring Boot 3.5**
- 🔁 **Spring WebFlux** (API 100% reactiva)
- 🔌 **Spring Data R2DBC** (reactividad con PostgreSQL)
- 🐘 **PostgreSQL**
- 🛠️ **Flyway** (migraciones automáticas de base de datos)
- 📚 **Swagger / OpenAPI 3** (documentación interactiva)
- 🔄 **MapStruct** (transformación DTO ↔ entidad)
- 💡 **Lombok** (boilerplate reduction)

---

## 🧱 Estructura del proyecto

```plaintext
src/
├── domain/                        # Modelo del negocio y abstracciones
│   ├── models/                    # Entidades del dominio
│   └── repositories/             # Interfaces de persistencia (puertos)
├── application/                  
│   └── usecases/                 # Lógica de aplicación / casos de uso
├── infrastructure/              
│   ├── adapters/                
│   │   ├── inbound/              # Entradas: controladores HTTP (WebFlux)
│   │   └── outbound/             # Salidas: implementación de repositorios
│   └── config/                  # Configuraciones (Beans, Swagger, R2DBC, etc.)
├── interfaces/                  # DTOs, mappers, controladores públicos (API)

📘 Swagger UI:
👉 http://217.71.200.99:8086/webjars/swagger-ui/index.html#/