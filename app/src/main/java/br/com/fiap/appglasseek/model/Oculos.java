package br.com.fiap.appglasseek.model;

import java.util.List;

public class Oculos {
    private String codigo;
    private String marca;
    private String modelo;
    private String tipo;
    private String genero;
    private String cor;
    private Double comprimento;
    private Double largura;
    private Double altura;
    private Double preco;
    private String material;
    private Integer imagem;
    private List<Integer> imagens;

    public Oculos(String codigo, String marca, String modelo, String tipo, String genero, String cor, Double comprimento, Double largura, Double altura, Double preco, String material, Integer imagem, List<Integer> imagens) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.genero = genero;
        this.cor = cor;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.preco = preco;
        this.material = material;
        this.imagem = imagem;
        this.imagens = imagens;
    }

    public Oculos(String codigo, String marca, String modelo, String tipo, String genero, String cor, Double comprimento, Double largura, Double altura, Double preco, String material, Integer imagem) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.genero = genero;
        this.cor = cor;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.preco = preco;
        this.material = material;
        this.imagem = imagem;
    }

    public Oculos(String codigo, String marca, String modelo, String tipo, String genero, String cor, Double comprimento, Double largura, Double altura, Double preco, String material) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.genero = genero;
        this.cor = cor;
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.preco = preco;
        this.material = material;
    }

    public Oculos() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getImagem() {
        return imagem;
    }

    public void setImagem(Integer imagem) {
        this.imagem = imagem;
    }

    public List<Integer> getImagens() {
        return imagens;
    }

    public void setImagens(List<Integer> imagens) {
        this.imagens = imagens;
    }
}
