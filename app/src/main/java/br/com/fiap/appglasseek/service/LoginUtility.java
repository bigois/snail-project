package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import br.com.fiap.appglasseek.model.Usuario;

public class UsuarioService {
    static final String IP_ADDRESS = "192.168.1.139";
    static final String URL = "http://" + IP_ADDRESS + ":6085/rest/00User";

    public static Boolean isLogged(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("logged", false);
    }

    public static void logIn(Context context, String email) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.commit();
    }

    public static void logOut(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("logged", false);
        editor.remove("email");
        editor.commit();
    }

    public static Boolean authUser(String email, String senha) {
        Boolean success = false;

        if (email.equals("teste@teste") && senha.equals("senha")) {
            success = true;
        }

        return success;
    }
}