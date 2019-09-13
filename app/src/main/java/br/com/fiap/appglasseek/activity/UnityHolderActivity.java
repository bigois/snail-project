package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.glasseek.Glasseek.UnityPlayerActivity;

import br.com.fiap.appglasseek.R;


/**
 * Need to create a holder activity of Unity to solve problem when pressing back button
 */

public class UnityHolderActivity extends AppCompatActivity {
    static String oculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = new Intent(this, UnityPlayerActivity.class);
//        String message = intent.getStringExtra("message");
//        message = "hardcoded sujo batata";
        intent.putExtra("arguments", oculos);
        startActivity(intent);

    }

    public void setOculos(String oculos) {
        this.oculos = oculos;
    }
}
