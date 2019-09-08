package br.com.fiap.appglasseek.Holder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OculosHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView categoria;
    public TextView marca;
    public TextView cor;
    public Image imagemOculos;

    public OculosHolder(View itemView) {
        super(itemView);
    }
}
