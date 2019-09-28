package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.glasseek.Glasseek.UnityPlayerActivity;

import br.com.fiap.appglasseek.R;

public class UnityHolderActivity extends AppCompatActivity {
    static String oculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("arguments", oculos);

        startActivity(intent);
    }

//    public void onFinish(String modelo) {
//        Intent resultIntent = new Intent(this, OculosFragment.class);
//        resultIntent.putExtra("oculos", modelo);
//
//        startActivity(resultIntent);
//    }
//
    public void setOculos(String oculos) {
        UnityHolderActivity.oculos = oculos;
    }
}
