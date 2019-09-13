package br.com.fiap.appglasseek.model;

import java.util.List;

public class Usuario {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String status;
    private String senha;
    private List<Endereco> enderecos;
    private List<Cartao> cartoes;
    private Favorito favoritos;
    private Carrinho carrinho;
    private List<Compra> compras;


    public Usuario(String cpf, String nome, String sobrenome, String email, String telefone, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Usuario(String cpf, String nome, String sobrenome, String email, String telefone, String status, String senha, List<Endereco> enderecos, List<Cartao> cartoes, Favorito favoritos, Carrinho carrinho) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.senha = senha;
        this.enderecos = enderecos;
        this.cartoes = cartoes;
        this.favoritos = favoritos;
        this.carrinho = carrinho;
    }

    public Usuario() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public Favorito getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Favorito favoritos) {
        this.favoritos = favoritos;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
