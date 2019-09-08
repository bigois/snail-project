package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.model.Usuario;
import br.com.fiap.appglasseek.R;

public class LoginActivity extends AppCompatActivity {
    private Usuario usuario;
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnVoltar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = new Usuario();

        btnVoltar = findViewById(R.id.btnVoltar);
        btnLogar = findViewById(R.id.btnLogar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    if (txtUsuario.getText().toString() == usuario.getEmail().toString() || txtSenha.getText().toString() == usuario.getSenha().toString()) {
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorreto.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
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
