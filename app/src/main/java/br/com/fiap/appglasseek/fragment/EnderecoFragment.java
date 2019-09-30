package br.com.fiap.appglasseek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.fiap.appglasseek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnderecoFragment extends Fragment {
    private Button btnSalvar;

    private EditText txtCep;
    private EditText txtDestinatario;
    private EditText txtEndereco;
    private EditText txtCidade;
    private EditText txtEstado;
    private EditText txtComplemento;
    private EditText txtTelefone;


    public EnderecoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_endereco, container, false);

        getActivity().setTitle("Endereço");

        txtCep = rootView.findViewById(R.id.txtCep);
        txtDestinatario = rootView.findViewById(R.id.txtDestinatario);
        txtEndereco = rootView.findViewById(R.id.txtEndereco);
        txtCidade = rootView.findViewById(R.id.txtCidade);
        txtEstado = rootView.findViewById(R.id.txtEstado);
        txtComplemento = rootView.findViewById(R.id.txtComplemento);
        txtTelefone = rootView.findViewById(R.id.txtTelefone);


        btnSalvar = rootView.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //UserService.updateAddress();
            }
        });
        return rootView;

    }

}
