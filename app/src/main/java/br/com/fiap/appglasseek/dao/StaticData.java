package br.com.fiap.appglasseek.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Compra;
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Item;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.model.Usuario;

public class StaticData {
    public static class OculosData {
        public static List<Integer> imageList;
        static List<Oculos> oculosList;

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

        public static Oculos getOculosByCodigo(String codigo) {
            Oculos oculos = new Oculos();

            for (Oculos oculosLista : oculosList) {
                if (oculosLista.getCodigo().equals(codigo)) {
                    oculos = oculosLista;
                }
            }

            return oculos;
        }
    }

    public static class UserData {
        static Usuario usuario;
        static Carrinho carrinho;
        static Favorito favorito;
        static List<Compra> compras;

        public static Favorito getFavorito() {
            instanciarFavoritosList();
            return favorito;
        }

        public static void setFavorito(Favorito favorito) {
            UserData.favorito = favorito;
        }

        public static void instanciarFavoritosList() {
            if (null == favorito) UserData.favorito = new Favorito();
        }

        public static Usuario getUsuario() {
            instanciarUsuario();
            return UserData.usuario;
        }

        public static void setUsuario(Usuario usuario) {
            UserData.usuario = usuario;
        }

        public static void resetUsuario() {
            UserData.usuario = new Usuario();
        }

        public static void instanciarUsuario() {
            if (null == UserData.usuario) UserData.usuario = new Usuario();
        }

        public static Carrinho getCarrinho() {
            instanciarCarrinhoSeNull();
            return carrinho;
        }

        public static void setCarrinho(Carrinho carrinho) {
            UserData.carrinho = carrinho;
        }

        public static void instanciarCarrinhoSeNull() {
            if (null == carrinho) UserData.carrinho = new Carrinho();
        }

        public static boolean oculosExisteNoCarrinho(Oculos oculos) {
            instanciarCarrinhoSeNull();

            for (Item item : carrinho.getItens()) {
                if (oculos.equals(item.getOculos())) return true;
            }

            return false;
        }

        public static Double valorTotalCarrinho() {
            instanciarCarrinhoSeNull();
            Double valorCarrinho = 0.0;

            for (Item item : carrinho.getItens()) {
                valorCarrinho += item.getQuantidade() * item.getOculos().getPreco();
            }

            return valorCarrinho;
        }

        public static void setCompras(List<Compra> compras) {
            UserData.compras = compras;
        }

        public static List<Compra> getCompras() {
            instanciarComprasList();
            return compras;
        }

        private static void instanciarComprasList() {
            if (null == compras) UserData.compras = new ArrayList<>();
        }


    }
}
