package br.com.fiap.appglasseek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.adapter.CarrinhoAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Oculos;

public class CarrinhoFragment extends Fragment {

    private static final String TAG = "CarrinhoFragment";

    private static Oculos oculos;
    private Button btnMais;
    private Button btnMenos;
    private EditText txtQuantidade;
    private Integer quantidade;
    private Button btnIrParaPagamento;

    private static TextView txtValorTotal;


    public CarrinhoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        getActivity().setTitle("Carrinho");

        View rootView = inflater.inflate(R.layout.fragment_carrinho, container, false);

        RecyclerView carrinhoRecyclerView = (RecyclerView) rootView.findViewById(R.id.rclCarrinho);
        carrinhoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final CarrinhoAdapter carrinhoAdapter = new CarrinhoAdapter(StaticData.UserData.getCarrinho(),getContext(),getFragmentManager());
        carrinhoAdapter.notifyDataSetChanged();

        carrinhoRecyclerView.setAdapter(carrinhoAdapter);
        carrinhoRecyclerView.setItemAnimator(new DefaultItemAnimator());

        txtValorTotal = rootView.findViewById(R.id.txtValorTotal);
        txtValorTotal.setText(new DecimalFormat("R$ #,##0.00").format(StaticData.UserData.valorTotalCarrinho()));

        btnIrParaPagamento = rootView.findViewById(R.id.proceedToPayment);
        btnIrParaPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartaoFragment cartaoFragment = new CartaoFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.c, cartaoFragment, cartaoFragment.getTag())
                        .commit();
            }
        });


        return rootView;
    }


    public CarrinhoFragment setOculos(Oculos oculos) {
        this.oculos = oculos;

        return this;
    }
}
