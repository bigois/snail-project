package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Usuario;

public class LoginUtility {
    public static Boolean isLogged(Context context) {
        Boolean logged = false;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(     preferences.getBoolean("logged", false) == true
                && preferences.getString("senha", null) != null
                && preferences.getString("email", null) != null){
            logged = true;
        }

        return logged;
    }

    public static void logIn(Context context, String email,String senha) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.putString("senha",senha);
        editor.commit();
    }

    public static void logOut(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        StaticData.UserData.setUsuario(new Usuario());
        StaticData.UserData.setCarrinho(new Carrinho());
        StaticData.UserData.setFavorito(new Favorito());

        editor.putBoolean("logged", false);
        editor.remove("email");
        editor.remove("senha");
        editor.commit();
    }
}
