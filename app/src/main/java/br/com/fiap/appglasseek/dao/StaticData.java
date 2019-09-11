package br.com.fiap.appglasseek.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.model.Usuario;

public class StaticData {
    public static class OculosData {
        static List<Oculos> oculosList;

        public static List<Oculos> getOculosList() {
            if (OculosData.oculosList == null) {
                OculosData.oculosList = new ArrayList<Oculos>();

                for (Integer i = 0; i < 10; i++) {
                    oculosList.add(new Oculos(
                            "OC00000" + i.toString(), "Oakley", "Juliet", "Óculos de sol", "M", "Dourado", 50.00, 100.00, 5.00, 350.00, "Aço", R.drawable.oc_ray_ban));
                }
            }

            return OculosData.oculosList;
        }

        public static void setOculosList(List<Oculos> oculos) {
            OculosData.oculosList = oculos;
        }

        public static void addOculos(Oculos oculos) {
            OculosData.oculosList.add(oculos);
        }

        public static void removeOculos(Oculos oculos) {
            OculosData.oculosList.remove(oculos);
        }
    }

    public static class UserData {
        static Usuario usuario;

        public static Usuario getUsuario() {
            if (UserData.usuario == null) {
                UserData.usuario = new Usuario();
            }

            return UserData.usuario;
        }

        public static void setUsuario(Usuario usuario) {
            UserData.usuario = usuario;
        }

        public static void resetUsuario() {
            UserData.usuario = new Usuario();
        }
    }
}
