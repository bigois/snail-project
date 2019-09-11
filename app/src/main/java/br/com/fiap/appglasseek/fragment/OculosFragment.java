package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Oculos;

public class OculosFragment extends Fragment {
    private Oculos oculos;
    private TextView txtCodigo, txtMarca, txtModelo, txtTipo, txtGenero, txtCor, txtComprimento, txtLargura, txtAltura, txtPreco, txtMaterial;



    public OculosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oculos, container, false);

        txtCodigo = view.findViewById(R.id.txtCodigo);
        txtCodigo.setText(oculos.getCodigo());

        txtMarca = view.findViewById(R.id.txtMarca);
        txtMarca.setText(oculos.getMarca());

        txtModelo = view.findViewById(R.id.txtModelo);
        txtModelo.setText(oculos.getModelo());

        txtTipo = view.findViewById(R.id.txtTipo);
        txtTipo.setText(oculos.getTipo());

        txtGenero = view.findViewById(R.id.txtGenero);
        txtGenero.setText(oculos.getGenero());

        txtCor = view.findViewById(R.id.txtCor);
        txtCor.setText(oculos.getCor());

        txtComprimento = view.findViewById(R.id.txtComprimento);
        txtComprimento.setText(new DecimalFormat("#,##0.00").format(oculos.getComprimento()));

        txtLargura = view.findViewById(R.id.txtLargura);
        txtLargura.setText(new DecimalFormat("#,##0.00").format(oculos.getLargura()));

        txtAltura = view.findViewById(R.id.txtAltura);
        txtAltura.setText(new DecimalFormat("#,##0.00").format(oculos.getAltura()));

        txtPreco = view.findViewById(R.id.txtPreco);
        txtPreco.setText(new DecimalFormat("#,##0.00").format(oculos.getPreco()));

        txtMaterial = view.findViewById(R.id.txtMaterial);
        txtMaterial.setText(oculos.getMaterial());




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
