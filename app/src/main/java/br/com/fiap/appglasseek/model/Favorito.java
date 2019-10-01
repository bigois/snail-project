package br.com.fiap.appglasseek.model;

import java.util.ArrayList;
import java.util.List;

public class Favorito {
    public Favorito() {
    }

    private List<Oculos> oculos;

    public Favorito(List<Oculos> oculos) {
        this.oculos = oculos;
    }

    public List<Oculos> getOculos() {
        instanciarOculosList();
        return oculos;
    }

    private void instanciarOculosList() {
        if (null == oculos) {
            oculos = new ArrayList<>();
        }
    }

    public void setOculos(List<Oculos> oculos) {
        this.oculos = oculos;
    }

}
