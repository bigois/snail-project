package br.com.fiap.appglasseek.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

import java.text.DecimalFormat;
import java.util.Date;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Compra;
import br.com.fiap.appglasseek.service.CompraService;

public class PagamentoFragment extends Fragment {
    AlertDialog.Builder alertBuilder;
    private Button btnPagar;
    private TextView txtValorTotal;

    public PagamentoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Pagamento");
        View rootView = inflater.inflate(R.layout.fragment_pagamento2, container, false);

        txtValorTotal = rootView.findViewById(R.id.txtValorTotalP);
        txtValorTotal.setText(new DecimalFormat("R$ #,##0.00").format(StaticData.UserData.valorTotalCarrinho()));

        final CardForm cardForm = rootView.findViewById(R.id.card_form);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .setup(getActivity());

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        cardForm.getCardEditText().setText("4035984471635801");
        cardForm.getExpirationDateEditText().setText("122020");
        cardForm.getCvvEditText().setText("123");

        btnPagar = rootView.findViewById(R.id.btnBuy);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(getActivity());
                    alertBuilder.setTitle("Confirme antes de comprar:");
                    alertBuilder.setMessage("Número do cartão: " + cardForm.getCardNumber() + "\n" +
                            "Validade: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Código de segurança: " + cardForm.getCvv() + "\n");
                    alertBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Compra compra = new Compra();
                            compra.setTotal(StaticData.UserData.valorTotalCarrinho());
                            compra.setData(new Date());
                            compra.setCustoEnvio(StaticData.UserData.valorTotalCarrinho() * (0.03));
                            compra.setItem(StaticData.UserData.getCarrinho().getItens());
                            compra.setMeioPagamento("Cartão de Crédito");

                            StaticData.UserData.getCompras().add(compra);

                            CompraService compraService = new CompraService(getContext(), "CREATE");
                            compraService.execute(StaticData.UserData.getUsuario().getEmail());

                            dialogInterface.dismiss();
                            Toast.makeText(getActivity(), "Compra realizada com sucesso", Toast.LENGTH_LONG).show();

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(getActivity(), "Por favor corrija o formulário", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

}
