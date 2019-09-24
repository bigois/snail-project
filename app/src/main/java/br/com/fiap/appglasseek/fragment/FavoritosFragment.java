package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.adapter.OculosAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Oculos;

public class FavoritosFragment extends Fragment {
    private TextView noFavoritos;
    private List<Oculos> favoritos;
    private FavoritosFragmentListener listener;
    public FavoritosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Favoritos");

        View rootView = inflater.inflate(R.layout.fragment_favoritos, container, false);

        noFavoritos = rootView.findViewById(R.id.noFavoritos);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rclFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (StaticData.OculosData.getFavoritosList() != null) {

            OculosAdapter oculosAdapter = new OculosAdapter(StaticData.OculosData.getFavoritosList(), getContext(), getFragmentManager());
            oculosAdapter.notifyDataSetChanged();

            recyclerView.setAdapter(oculosAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } else {
            noFavoritos.setText("Você não possui favoritos");
        }

        return rootView;
    }

    public interface FavoritosFragmentListener {
        void onInputSentFav(List<Oculos> favList);
    }
}
