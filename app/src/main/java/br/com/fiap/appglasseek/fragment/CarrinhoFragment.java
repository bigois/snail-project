package br.com.fiap.appglasseek.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.MenuActivity;
import br.com.fiap.appglasseek.adapter.CarrinhoAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.holder.CarrinhoOculosHolder;
import br.com.fiap.appglasseek.model.Oculos;

public class CarrinhoFragment extends Fragment {

    private static final String TAG = "CarrinhoFragment";

    private static Oculos oculos;
    private Button btnMais;
    private Button btnMenos;
    private EditText txtQuantidade;
    private Integer quantidade;
    private Button btnIrParaPagamento;


    public CarrinhoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        getActivity().setTitle("Carrinho");

        View rootView = inflater.inflate(R.layout.fragment_carrinho, container, false);

        RecyclerView carrinhoRecyclerView = (RecyclerView) rootView.findViewById(R.id.rclCarrinho);
        carrinhoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final CarrinhoAdapter carrinhoAdapter = new CarrinhoAdapter(StaticData.UserData.getCarrinhoList(),getContext(),getFragmentManager());
        carrinhoAdapter.notifyDataSetChanged();

        carrinhoRecyclerView.setAdapter(carrinhoAdapter);
        carrinhoRecyclerView.setItemAnimator(new DefaultItemAnimator());



//        btnMais = (Button) rootView.findViewById(R.id.btnIncreaseUnityBy1);
//        btnMenos = (Button) rootView.findViewById(R.id.btnDecreaseUnityBy1);
//        txtQuantidade = (EditText) rootView.findViewById(R.id.txtQuantidade);
//        quantidade = 1;//Integer.parseInt(txtQuantidade.getText().toString());

//        btnMais.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                quantidade++;
//                txtQuantidade.setText(quantidade);
//            }
//        });
//
//        btnMenos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (quantidade==1){
//                    new AlertDialog.Builder(getContext())
//                            .setMessage("Deseja realmente deletar este item do carrinho?")
//                            .setCancelable(false)
//                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    StaticData.UserData.removeFromCarrinhoList(oculos);
//                                }
//                            })
//                            .setNegativeButton("Não", null)
//                            .show();
//                }
//                quantidade--;
//                txtQuantidade.setText(quantidade);
//            }
//        });







        return rootView;
    }


    public CarrinhoFragment setOculos(Oculos oculos) {
        this.oculos = oculos;

        return this;
    }
}
