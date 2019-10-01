package br.com.fiap.appglasseek.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginUtility {
    public static Boolean isLogged(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("logged", false);
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

        editor.putBoolean("logged", false);
        editor.remove("email");
        editor.remove("senha");
        editor.commit();
    }
}
