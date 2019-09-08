package br.com.fiap.appglasseek.model;

import java.util.List;

public class Carrinho {
    private List<Oculos> oculos;

    public Carrinho(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    public Carrinho() {
    }

    public List<Oculos> getOculos() {
        return oculos;
    }

    public void setOculos(List<Oculos> oculos) {
        this.oculos = oculos;
    }
}
