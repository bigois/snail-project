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
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Endereco;
import br.com.fiap.appglasseek.service.AddressService;

public class EnderecoFragment extends Fragment {
    private Button btnAtualizar;
    private EditText txtCep;
    private EditText txtDestinatario;
    private EditText txtEndereco;
    private EditText txtNumero;
    private EditText txtCidade;
    private EditText txtEstado;
    private EditText txtComplemento;
    private EditText txtTelefone;

    public EnderecoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_endereco, container, false);
        getActivity().setTitle("Endereço");

        txtCep = rootView.findViewById(R.id.txtCep);
        txtEndereco = rootView.findViewById(R.id.txtEndereco);
        txtNumero = rootView.findViewById(R.id.txtNumero);
        txtCidade = rootView.findViewById(R.id.txtCidade);
        txtEstado = rootView.findViewById(R.id.txtEstado);
        txtComplemento = rootView.findViewById(R.id.txtComplemento);

        if (!StaticData.UserData.getUsuario().getEnderecos().isEmpty()) {
            txtCep.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getCep());
            txtEndereco.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getEndereco());
            txtNumero.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getNumero());
            txtCidade.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getCidade());
            txtEstado.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getEstado());
            txtComplemento.setText(StaticData.UserData.getUsuario().getEnderecos().get(0).getComplemento());
        }

        btnAtualizar = rootView.findViewById(R.id.btnAtualizar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method;

                if (validaCampos()) {
                    Endereco endereco = new Endereco();
                    endereco.setCep(txtCep.getText().toString());
                    endereco.setEndereco(txtEndereco.getText().toString());
                    endereco.setNumero(txtNumero.getText().toString());
                    endereco.setCidade(txtCidade.getText().toString());
                    endereco.setEstado(txtEstado.getText().toString());
                    endereco.setComplemento(txtComplemento.getText().toString());

                    if (StaticData.UserData.getUsuario().getEnderecos().size() > 0) {
                        method = "UPDATE";
                    } else {
                        method = "CREATE";
                    }

                    StaticData.UserData.getUsuario().getEnderecos().add(endereco);

                    AddressService addressService = new AddressService(getContext(), method);
                    addressService.execute(StaticData.UserData.getUsuario().getEmail(), endereco.getNumero(), endereco.getEndereco(), endereco.getCep(), endereco.getEstado(), endereco.getComplemento(), endereco.getCidade(), endereco.getMunicipio(), endereco.getDestinatario(), endereco.getTelefone());

                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getContext(), "Campos obrigatórios não preenchidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public Boolean validaCampos() {
        Boolean valid = true;

        if (TextUtils.isEmpty(txtCep.getText().toString())) {
            txtCep.setError("Informe o CEP!");
            valid = false;
        }

        if (TextUtils.isEmpty(txtEndereco.getText().toString())) {
            txtEndereco.setError("Informe o endereço!");
            valid = false;
        }

        if (TextUtils.isEmpty(txtNumero.getText().toString())) {
            txtNumero.setError("Informe o número!");
            valid = false;
        }

        if (TextUtils.isEmpty(txtCidade.getText().toString())) {
            txtCidade.setError("Informe a cidade!");
            valid = false;
        }

        if (TextUtils.isEmpty(txtEstado.getText().toString())) {
            txtEstado.setError("Informe o estado!");
            valid = false;
        }

        if (TextUtils.isEmpty(txtComplemento.getText().toString())) {
            txtComplemento.setError("Informe o complemento!");
            valid = false;
        }

        return valid;

    }
}
