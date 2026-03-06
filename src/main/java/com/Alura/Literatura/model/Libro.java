package com.Alura.Literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String autor;
    private String idioma;
    private Double numeroDeDescargas;


    public Libro() {
    }


    public Libro(String titulo, String autor, String idioma, Double numeroDeDescargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();

        if (datosLibro.idiomas() != null && !datosLibro.idiomas().isEmpty()) {
            this.idioma = datosLibro.idiomas().get(0);
        }
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autor = datosLibro.autor().get(0).nombre();
        }
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "---------------- LIBRO ----------------" + "\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Descargas: " + numeroDeDescargas + "\n" +
                "---------------------------------------";
    }


}