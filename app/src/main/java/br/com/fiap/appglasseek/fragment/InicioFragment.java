package br.com.fiap.appglasseek.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.adapter.OculosAdapter;
import br.com.fiap.appglasseek.dao.StaticData;

public class InicioFragment extends Fragment {
    public InicioFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rclInicio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        OculosAdapter oculosAdapter = new OculosAdapter(StaticData.OculosData.getOculosList());
        oculosAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(oculosAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}
