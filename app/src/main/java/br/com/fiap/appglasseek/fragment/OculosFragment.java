package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Oculos;

public class OculosFragment extends Fragment {
    private Oculos oculos;
    private TextView txtCodigo;

    public OculosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oculos, container, false);

        txtCodigo = view.findViewById(R.id.txtCodigo);
        txtCodigo.setText(oculos.getCodigo());

        // TODO GABRIEL
        // RETORNAR A PARTIR DAQUI
        // CRIAR LAYOUT E EXIBIR OS DADOS CONTIDOS NO OBJETO OCULOS

        return view;
    }

    public OculosFragment setOculos(Oculos oculos) {
        this.oculos = oculos;

        return this;
    }
}
