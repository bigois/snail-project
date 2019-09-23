package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.glasseek.Glasseek.UnityPlayerActivity;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.fragment.OculosFragment;


/**
 * Need to create a holder activity of Unity to solve problem when pressing back button
 */

public class UnityHolderActivity extends UnityPlayerActivity {
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

    public void onFinish(String modelo) {
        Log.d("I","ENTROU NA PORRA DO onFinish");
        Intent resultIntent = new Intent(this, OculosFragment.class);
        resultIntent.putExtra("oculos", modelo);
        startActivity(resultIntent);
    }

    public void setOculos(String oculos) {
        this.oculos = oculos;
    }
}
