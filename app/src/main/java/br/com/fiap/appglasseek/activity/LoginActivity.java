package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.service.LoginUtility;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnVoltar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogar = findViewById(R.id.btnLogar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);

        Toast.makeText(LoginActivity.this, "Você não está conectado!\nEntre para mais detalhes", Toast.LENGTH_LONG).show();

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();
                Boolean valid = true;

                if (TextUtils.isEmpty(email)) {
                    txtUsuario.setError("Informe o endereço de e-mail");
                    valid = false;
                }

                if (TextUtils.isEmpty(senha)) {
                    txtSenha.setError("O campo senha é obrigatório");
                    valid = false;
                }

                if (!valid) {
                    Toast.makeText(LoginActivity.this, "Campos obrigatórios não preenchidos", Toast.LENGTH_SHORT).show();
                } else {
                    if (LoginUtility.authUser(email, senha)) {
                        LoginUtility.logIn(LoginActivity.this.getApplicationContext(), email, senha);
                        // Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                        // LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
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
}
