package br.com.fiap.appglasseek.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.Model.Usuario;
import br.com.fiap.appglasseek.R;

public class RegistrarActivity extends AppCompatActivity {

    Usuario usuario;
    private Button btnVoltar;
    private Button btnCadastrar;
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario(txtNome.getText().toString(), txtSobrenome.getText().toString(), txtEmail.getText().toString(), txtSenha.getText().toString());

                Toast.makeText(RegistrarActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
