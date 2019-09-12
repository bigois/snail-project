package br.com.fiap.appglasseek.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.model.Usuario;

public class StaticData {
    public static class OculosData {
        static List<Oculos> oculosList;
        static List<Integer> imageList;

        public static List<Oculos> getOculosList() {
            if (OculosData.oculosList == null) {
                OculosData.oculosList = new ArrayList<Oculos>();
                OculosData.imageList = new ArrayList<Integer>();

                imageList.add(R.drawable.juliet1);
                imageList.add(R.drawable.juliet2);
                imageList.add(R.drawable.juliet3);

                oculosList.add(new Oculos(
                        "OC000001", "Oakley", "Juliet", "Óculos de sol", "M", "Dourado", 15.20, 12.00, 5.00, 349.90, "Aço", R.drawable.juliet, imageList));
                oculosList.add(new Oculos(
                        "OC000002", "Ray Ban", "Round Metal", "Óculos de sol", "U", "Dourado", 14.50, 13.20, 4.20, 449.90, "Metal", R.drawable.oc_ray_ban, imageList));
                oculosList.add(new Oculos(
                        "OC000003", "Track & Field", "Pipa", "Óculos de sol", "F", "Transparente", 13.15, 10.00, 5.10, 260.90, "Acrílico", R.drawable.oc_tf_pipa, imageList));
                oculosList.add(new Oculos(
                        "OC000004", "Prada", "Millennials", "Óculos de grau", "F", "Tartaruga", 15.00, 13.00, 5.45, 749.90, "Termoplástico", R.drawable.oc_prada_millennials, imageList));
                oculosList.add(new Oculos(
                        "OC000005", "Lema21", "Otto", "Óculos de grau", "U", "Dourado", 14.20, 14.25, 6.10, 142.29, "Aço", R.drawable.oc_lema21_otto, imageList));
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
