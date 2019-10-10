package br.com.fiap.appglasseek.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.fiap.appglasseek.R;

public class ComprasHolder extends RecyclerView.ViewHolder {
    public TextView codigo;
    public TextView data;
    public TextView valor;
    public Button detalhes;

    public ComprasHolder(@NonNull View itemView) {
        super(itemView);

        codigo = itemView.findViewById(R.id.txt_cod_compra);
        data = itemView.findViewById(R.id.txt_data_compra);
        valor = itemView.findViewById(R.id.txt_valor_compra);
        detalhes = itemView.findViewById(R.id.btn_detalhes_compra);
    }
}
