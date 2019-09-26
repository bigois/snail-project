package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginService {
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
