package br.com.fiap.appglasseek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Usuario;

public class RegistrarActivity extends AppCompatActivity {
    private Button btnVoltar;
    private Button btnCadastrar;
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtCpf;
    private EditText txtTelefone;
    private EditText txtEndereco;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNome = findViewById(R.id.txtNome);
        txtEndereco = findViewById(R.id.txtEndereco);
        txtCpf = findViewById(R.id.txtCpf);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario(txtCpf.getText().toString(), txtNome.getText().toString(), txtSobrenome.getText().toString(), txtEmail.getText().toString(), txtTelefone.getText().toString(), txtSenha.getText().toString());
                Toast.makeText(RegistrarActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarActivity.this.finish();
            }
        });

    }
}
