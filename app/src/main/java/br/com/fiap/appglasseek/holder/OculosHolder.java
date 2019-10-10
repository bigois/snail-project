package br.com.fiap.appglasseek.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.fiap.appglasseek.R;

public class OculosHolder extends RecyclerView.ViewHolder {
    public TextView marca;
    public TextView modelo;
    public TextView preco;
    public ImageView imagem;

    public OculosHolder(View itemView) {
        super(itemView);

        marca = itemView.findViewById(R.id.txtMarca);
        modelo = itemView.findViewById(R.id.lbl_cod_compra);
        preco = itemView.findViewById(R.id.txtPreco);
        imagem = itemView.findViewById(R.id.imgOculos);
    }
}
