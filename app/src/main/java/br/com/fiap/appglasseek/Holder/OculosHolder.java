package br.com.fiap.appglasseek.Holder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.fiap.appglasseek.Model.Oculos;
import br.com.fiap.appglasseek.R;

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
