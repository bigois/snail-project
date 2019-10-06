package br.com.fiap.appglasseek.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.adapter.CarrinhoAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Oculos;

public class CarrinhoFragment extends Fragment {
    private static final String TAG = "CarrinhoFragment";

    private static TextView txtValorTotal;
    private static Button btnCheckout;

    private static Oculos oculos;
    private Button btnMais;
    private Button btnMenos;
    private EditText txtQuantidade;
    private Integer quantidade;
    private Button btnIrParaPagamento;

    public CarrinhoFragment() {
    }

    public static void updateValorTotalNoFragment() {
        txtValorTotal.setText(new DecimalFormat("R$ #,##0.00").format(StaticData.UserData.valorTotalCarrinho()));
        if (StaticData.UserData.valorTotalCarrinho() == Double.parseDouble("0")) {
            btnCheckout.setBackgroundColor(Color.parseColor("#808080"));
        } else {
            btnCheckout.setBackgroundColor(Color.parseColor("#303F9F"));
        }
    }

    public CarrinhoFragment setOculos(Oculos oculos) {
        CarrinhoFragment.oculos = oculos;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Carrinho");

        View rootView = inflater.inflate(R.layout.fragment_carrinho, container, false);

        RecyclerView carrinhoRecyclerView = rootView.findViewById(R.id.rclCarrinho);
        carrinhoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final CarrinhoAdapter carrinhoAdapter = new CarrinhoAdapter(StaticData.UserData.getCarrinho(), getContext(), getFragmentManager());

        carrinhoAdapter.notifyDataSetChanged();
        carrinhoRecyclerView.setAdapter(carrinhoAdapter);
        carrinhoRecyclerView.setItemAnimator(new DefaultItemAnimator());

        txtValorTotal = rootView.findViewById(R.id.txtValorTotalP);
        btnCheckout = rootView.findViewById(R.id.btnCheckout);

        updateValorTotalNoFragment();

        if (StaticData.UserData.valorTotalCarrinho() == Double.parseDouble("0")) {
            btnCheckout.setBackgroundColor(Color.parseColor("#808080"));
        } else {
            btnCheckout.setBackgroundColor(Color.parseColor("#303F9F"));
        }

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (StaticData.UserData.valorTotalCarrinho() == Double.parseDouble("0")) {
                    Toast.makeText(getActivity(), "Adicione itens no carrinho para fazer comprar!", Toast.LENGTH_LONG).show();
                } else {
                    PagamentoFragment pagamentoFragment = new PagamentoFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), pagamentoFragment, "PagamentoFragment").addToBackStack(null).commit();
                }

            }
        });

        return rootView;
    }
}
