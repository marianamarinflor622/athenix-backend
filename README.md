# 🛠️ Athenyx - Backend

Este backend forma parte del proyecto **Athenyx**, una plataforma educativa y visual construida con Spring Boot y PostgreSQL. Su objetivo es manejar recursos multimedia, autenticación administrativa y proporcionar una API RESTful segura y estructurada.

---

## 🚀 Tecnologías Utilizadas

| Tecnología      | Versión |
| --------------- | ------- |
| Java            | 21      |
| Spring Boot     | 3+      |
| Maven           | 4+      |
| PostgreSQL      | 15      |
| Spring Security | ✅       |
| Spring Web      | ✅       |
| Spring Data JPA | ✅       |

---

## 📂 Estructura del Proyecto (resumen)

```
athenyx-backend/
├── controller/           # Controladores REST (Content, Comment, Admin)
├── service/              # Lógica de negocio
├── model/                # Entidades JPA
├── repository/           # Repositorios Spring Data
├── security/             # Configuración de seguridad manual
├── config/               # Configuraciones como CORS
└── AthenyxApplication.java
```

---

## 📡 Endpoints Principales

### 🔐 Login Administrador

* `POST /api/admin/login` → recibe usuario y contraseña (valida contra credenciales .env)

### 📁 Recursos

* `GET /api/resources` → lista todos los recursos
* `GET /api/resources/{id}` → obtiene un recurso por ID
* `POST /api/resources` → crea un nuevo recurso (usa `multipart/form-data`)
* `GET /api/resources/download/{filename}` → descarga el archivo

### 💬 Comentarios

* `GET /api/comments/{resourceId}` → comentarios por recurso
* `POST /api/comments/{resourceId}` → nuevo comentario

---

## 🛡️ Seguridad Aplicada

* Login con credenciales ocultas en `.env`
* Inputs sanitizados en frontend y validados
* Spring Security con `InMemoryUserDetailsManager`
* Endpoints protegidos vía rutas
* CORS habilitado solo para el frontend (localhost:5173)
* Subida de archivos en ruta segura (`file.upload-dir`)

---

## ⚙️ Configuración .env (ejemplo)

```
SECURITY_USER_NAME=adminuser
SECURITY_USER_PASSWORD=adminpass
```

---

## ▶️ Ejecución del Backend

```bash
cd athenyx-backend
./mvnw spring-boot:run
```

> Asegúrate de tener PostgreSQL corriendo y configurado en `application.properties`

---

## 👩‍💻 Autora

**Mariana Marín Flor**
Desarrolladora Fullstack Junior | Enfoque en accesibilidad, inclusión y proyectos con impacto.

* 💼 [LinkedIn](https://www.linkedin.com/in/mariana-marin-1b6268348/)
* 🐙 [GitHub](https://github.com/marianamarinflor622)

---

## 📄 Licencia

MIT License

Copyright (c) 2025 Mariana Marín Flor

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
