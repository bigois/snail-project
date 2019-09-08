package br.com.fiap.appglasseek.Model;

import android.media.Image;

public class Oculos {
    private String id;
    private String nome;
    private String categoria;
    private String marca;
    private String cor;
    private Double preco;
    private Image imagemOculos;

    public Oculos(String id, String nome, String categoria, String marca, String cor, Double preco, Image imagemOculos) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.marca = marca;
        this.cor = cor;
        this.preco = preco;
        this.imagemOculos = imagemOculos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Image getImagemOculos() {
        return imagemOculos;
    }

    public void setImagemOculos(Image imagemOculos) {
        this.imagemOculos = imagemOculos;
    }
}
