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
    private RecyclerView.Adapter adapter;

    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        inicioRecycler = (RecyclerView) view.findViewById(R.id.inicioRecycler);

        inicioRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        oculosLista = new ArrayList<>();

        Oculos oculosItem = new Oculos(
                "1","Oakley","Juliet","Oculos de sol","M","Dourado",00.00, 50.00, 10.00,5.00,350.00,"Aço"

        );
        oculosLista.add(oculosItem);

        adapter = new OculosAdapter(oculosLista);
        inicioRecycler.setAdapter(adapter);

        inicioRecycler.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
