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

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Usuario;
import br.com.fiap.appglasseek.service.AddressService;
import br.com.fiap.appglasseek.service.LoginUtility;
import br.com.fiap.appglasseek.service.UserService;

public class PerfilFragment extends Fragment {
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtCpf;
    private EditText txtTelefone;
    private EditText txtSenha;
    private Button btnEnderecos;
    private Button btnDeletarUsuario;
    private Button btnAtualizar;
    private Button btnRegistrar;

    public PerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Perfil");
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        txtNome = view.findViewById(R.id.txtNome);
        txtSobrenome = view.findViewById(R.id.txtSobrenome);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtCpf = view.findViewById(R.id.txtCpf);
        txtTelefone = view.findViewById(R.id.txtTelefone);
        txtSenha = view.findViewById(R.id.txtSenha);
        btnEnderecos = view.findViewById(R.id.btnEnderecos);
        btnDeletarUsuario = view.findViewById(R.id.btnDeletarUsuario);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);

        txtNome.setText(StaticData.UserData.getUsuario().getNome());
        txtSobrenome.setText(StaticData.UserData.getUsuario().getSobrenome());
        txtEmail.setText(StaticData.UserData.getUsuario().getEmail());
        txtCpf.setText(StaticData.UserData.getUsuario().getCpf());
        txtTelefone.setText(StaticData.UserData.getUsuario().getTelefone());
        txtSenha.setText(StaticData.UserData.getUsuario().getSenha());

        btnEnderecos.setEnabled(true);
        btnEnderecos.setVisibility(View.VISIBLE);
        btnEnderecos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StaticData.UserData.getUsuario().getEnderecos().isEmpty()) {
                    AddressService addressService = new AddressService(getContext(), "GET");
                    addressService.execute(StaticData.UserData.getUsuario().getEmail());
                }

                EnderecoFragment enderecoFragment = new EnderecoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), enderecoFragment, "perfilFragment").addToBackStack(null).commit();
            }
        });

        btnDeletarUsuario.setEnabled(true);
        btnDeletarUsuario.setVisibility(View.VISIBLE);
        btnDeletarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Deseja realmente deletar seu perfil?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                UserService userService = new UserService(getContext(), "DELETE");
                                userService.execute();
                                getActivity().getSupportFragmentManager().popBackStack();
                                LoginUtility.logOut(getContext());
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNome(txtNome.getText().toString());
                usuario.setSobrenome(txtSobrenome.getText().toString());
                usuario.setCpf(txtCpf.getText().toString());
                usuario.setTelefone(txtTelefone.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());

                UserService userService = new UserService(getContext(), "UPDATE");
                userService.execute(usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getTelefone(), usuario.getEmail(), usuario.getSenha());
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
