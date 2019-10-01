package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.service.LoginUtility;
import br.com.fiap.appglasseek.service.OculosService;
import br.com.fiap.appglasseek.service.UserService;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        OculosService oculosService = new OculosService(SplashActivity.this,"GET");
        oculosService.execute();

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);

                if(LoginUtility.isLogged(getApplicationContext())){
                    UserService userService = new UserService(SplashActivity.this, "GET");

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();

                    userService.execute(preferences.getString("email",null), preferences.getString("senha",null));
                }
                finish();
            }
        }, 1500);
    }
}
