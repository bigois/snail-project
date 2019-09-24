package br.com.fiap.appglasseek.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.fragment.OculosFragment;
import br.com.fiap.appglasseek.holder.CarrinhoOculosHolder;
import br.com.fiap.appglasseek.model.Oculos;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoOculosHolder> {
    private List<Oculos> oculosList;
    private Context context;
    private FragmentManager fragmentManager;

    private Button btnMais;
    private Button btnMenos;
    private EditText quantidade;
    private String txtQuantidade;
    private Integer intQuantidade;

    public CarrinhoAdapter(List<Oculos> oculosList, Context context, FragmentManager fragmentManager) {
        this.oculosList = oculosList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CarrinhoOculosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.holder_oculos_carrinho, parent, false);

        final CarrinhoOculosHolder viewHolder = new CarrinhoOculosHolder(view);

        btnMais = view.findViewById(R.id.btnIncreaseUnityBy1);
        btnMenos = view.findViewById(R.id.btnDecreaseUnityBy1);
        quantidade = view.findViewById(R.id.txtQuantidade);
        txtQuantidade = quantidade.getEditableText().toString();
        intQuantidade = txtQuantidade.isEmpty() ? 0 : Integer.parseInt(txtQuantidade);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OculosFragment().setOculos(oculosList.get(viewHolder.getAdapterPosition()));
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intQuantidade = Integer.parseInt(quantidade.getText().toString()) + 1;
                quantidade.setText(intQuantidade.toString());
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intQuantidade == 1) {
                    new AlertDialog.Builder(view.getContext())
                            .setMessage("Deseja realmente deletar este item do carrinho?")
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    StaticData.UserData.removeFromCarrinhoList(oculosList.get(viewHolder.getAdapterPosition()));
                                }
                            }).setNegativeButton("Não", null)
                            .show();

                    notifyDataSetChanged();
                    notify();
                } else {
                    intQuantidade = Integer.parseInt(quantidade.getText().toString()) - 1;
                    quantidade.setText(intQuantidade.toString());
                }

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarrinhoOculosHolder carrinhoOculosHolder, int position) {
        if (oculosList != null && oculosList.size() > 0) {
            Oculos oculos = oculosList.get(position);

            Locale.setDefault(new Locale("pt", "BR"));

            carrinhoOculosHolder.marca.setText(oculos.getMarca());
            carrinhoOculosHolder.modelo.setText(oculos.getModelo());
            carrinhoOculosHolder.preco.setText(new DecimalFormat("R$ #,##0.00").format(oculos.getPreco()));
            carrinhoOculosHolder.imagem.setImageResource(oculos.getImagem());
            carrinhoOculosHolder.quantidade.setText("1");
        }
    }

    @Override
    public int getItemCount() {
        if (oculosList != null) {
            return oculosList.size();
        } else {
            return 0;
        }
    }
}