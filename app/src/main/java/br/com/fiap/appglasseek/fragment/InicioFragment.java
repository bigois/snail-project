package br.com.fiap.appglasseek.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.fiap.appglasseek.R;
import br.com.fiap.appglasseek.activity.MenuActivity;
import br.com.fiap.appglasseek.adapter.OculosAdapter;
import br.com.fiap.appglasseek.dao.StaticData;
import br.com.fiap.appglasseek.service.LoginUtility;
import br.com.fiap.appglasseek.service.OculosService;
import br.com.fiap.appglasseek.service.UserService;

public class InicioFragment extends Fragment {
    public InicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inicio, container, false);

        getActivity().setTitle("Glasseek");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

//        if(preferences.getString("email", null) != null
//                && preferences.getString("senha", null) != null
//                && preferences.getBoolean("logged", false) == false) {
//
//            OculosService oculosService = new OculosService(getContext(),"GET");
//            oculosService.execute();
//
//            UserService userService = new UserService(getContext(), "GET");
//            userService.execute(preferences.getString("email", null), preferences.getString("senha", null), "InicioFragment");
//
//            MenuActivity menuActivity = new MenuActivity();
//            Intent intent = new Intent(getActivity(), menuActivity.getClass());
//
//            startActivity(intent);
//        }

        RecyclerView recyclerView = rootView.findViewById(R.id.rclInicio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        OculosAdapter oculosAdapter = new OculosAdapter(StaticData.OculosData.getOculosList(), getContext(), getFragmentManager());
        oculosAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(oculosAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        return rootView;
    }
}
