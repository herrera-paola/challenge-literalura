package com.example.challenge_literalura.principal;

import com.example.challenge_literalura.dto.AutorDTO;
import com.example.challenge_literalura.service.ConsumoAPI;
import com.example.challenge_literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private final  String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu() {
        var opcion = -1;
        while(opcion != 0){
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

            switch (opcion){
                case 0 :
                    System.out.println("👋 Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        }
    }

}
