package br.com.fiap.appglasseek.Model;

import android.media.Image;

public class Oculos {
    private String cod;
    private String marca;
    private String modelo;
    private String tipo;
    private String genero;
    private String cor;
    private Double comprimento;
    private Double largura;
    private Double altura;
    private String material;
    private Image imagem;

    public Oculos(String cod, String marca, String modelo, String tipo, String genero, String cor, Double comprimento, Double largura, Double altura, String material, Image imagem) {
        this.cod = cod;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.genero = genero;
        this.cor = cor;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.material = material;
        this.imagem = imagem;
    }

    public Oculos() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
