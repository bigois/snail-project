package br.com.fiap.appglasseek.model;

import java.util.ArrayList;
import java.util.List;

public class Favorito {
    private List<Oculos> oculos;

    public Favorito() {
    }

    public Favorito(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    public List<Oculos> getOculos() {
        instanciarOculosList();
        return oculos;
    }

    public void setOculos(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    private void instanciarOculosList() {
        if (null == oculos) {
            oculos = new ArrayList<>();
        }
    }

}
