package br.com.fiap.appglasseek.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.fiap.appglasseek.R;

public class CarrinhoOculosHolder extends RecyclerView.ViewHolder {
    public TextView modelo;
    public TextView preco;
    public ImageView imagem;
    public TextView quantidade;
    public TextView total;

    public CarrinhoOculosHolder(View itemView) {
        super(itemView);

        modelo = itemView.findViewById(R.id.txtModelo);
        preco = itemView.findViewById(R.id.txtPreco);
        imagem = itemView.findViewById(R.id.imgOculos);
        quantidade = itemView.findViewById(R.id.txtQuantidade);
        total = itemView.findViewById(R.id.txtTotal);
    }
}
