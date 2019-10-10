package br.com.fiap.appglasseek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.model.Compra;

public class DetalheCompraFragment extends Fragment {
    private Compra compra;

    public DetalheCompraFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_compra, container, false);
        getActivity().setTitle("Compra: " + compra.getCodigo());

        return view;
    }

    public DetalheCompraFragment setCompra(Compra compra) {
        this.compra = compra;

        return this;
    }
}
