package br.com.fiap.appglasseek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DecimalFormat;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.UnityHolderActivity;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Item;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.service.CarrinhoService;
import br.com.fiap.appglasseek.service.FavoritesService;
import br.com.fiap.appglasseek.service.LoginUtility;

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
    private Button btnComprar;
    private ToggleButton toggleButton;

    public OculosFragment() {
    }

    public OculosFragment setOculos(Oculos oculos) {
        OculosFragment.oculos = oculos;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(oculos.getMarca() + " - " + oculos.getModelo());
        View view = inflater.inflate(R.layout.fragment_oculos, container, false);

        if (oculos != null) {
            txtMarca = view.findViewById(R.id.txtMarca);
            txtMarca.setText(oculos.getMarca());

            txtModelo = view.findViewById(R.id.lbl_cod_compra);
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

            toggleButton = view.findViewById(R.id.toggleButton);

            if (StaticData.UserData.getFavorito().getOculos().contains(oculos)) {
                toggleButton.setChecked(true);
            } else {
                toggleButton.setChecked(false);
            }

            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (LoginUtility.isLogged(getContext())) {
                        if (isChecked) {
                            StaticData.UserData.getFavorito().getOculos().add(oculos);
                            Toast.makeText(getContext(), "Oculos adicionado aos favoritos.", Toast.LENGTH_SHORT).show();

                            FavoritesService favoritesService = new FavoritesService(getContext(), "CREATE");
                            favoritesService.execute(StaticData.UserData.getUsuario().getEmail(), oculos.getCodigo());
                        } else {
                            StaticData.UserData.getFavorito().getOculos().remove(oculos);
                            Toast.makeText(getContext(), "Oculos removido dos favoritos.", Toast.LENGTH_SHORT).show();

                            FavoritesService favoritesService = new FavoritesService(getContext(), "DELETE");
                            favoritesService.execute(StaticData.UserData.getUsuario().getEmail(), oculos.getCodigo());
                        }
                    } else {
                        Toast.makeText(getContext(), "Faça o login para poder favoritar um óculos!", Toast.LENGTH_SHORT).show();
                        toggleButton.setChecked(false);
                    }
                }
            });

            carouselView = view.findViewById(R.id.carouselView);
            carouselView.setPageCount(oculos.getImagens().size());
            carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(oculos.getImagens().get(position));
                }
            });

            btnExperimentar = view.findViewById(R.id.btnExperimentar);
            btnExperimentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UnityHolderActivity unityHolderActivity = new UnityHolderActivity();
                    unityHolderActivity.setOculos(oculos.getCodigo());

                    Intent intent = new Intent(getActivity(), unityHolderActivity.getClass());
                    startActivity(intent);
                }
            });

            btnComprar = view.findViewById(R.id.btnComprar);
            btnComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (LoginUtility.isLogged(getContext())) {
                        if (!StaticData.UserData.oculosExisteNoCarrinho(oculos)) {
                            Item item = new Item(oculos, 1);
                            StaticData.UserData.getCarrinho().getItens().add(item);

                            CarrinhoService carrinhoService = new CarrinhoService(getContext(), "CREATE");
                            carrinhoService.execute(StaticData.UserData.getUsuario().getEmail(), item.getOculos().getCodigo(), item.getQuantidade().toString());
                        }

                        CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), carrinhoFragment, "OculosFragment").addToBackStack(null).commit();
                    } else {
                        Toast.makeText(getContext(), "Faça o login para poder realizar uma compra!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return view;
    }
}
