package com.Alura.Literatura.principal;

import com.Alura.Literatura.model.Datos;
import com.Alura.Literatura.model.DatosLibro;
import com.Alura.Literatura.model.Libro;
import com.Alura.Literatura.repository.LibroRepository;
import com.Alura.Literatura.services.ConsumoAPI;
import com.Alura.Literatura.services.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                -------------------------------------------------
                📖 BIENVENIDO A LITERATURA-ALURA 📖
                -------------------------------------------------
                Por favor, elija el NÚMERO de la opción deseada:
                
                1 - Buscar libro por título (para guardar uno nuevo)
                2 - Listar todos los libros ya registrados
                3 - Listar autores registrados
                0 - Salir del programa
                -------------------------------------------------
                Escribe un número: """;

            System.out.print(menu);

            try {
                opcion = lectura.nextInt();
                lectura.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        mostrarLibrosRegistrados();
                        break;
                    case 3:
                       listarAutoresRegistrados();

                    case 0:
                        System.out.println("Cerrando la aplicación... ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Por favor, marca un número del menú.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ ERROR: Debes ingresar un NÚMERO, no el nombre del libro.");
                lectura.nextLine();
            }
        }
    }
    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = lectura.nextLine();

        String nombreFormateado = nombreLibro.toLowerCase().trim().replace(" ", "%20");

        System.out.println("Buscando en: " + URL_BASE + nombreFormateado);
        var json = consumoApi.obtenerDatos(URL_BASE + nombreFormateado);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && !datos.resultados().isEmpty()) {
            DatosLibro primerLibro = datos.resultados().get(0);
            Libro libro = new Libro(primerLibro);

            try {
                repositorio.save(libro);
                System.out.println("Alicero guardado con éxito!");
            } catch (Exception e) {
                System.out.println("⚠ Nota: El libro '" + libro.getTitulo() + "' ya existe en tu base de datos.");
            }
        } else {
            System.out.println("Lo siento, no encontré ese libro en la API.");
        }
    }

    private void mostrarLibrosRegistrados() {
        List<Libro> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados todavía.");
        } else {
            System.out.println("--- LIBROS REGISTRADOS ---");
            libros.forEach(System.out::println);
        }
    }
    private void listarAutoresRegistrados() {

        var libros = repositorio.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay autores registrados todavía.");
        } else {
            System.out.println("--- AUTORES REGISTRADOS ---");

            libros.stream()
                    .map(l -> l.getAutor())
                    .distinct()
                    .forEach(System.out::println);
        }
    }

}