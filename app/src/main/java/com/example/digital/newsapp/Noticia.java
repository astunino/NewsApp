package com.example.digital.newsapp;

public class Noticia {

    private String autor;
    private String titulo;
    private String descripcion;
    private String url;
    private String urlImagen;
    private String publicado;
    private String contenido;

    public Noticia(String autor, String titulo, String descripcion, String url, String urlImagen, String publicado, String contenido) {
        this.autor = autor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
        this.urlImagen = urlImagen;
        this.publicado = publicado;
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getpublicado() {
        return publicado;
    }

    public void setpublicado(String publicado) {
        this.publicado = publicado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
