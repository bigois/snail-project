package br.com.fiap.appglasseek.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.fiap.appglasseek.R;

public class UsuarioHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView sobrenome;
    public TextView email;
    public TextView senha;

    public UsuarioHolder(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.txtNome);
        sobrenome = itemView.findViewById(R.id.txtSobrenome);
        email = itemView.findViewById(R.id.txtEmail);
        senha = itemView.findViewById(R.id.txtSenha);

    }
}
