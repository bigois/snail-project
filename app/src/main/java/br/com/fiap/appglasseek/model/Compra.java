package br.com.fiap.appglasseek.model;

import java.util.Date;
import java.util.List;

public class Compra {
    private String codigo;
    private Date data;
    private String meioPagamento;
    private Integer quantidade;
    private Double custoEnvio;
    private Double total;
    private List<Item> item;

    public Compra(String codigo, Date data, String meioPagamento, Integer quantidade, Double custoEnvio, Double total, List<Item> item) {
        this.codigo = codigo;
        this.data = data;
        this.meioPagamento = meioPagamento;
        this.quantidade = quantidade;
        this.custoEnvio = custoEnvio;
        this.total = total;
        this.item = item;
    }

    public Compra(String codigo, Date data, Double total) {
        this.codigo = codigo;
        this.data = data;
        this.total = total;
    }

    public Compra() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
