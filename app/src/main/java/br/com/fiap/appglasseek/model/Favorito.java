package br.com.fiap.appglasseek.model;

import java.util.List;

public class Favorito {
    private List<Oculos> oculos;

    public Favorito(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    public Favorito() {
    }

    public List<Oculos> getOculos() {
        return oculos;
    }

    public void setOculos(List<Oculos> oculos) {
        this.oculos = oculos;
    }
}
