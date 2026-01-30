package com.example.challenge_literalura.principal;

import com.example.challenge_literalura.dto.AutorDTO;
import com.example.challenge_literalura.dto.LibroDTO;
import com.example.challenge_literalura.dto.ResultadoBusquedaDTO;
import com.example.challenge_literalura.modelo.Autor;
import com.example.challenge_literalura.modelo.Libro;
import com.example.challenge_literalura.repository.AutorRepository;
import com.example.challenge_literalura.repository.LibroRepository;
import com.example.challenge_literalura.service.ConsumoAPI;
import com.example.challenge_literalura.service.ConvierteDatos;
import org.springframework.stereotype.Component;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    📖 Menú de opciones
                    ────────────────────
                    🔍 1- Buscar libro por título
                    📚 2- Listar libros registrados
                    👤 3- Listar autores registrados
                    🕰️ 4- Autores vivos en determinado año
                    🌍 5- Listar libros por idioma
                    🚪 0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("👋 Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        }
    }

    //Opción: 1
    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        String terminoBusqueda = URLEncoder.encode(nombreLibro, StandardCharsets.UTF_8);
        var json = consumoAPI.obtenerDatos(URL_BASE +  "?search="+ terminoBusqueda);
        System.out.println("Este es la url"+ json);

        ResultadoBusquedaDTO resultadoBusqueda = conversor.obtenerDatos(json, ResultadoBusquedaDTO.class);

        if (resultadoBusqueda.resultadoLibros().isEmpty()) {
            System.out.println("No se encontró el libro");
            return;
        }
        LibroDTO libroDTO = resultadoBusqueda.resultadoLibros().get(0);
        AutorDTO autorDTO = libroDTO.autor().get(0);

        Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(libroDTO.titulo());
        if (libroExistente.isPresent()){
            System.out.println("⚠️ El libro ya fue registrado. No se puede ingresar más de una vez.");
            return;
        }

        Autor autor = autorRepository.findByNombreContainsIgnoreCase(autorDTO.nombre())
                .orElseGet(() -> autorRepository.save(new Autor(autorDTO)));

        Libro libro = libroRepository.findByTituloIgnoreCase(libroDTO.titulo())
                .orElseGet(() -> {
                    Libro nuevoLibro = new Libro(libroDTO,autor);
                    nuevoLibro.setAutor(autor);
                    return libroRepository.save(nuevoLibro);
                });
        System.out.println("✅ Libro registrado correctamente:");
        System.out.println(libro);
    }

    //Opción: 2
    public void listarLibrosRegistrados(){
        libros = libroRepository.findAll();
        if (libros.isEmpty()){
            System.out.println("📭 No hay libros registrados.");
            return;
        }
        libros.forEach(System.out::println);
    }

    //Opción: 3
    public void listarAutoresRegistrados(){
        autores = autorRepository.findAll();
        if (autores.isEmpty()){
            System.out.println("📭 No hay autores registrados.");
            return;
        }
        autores.forEach(System.out::println);
    }

    //Opción: 4
    public void listarAutoresVivosEnDeterminadoAnio(){
        System.out.println("Ingrese el año para consultar autor(es) vivos: ");
        Integer anioIngresado = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresFiltrados = autorRepository.autoresVivosEnAnio(anioIngresado);

        if (autoresFiltrados.isEmpty()){
            System.out.println("📭 No se encontraron autores vivos en el año: " + anioIngresado);
        } else {
            autoresFiltrados.forEach(System.out::println);
        }
    }

//    public void listarAutoresVivosEnDeterminadoAnio(){
//        System.out.println("Ingrese el año para consultar autor(es) vivos: ");
//        Integer anioIngresado = teclado.nextInt();
//        teclado.nextLine();
//
//        List<Autor> autoresVivos = new ArrayList<>();
//        autoresVivos.addAll(autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anioIngresado,anioIngresado));
//        autoresVivos.addAll(autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoIsNull(anioIngresado));
//
//
//        if (autoresVivos.isEmpty()){
//            System.out.println("📭 No se encontraron autores vivos en el año: " + anioIngresado);
//        } else {
//            autoresVivos.forEach(System.out::println);
//        }
//    }

    //Opción: 5
    public void listarLibrosPorIdioma(){
        System.out.println("""
            🌍 Idiomas disponibles:
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """);
        System.out.println("Ingrese el idioma: ");
        String idioma = teclado.nextLine().toLowerCase();

        libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("📭 No hay libros en ese idioma.");
            return;
        }
        libros.forEach(System.out::println);
    }

}
