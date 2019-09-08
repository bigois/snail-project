package br.com.fiap.appglasseek.model;

public class Item {
    private Oculos oculos;
    private Integer parcelas;
    private Double custoEnvio;
    private Double preco;
    private Double total;

    public Item(Oculos oculos, Integer parcelas, Double custoEnvio, Double preco, Double total) {
        this.oculos = oculos;
        this.parcelas = parcelas;
        this.custoEnvio = custoEnvio;
        this.preco = preco;
        this.total = total;
    }

    public Item() {
    }

    public Oculos getOculos() {
        return oculos;
    }

    public void setOculos(Oculos oculos) {
        this.oculos = oculos;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getCustoEnvio() {
        return custoEnvio;
    }

    public void setCustoEnvio(Double custoEnvio) {
        this.custoEnvio = custoEnvio;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
