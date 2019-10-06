package br.com.fiap.appglasseek.model;

public class Item {
    private Oculos oculos;
    private Integer quantidade;
    private Double total;

    private Integer parcelas;
    private Double custoEnvio;

    public Item(Oculos oculos, Integer quantidade, Double total) {
        this.oculos = oculos;
        this.quantidade = quantidade;
        this.total = total;
    }

    public Item(Oculos oculos, Integer parcelas, Double custoEnvio, Double total, Integer quantidade) {
        this.oculos = oculos;
        this.parcelas = parcelas;
        this.custoEnvio = custoEnvio;
        this.total = total;
        this.quantidade = quantidade;
    }

    public Item(Oculos oculos, Integer quantidade) {
        this.oculos = oculos;
        this.quantidade = quantidade;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
