package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.OculosActivity;
import br.com.fiap.appglasseek.adapter.OculosAdapter;
import br.com.fiap.appglasseek.model.Oculos;

public class InicioFragment extends Fragment {

    private RecyclerView inicioRecycler;
    private List<Oculos> oculosLista;


    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);
        // 1. get a reference to recyclerView
        inicioRecycler = (RecyclerView) rootView.findViewById(R.id.inicioRecycler);

        // 2. set layoutManger
        inicioRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        // this is data fro recycler view
        oculosLista = new ArrayList<>();

        Oculos oculosItem = new Oculos(
                "1","Oakley","Juliet","Oculos de sol","M","Dourado","Oculos", "15", "10","5","350","Aço"
        );
        oculosLista.add(oculosItem);

        // 3. create an adapter
        OculosAdapter oculosAdapter = new OculosAdapter(oculosLista);

        // 4. set adapter
        inicioRecycler.setAdapter(oculosAdapter);

        // 5. set item animator to DefaultAnimator
        inicioRecycler.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
