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
import java.util.Locale;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.fragment.OculosFragment;
import br.com.fiap.appglasseek.holder.CarrinhoOculosHolder;
import br.com.fiap.appglasseek.model.Carrinho;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.service.CarrinhoService;

import static br.com.fiap.appglasseek.fragment.CarrinhoFragment.updateValorTotalNoFragment;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoOculosHolder> {
    private Carrinho carrinho;
    private Context context;
    private FragmentManager fragmentManager;
    private Button btnMais;
    private Button btnMenos;
    private EditText quantidade;
    private String txtQuantidade;
    private Integer intQuantidade;

    public CarrinhoAdapter(Carrinho carrinho, Context context, FragmentManager fragmentManager) {
        this.carrinho = carrinho;
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
        intQuantidade = txtQuantidade.isEmpty() ? 1 : Integer.parseInt(txtQuantidade);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new OculosFragment().setOculos(StaticData.OculosData.getOculosList().get(viewHolder.getAdapterPosition()));
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarrinhoOculosHolder carrinhoOculosHolder, final int position) {
        if (carrinho.getItens() != null && carrinho.getItens().size() > 0) {
            final Oculos oculos = carrinho.getItens().get(position).getOculos();

            Locale.setDefault(new Locale("pt", "BR"));

            carrinhoOculosHolder.modelo.setText(oculos.getMarca() + " - " + oculos.getModelo());
            carrinhoOculosHolder.preco.setText(new DecimalFormat("R$ #,##0.00").format(oculos.getPreco()));
            carrinhoOculosHolder.imagem.setImageResource(oculos.getImagem());
            carrinhoOculosHolder.quantidade.setText(carrinho.getItens().get(position).getQuantidade().toString());
            carrinhoOculosHolder.total.setText(new DecimalFormat("R$ #,##0.00").format(carrinho.getItens().get(position).getQuantidade() * oculos.getPreco()));

            btnMais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intQuantidade = 1 + Integer.parseInt(carrinhoOculosHolder.quantidade.getText().toString());
                    quantidade.setText(intQuantidade.toString());

                    StaticData.UserData.getCarrinho().getItens().get(position).setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                    updateValorTotalNoFragment();
                    notifyDataSetChanged();
                }
            });

            btnMenos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(carrinhoOculosHolder.quantidade.getText().toString()) == 1) {
                        new AlertDialog.Builder(view.getContext())
                                .setMessage("Deseja realmente deletar este item do carrinho?")
                                .setCancelable(false)
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        StaticData.UserData.getCarrinho().getItens().remove(carrinho.getItens().get(position));//.removeFromCarrinho(carrinho.getItens().get(position));

                                        CarrinhoService carrinhoService = new CarrinhoService(context,"DELETE");
                                        carrinhoService.execute(StaticData.UserData.getUsuario().getEmail(),oculos.getCodigo());

                                        updateValorTotalNoFragment();
                                        notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Não", null)
                                .show();
                    } else {
                        intQuantidade = Integer.parseInt(carrinhoOculosHolder.quantidade.getText().toString()) - 1;
                        quantidade.setText(intQuantidade.toString());

                        StaticData.UserData.getCarrinho().getItens().get(position).setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                        updateValorTotalNoFragment();
                        notifyDataSetChanged();
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (carrinho.getItens() != null) {
            return carrinho.getItens().size();
        } else {
            return 0;
        }
    }
}