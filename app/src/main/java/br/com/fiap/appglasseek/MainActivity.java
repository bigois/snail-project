package br.com.fiap.appglasseek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.Model.Usuario;

public class MainActivity extends AppCompatActivity {

    Usuario usuario;
    private Button btnPular;
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPular = (Button) findViewById(R.id.btnPular);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    if (txtUsuario.getText().toString() == usuario.getEmail().toString() || txtSenha.getText().toString() == usuario.getSenha().toString()) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Usuário ou senha incorreto.", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        btnPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean validateForm() {
        if (txtUsuario.getText() == null || txtUsuario.getText().toString().equals("") || txtUsuario.getText().toString().length() < 4) {
            txtUsuario.setError("Campo precisa de pelo menos 4 caracteres");
            return false;
        }
        return true;
    }
}
