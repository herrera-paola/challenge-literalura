# 📚 Challenge Literalura

Proyecto desarrollado como parte del challenge de Alura Latam.
La aplicación permite consultar libros y autores utilizando la API pública de Gutendex,
guardar la información en una base de datos y realizar consultas desde consola.

---

## 🚀 Funcionalidades

- 🔍 Buscar libro por título (API Gutendex)
- 📚 Listar libros registrados
- 👤 Listar autores registrados
- 🕰️ Listar autores vivos en un determinado año
- 🌍 Listar libros por idioma
- 📊 Mostrar cantidad de libros por idioma

---

## 🧠 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- API Gutendex
- Maven

---

## 🗄️ Base de datos

El proyecto utiliza **PostgreSQL** como base de datos relacional.

Tablas principales:
- `libro`
- `autor`

Relación:
- Un autor puede tener varios libros
- Un libro pertenece a un autor

---

## ✨ Posibles mejoras

El proyecto puede ser extendido con las siguientes funcionalidades adicionales:

- 📊 Generar estadísticas a partir de los libros almacenados (promedio, máximo y mínimo de descargas).
- 🔝 Mostrar el Top 10 de libros más descargados.
- 🔍 Implementar la búsqueda de autores por nombre desde la base de datos.
- 📅 Incorporar consultas adicionales sobre autores según año de nacimiento o fallecimiento.

## 👩‍💻 Autor/a

Paola Herrera  
Challenge Alura Latam – Backend Java
