package br.com.fiap.appglasseek.fragment;

import android.content.Context;
import android.content.Intent;
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
import br.com.fiap.appglasseek.model.Favorito;
import br.com.fiap.appglasseek.model.Oculos;
import br.com.fiap.appglasseek.model.Usuario;

public class FavoritosFragment extends Fragment {
    public FavoritosFragment() {
    }
    private TextView noFavoritos;
    private List<Oculos> favoritos;
    private FavoritosFragmentListener listener;

    public interface FavoritosFragmentListener{
        void onInputSentFav(List<Oculos> favList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout. fragment_favoritos, container, false);

        noFavoritos = rootView.findViewById(R.id.noFavoritos);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rclFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Intent intent = getActivity().getIntent();
        if(favoritos != null) {

            OculosAdapter oculosAdapter = new OculosAdapter(favoritos, getContext(), getFragmentManager());
            oculosAdapter.notifyDataSetChanged();

            recyclerView.setAdapter(oculosAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }else{
            noFavoritos.setText("Você não possui favoritos");
        }


        return rootView;
    }
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FavoritosFragmentListener){
            listener = (FavoritosFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + "must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public void updateData(List<Oculos> novaLista){
        favoritos.addAll(novaLista);
    }

     */
}
