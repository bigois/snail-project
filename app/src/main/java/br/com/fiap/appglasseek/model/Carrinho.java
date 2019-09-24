package br.com.fiap.appglasseek.model;

import java.util.List;

public class Carrinho {
    private List<Item> itens;

    public Carrinho() {
    }

    public Carrinho(List<Item> itens) {
        this.itens = itens;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
