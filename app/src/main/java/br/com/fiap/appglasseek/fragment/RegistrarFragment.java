package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Usuario;
import br.com.fiap.appglasseek.service.UserService;

public class RegistrarFragment extends Fragment {
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtCpf;
    private EditText txtTelefone;
    private EditText txtSenha;
    private Button btnEnderecos;
    private Button btnDeletarUsuario;
    private Button btnCadastrar;

    public RegistrarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Registrar");

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtNome = view.findViewById(R.id.txtNome);
        txtSobrenome = view.findViewById(R.id.txtSobrenome);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtCpf = view.findViewById(R.id.txtCpf);
        txtCpf.setEnabled(true);

        txtTelefone = view.findViewById(R.id.txtTelefone);
        txtSenha = view.findViewById(R.id.txtSenha);
        btnEnderecos = view.findViewById(R.id.btnEnderecos);
        btnDeletarUsuario = view.findViewById(R.id.btnDeletarUsuario);
        btnCadastrar = view.findViewById(R.id.btnAtualizar);

        btnEnderecos.setVisibility(View.INVISIBLE);
        btnEnderecos.setEnabled(false);

        btnDeletarUsuario.setVisibility(View.INVISIBLE);
        btnDeletarUsuario.setEnabled(false);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validaCampos()){
                    Usuario usuario = new Usuario();
                    usuario.setNome(txtNome.getText().toString());
                    usuario.setSobrenome(txtSobrenome.getText().toString());
                    usuario.setCpf(txtCpf.getText().toString());
                    usuario.setTelefone(txtTelefone.getText().toString());
                    usuario.setEmail(txtEmail.getText().toString());
                    usuario.setSenha(txtSenha.getText().toString());

                    UserService userService = new UserService(getContext(), "CREATE");
                    userService.execute(usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha());

                    getActivity().getSupportFragmentManager().popBackStack();
                    getActivity().getSupportFragmentManager().beginTransaction().remove(RegistrarFragment.this).commit();
                } else {
                    Toast.makeText(getContext(), "Campos obrigatórios não preenchidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public Boolean validaCampos(){
        Boolean valid = true;

        if (TextUtils.isEmpty(txtNome.getText().toString())) {
            txtNome.setError("Informe o nome!");
            valid = false;
        }
        if (TextUtils.isEmpty(txtSobrenome.getText().toString())) {
            txtSobrenome.setError("Informe o sobrenome!");
            valid = false;
        }
        if (TextUtils.isEmpty(txtCpf.getText().toString())) {
            txtCpf.setError("Informe o CPF!");
            valid = false;
        }
        if (TextUtils.isEmpty(txtTelefone.getText().toString())) {
            txtTelefone.setError("Informe a telefone!");
            valid = false;
        }
        if (TextUtils.isEmpty(txtEmail.getText().toString())) {
            txtEmail.setError("Informe o email!");
            valid = false;
        }
        if (TextUtils.isEmpty(txtSenha.getText().toString())) {
            txtSenha.setError("Informe a senha!");
            valid = false;
        }

        return valid;

    }
}
