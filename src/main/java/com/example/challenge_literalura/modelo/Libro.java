package com.example.challenge_literalura.modelo;

import com.example.challenge_literalura.dto.LibroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
    private  Integer numeroDescargas;

    public Libro(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }


    public Libro(LibroDTO dto, Autor autor) {
        this.titulo = dto.titulo();
        this.autor = autor;
        this.idioma = dto.idioma().get(0);
        this.numeroDescargas = dto.numeroDescargas();
    }

    @Override
    public String toString() {
        return "📖 ---- LIBRO ----\n" +
                "📌 Titulo: " + titulo + "\n" +
                "👤 Autor: " + autor.getNombre() + "\n" +
                "🌍 Idioma: " + idioma + "\n" +
                "⬇️ Numero de descargas: " + numeroDescargas + "\n" +
                "----------------";
    }
}