package br.com.fiap.appglasseek.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Item> itens;

    public Carrinho() {
    }

    public Carrinho(List<Item> itens) {
        this.itens = itens;
    }

    public List<Item> getItens() {
        if (null == itens) instanciarItensSeNull();
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void addItemToCarrinho(Item item) {
        instanciarItensSeNull();
        itens.add(item);
    }

    public void instanciarItensSeNull() {
        if (null == itens) itens = new ArrayList<Item>();
    }

    public void limpaCarrinho(List<Item> item) {
        this.itens.removeAll(item);
    }

}
