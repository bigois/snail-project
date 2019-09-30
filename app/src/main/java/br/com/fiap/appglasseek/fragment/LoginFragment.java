package br.com.fiap.appglasseek.fragment;

import android.content.Intent;
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
import br.com.fiap.appglasseek.activity.RegistrarActivity;
import br.com.fiap.appglasseek.service.UserService;
import br.com.fiap.appglasseek.utility.TransportView;

public class LoginFragment extends Fragment {
    private EditText txtUsuario;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnVoltar;
    private Button btnRegistrar;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Login");

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogar = view.findViewById(R.id.btnLogar);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);
        btnVoltar = view.findViewById(R.id.btnVoltar);
        txtUsuario = view.findViewById(R.id.txtUsuario);
        txtSenha = view.findViewById(R.id.txtSenha);

        Toast.makeText(getContext(), "Você não está conectado!\nEntre para mais detalhes", Toast.LENGTH_LONG).show();

        txtUsuario.setText("batata@outlook.com");
        txtSenha.setText("batata");

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
                    Toast.makeText(getContext(), "Campos obrigatórios não preenchidos", Toast.LENGTH_SHORT).show();
                } else {
                    TransportView.Transport.setActivity(getActivity());
                    TransportView.Transport.setContext(getContext());
                    TransportView.Transport.setFragmentManager(getFragmentManager());
                    TransportView.Transport.setFragment(LoginFragment.this);

                    UserService userService = new UserService(getContext(), "GET");
                    userService.execute(email, senha);

                    getActivity().getSupportFragmentManager().popBackStack();

                    //getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new InicioFragment(), "LoginFragment").commit();

                    //getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new PerfilFragment(), "PerfilFragment").commit();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new RegistrarFragment(), "LoginFragment").commit();

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
