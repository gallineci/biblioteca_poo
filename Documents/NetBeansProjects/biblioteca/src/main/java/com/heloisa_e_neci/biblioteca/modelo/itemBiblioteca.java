package com.heloisa_e_neci.biblioteca.modelo;

public class itemBiblioteca {
   private int id;
   private String titulo;
   private String autor;
   private String editora;
   private String anoPublicacao;
   
   public itemBiblioteca(int id, String titulo, String autor, String editora,String anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
     public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    // Método abstrato toString, que será sobrescrito nas subclasses
    //@Override
   // public abstract void aplicarOperacao();
}
