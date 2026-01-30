package com.example.challenge_literalura.modelo;

import com.example.challenge_literalura.dto.AutorDTO;
import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;


    public Autor(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(AutorDTO dto) {
        this.nombre = dto.nombre();
        this.anioNacimiento = dto.anioNacimiento();
        this.anioFallecimiento = dto.anioFallecimiento();
    }

    @Override
    public String toString() {
        return "👤 Autor: " + nombre + "\n" +
                "🎂 Fecha de nacimiento: " + anioNacimiento + "\n" +
                "⚰️ Fecha de fallecimiento: " + (anioFallecimiento != null ? anioFallecimiento : "-") +"\n" +
                "📚 Libros: " + libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "))+  "\n" +
                "----------------";
    }
}