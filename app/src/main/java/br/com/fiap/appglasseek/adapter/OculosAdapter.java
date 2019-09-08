package br.com.fiap.appglasseek.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.List;

import br.com.fiap.appglasseek.holder.OculosHolder;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.R;

public class OculosAdapter extends RecyclerView.Adapter<OculosHolder> {
    private List<Oculos> oculosLista;

    public OculosAdapter(List<Oculos> oculosLista){
        this.oculosLista = oculosLista;
    }

    @NonNull
    @Override
    public OculosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.items_compras, parent, false);
        OculosHolder viewHolder = new OculosHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OculosHolder holder, int position) {
        if (oculosLista != null && oculosLista.size() > 0) {
            Oculos oculos = oculosLista.get(position);

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
            String preco = decimalFormat.format(oculos.getPreco());

            holder.marca.setText(oculos.getMarca());
            holder.modelo.setText(oculos.getModelo());
            holder.preco.setText(preco);

        }
    }

    @Override
    public int getItemCount() {
        return oculosLista.size();
    }
}