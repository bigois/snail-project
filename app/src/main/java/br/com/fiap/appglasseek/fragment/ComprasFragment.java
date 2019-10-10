package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.adapter.CompraAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.model.Compra;
import br.com.fiap.appglasseek.service.AddressService;
import br.com.fiap.appglasseek.service.CompraService;

public class ComprasFragment extends Fragment {
    public ComprasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (StaticData.UserData.getCompras().isEmpty()) {
            CompraService compraService = new CompraService(getContext(), "GET");
            compraService.execute(StaticData.UserData.getUsuario().getEmail());
        }

        View rootView = inflater.inflate(R.layout.fragment_compras, container, false);
        getActivity().setTitle("Compras");

        RecyclerView recycler = rootView.findViewById(R.id.recycler_compras);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        final CompraAdapter adapter = new CompraAdapter(StaticData.UserData.getCompras(), getContext(), getFragmentManager());
        adapter.notifyDataSetChanged();

        recycler.setAdapter(adapter);
        recycler.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
