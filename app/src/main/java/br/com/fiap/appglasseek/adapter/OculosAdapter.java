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
import java.util.List;
import java.util.Locale;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.fragment.OculosFragment;
import br.com.fiap.appglasseek.holder.OculosHolder;
import br.com.fiap.appglasseek.model.Oculos;

public class OculosAdapter extends RecyclerView.Adapter<OculosHolder> {
    private List<Oculos> oculosList;
    private Context context;
    private FragmentManager fragmentManager;

    public OculosAdapter(List<Oculos> oculosList, Context context, FragmentManager fragmentManager) {
        this.oculosList = oculosList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public OculosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.holder_oculos_menu, parent, false);

        final OculosHolder viewHolder = new OculosHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "Item " + oculosList.get(viewHolder.getAdapterPosition()).getCodigo() + " selecionado", Toast.LENGTH_SHORT).show();

                Fragment fragment = new OculosFragment().setOculos(oculosList.get(viewHolder.getAdapterPosition()));
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                // Intent intent = new Intent(context, LoginActivity.class);
                // context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OculosHolder holder, int position) {
        if (oculosList != null && oculosList.size() > 0) {
            Oculos oculos = oculosList.get(position);

            Locale.setDefault(new Locale("pt", "BR"));

            holder.marca.setText(oculos.getMarca());
            holder.modelo.setText(oculos.getModelo());
            holder.preco.setText(new DecimalFormat("R$ #,##0.00").format(oculos.getPreco()));
            holder.imagem.setImageResource(oculos.getImagem());
        }
    }

    @Override
    public int getItemCount() {
        return oculosList.size();
    }
}