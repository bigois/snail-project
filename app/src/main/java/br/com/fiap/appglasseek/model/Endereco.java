package br.com.fiap.appglasseek.model;

public class Endereco {
    private String cep;
    private String destinatario;
    private String endereco;
    private String cidade;
    private String municipio;
    private String estado;
    private String complemento;
    private String telefone;

    public Endereco(String cep, String destinatario, String endereco, String cidade, String municipio, String estado, String complemento, String telefone) {
        this.cep = cep;
        this.destinatario = destinatario;
        this.endereco = endereco;
        this.cidade = cidade;
        this.municipio = municipio;
        this.estado = estado;
        this.complemento = complemento;
        this.telefone = telefone;
    }

    public Endereco(String cep, String destinatario, String endereco, String cidade, String municipio, String estado, String telefone) {
        this.cep = cep;
        this.destinatario = destinatario;
        this.endereco = endereco;
        this.cidade = cidade;
        this.municipio = municipio;
        this.estado = estado;
        this.telefone = telefone;
    }

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
