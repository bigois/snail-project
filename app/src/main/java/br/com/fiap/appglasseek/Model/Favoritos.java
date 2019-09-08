package br.com.fiap.appglasseek.Model;

import java.util.List;

public class Favoritos {
    private List<Oculos> oculos;

    public Favoritos(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    public Favoritos() {
    }

    public List<Oculos> getOculos() {
        return oculos;
    }

    public void setOculos(List<Oculos> oculos) {
        this.oculos = oculos;
    }
}
