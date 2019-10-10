package br.com.fiap.appglasseek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.fragment.DetalheCompraFragment;
import br.com.fiap.appglasseek.fragment.OculosFragment;
import br.com.fiap.appglasseek.holder.ComprasHolder;
import br.com.fiap.appglasseek.model.Compra;

public class CompraAdapter extends RecyclerView.Adapter<ComprasHolder> {
    private List<Compra> compras;
    private Context context;
    private FragmentManager fragmentManager;

    public CompraAdapter(List<Compra> compras, Context context, FragmentManager fragmentManager) {
        this.compras = compras;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ComprasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.holder_compras, parent, false);
        final ComprasHolder viewHolder = new ComprasHolder(view);

        viewHolder.detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DetalheCompraFragment().setCompra(compras.get(viewHolder.getAdapterPosition()));
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComprasHolder holder, int position) {
        if (compras != null && compras.size() > 0) {
            Compra compra = compras.get(position);
            Locale.setDefault(new Locale("pt", "BR"));

            holder.codigo.setText(compra.getCodigo());
            holder.data.setText(new SimpleDateFormat("dd/MM/yyyy").format(compra.getData()));
            holder.valor.setText(new DecimalFormat("R$ #,##0.00").format(compra.getTotal()));
        }
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }
}