package br.com.fiap.appglasseek.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
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
        txtTelefone = view.findViewById(R.id.txtTelefone);
        txtSenha = view.findViewById(R.id.txtSenha);
        btnEnderecos = view.findViewById(R.id.btnEnderecos);
        btnDeletarUsuario = view.findViewById(R.id.btnDeletarUsuario);
        btnCadastrar = view.findViewById(R.id.btnAtualizar);

        btnEnderecos.setVisibility(View.INVISIBLE);
        btnEnderecos.setEnabled(false);
//        btnEnderecos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Usuário deletado, você foi desconectado!", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnDeletarUsuario.setVisibility(View.INVISIBLE);
        btnDeletarUsuario.setEnabled(false);
//        btnDeletarUsuario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new AlertDialog.Builder(getContext())
//                        .setMessage("Deseja realmente deletar seu perfil?")
//                        .setCancelable(false)
//                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                UserService userService = new UserService(getContext(), "DELETE");
//                                userService.execute();
//                                getActivity().getSupportFragmentManager().popBackStack();
//                            }
//                        })
//                        .setNegativeButton("Não", null)
//                        .show();
//            }
//        });

        btnCadastrar.setText("Cadastrar");


        return view;
    }
}
