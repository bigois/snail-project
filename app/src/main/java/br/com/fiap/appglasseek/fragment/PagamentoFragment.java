package br.com.fiap.appglasseek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagamentoFragment extends Fragment {
    private Button btnPagar, btnVoltar;
    private TextView txtValorTotal;


    public PagamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pagamento, container, false);

        txtValorTotal = rootView.findViewById(R.id.txtValorTotal);
        txtValorTotal.setText(new DecimalFormat("R$ #,##0.00").format(StaticData.UserData.valorTotalCarrinho()));

        btnVoltar = rootView.findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), carrinhoFragment, "CarrinhoFragment").addToBackStack(null).commit();
            }
        });

        btnPagar = rootView.findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Pagamento concluído com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
