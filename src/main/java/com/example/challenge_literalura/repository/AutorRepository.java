package com.example.challenge_literalura.repository;

import com.example.challenge_literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("""
       SELECT a FROM Autor a
       WHERE a.anioNacimiento <= :anio
       AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)
       """)
    List<Autor> autoresVivosEnAnio(@Param("anio") Integer anio);


    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(
            Integer nacimiento, Integer fallecimiento
    );
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoIsNull(
            Integer nacimiento
    );

}

