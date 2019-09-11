package br.com.fiap.appglasseek.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.model.Usuario;

public class StaticData {
    public static class OculosData {
        static List<Oculos> oculosList;
        static String idCodigo;

        public static List<Oculos> getOculosList() {
            if (OculosData.oculosList == null) {
                OculosData.oculosList = new ArrayList<Oculos>();

                oculosList.add(new Oculos(
                        idCodigo, "Oakley", "Juliet", "Óculos de sol", "M", "Dourado", 15.20, 12.00, 5.00, 349.90, "Aço", R.drawable.juliet));
                oculosList.add(new Oculos(
                        idCodigo, "Ray Ban", "Round Metal", "Óculos de sol", "U", "Dourado", 14.50, 13.20, 4.20, 449.90, "Metal", R.drawable.oc_ray_ban));
                oculosList.add(new Oculos(
                        idCodigo, "Track & Field", "Pipa", "Óculos de sol", "F", "Transparente", 13.15, 10.00, 5.10, 260.90, "Acrílico", R.drawable.oc_tf_pipa));
                oculosList.add(new Oculos(
                        idCodigo, "Prada", "Millennials", "Óculos de grau", "F", "Tartaruga", 15.00, 13.00, 5.45, 749.90, "Termoplástico", R.drawable.oc_prada_millennials));
                oculosList.add(new Oculos(
                        idCodigo, "Lema21", "Otto", "Óculos de grau", "U", "Dourado", 14.20, 14.25, 6.10, 142.29, "Aço", R.drawable.oc_lema21_otto));

                for(Integer i = 0; i < oculosList.size(); i++) {
                    idCodigo = "OC00000" + i.toString();
                }
                /*
                for (Integer i = 0; i < 10; i++) {
                    oculosList.add(new Oculos(
                            "OC00000" + i.toString(), "Oakley", "Juliet", "Óculos de sol", "M", "Dourado", 50.00, 100.00, 5.00, 350.00, "Aço", R.drawable.oc_ray_ban));
                }
                 */

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
