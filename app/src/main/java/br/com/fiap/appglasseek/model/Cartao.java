package br.com.fiap.appglasseek.model;

public class Cartao {
    private String numero;
    private String nome;
    private String validade;
    private String tipo;

    public Cartao(String numero, String nome, String validade, String tipo) {
        this.numero = numero;
        this.nome = nome;
        this.validade = validade;
        this.tipo = tipo;
    }

    public Cartao() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
