package br.com.fiap.appglasseek.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.OculosActivity;
import br.com.fiap.appglasseek.activity.UnityHolderActivity;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Oculos;

public class OculosFragment extends Fragment {
    private static Oculos oculos;
    private TextView txtMarca;
    private TextView txtModelo;
    private TextView txtTipo;
    private TextView txtGenero;
    private TextView txtCor;
    private TextView txtComprimento;
    private TextView txtLargura;
    private TextView txtAltura;
    private TextView txtPreco;
    private TextView txtMaterial;
    private CarouselView carouselView;
    private Button btnExperimentar;
    private ImageButton btnFavorito;
    private ImageButton btnRemover;



    public OculosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(oculos.getMarca() + " - " + oculos.getModelo());

        View view = inflater.inflate(R.layout.fragment_oculos, container, false);

        if (oculos != null){

        txtMarca = view.findViewById(R.id.txtMarca);
        txtMarca.setText(oculos.getMarca());

        txtModelo = view.findViewById(R.id.txtModelo);
        txtModelo.setText(oculos.getModelo());

        txtTipo = view.findViewById(R.id.txtTipo);
        txtTipo.setText(oculos.getTipo());

        txtGenero = view.findViewById(R.id.txtGenero);
        txtGenero.setText(oculos.getGenero());

        txtCor = view.findViewById(R.id.txtCor);
        txtCor.setText(oculos.getCor());

        txtComprimento = view.findViewById(R.id.txtComprimento);
        txtComprimento.setText(new DecimalFormat("#,##0.00").format(oculos.getComprimento()));

        txtLargura = view.findViewById(R.id.txtLargura);
        txtLargura.setText(new DecimalFormat("#,##0.00").format(oculos.getLargura()));

        txtAltura = view.findViewById(R.id.txtAltura);
        txtAltura.setText(new DecimalFormat("#,##0.00").format(oculos.getAltura()));

        txtPreco = view.findViewById(R.id.txtPreco);
        txtPreco.setText(new DecimalFormat("#,##0.00").format(oculos.getPreco()));

        txtMaterial = view.findViewById(R.id.txtMaterial);
        txtMaterial.setText(oculos.getMaterial());

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(oculos.getImagens().size());
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(oculos.getImagens().get(position));
            }
        });

        btnExperimentar = (Button) view.findViewById(R.id.btnExperimentar);
        btnExperimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnityHolderActivity unityHolderActivity = new UnityHolderActivity();
                unityHolderActivity.setOculos(oculos.getCodigo());//txtModelo.getText().toString());
                Intent intent = new Intent(getActivity(), unityHolderActivity.getClass());

                startActivity(intent);
            }
        });

        btnFavorito = (ImageButton) view.findViewById(R.id.btnFavorito);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StaticData.OculosData.addFavorito(oculos);
                    Toast.makeText(getContext(), "Oculos adicionado aos favoritos.", Toast.LENGTH_SHORT).show();
                }
            });
        btnRemover = (ImageButton) view.findViewById(R.id.btnRemover);
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticData.OculosData.removeFavorito(oculos);
                Toast.makeText(getContext(), "Oculos removido dos favoritos.", Toast.LENGTH_SHORT).show();
            }
        });


    }
        return view;
    }

    public OculosFragment setOculos(Oculos oculos) {
        this.oculos = oculos;

        return this;
    }

}
