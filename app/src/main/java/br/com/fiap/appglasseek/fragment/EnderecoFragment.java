package br.com.fiap.appglasseek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.fiap.appglasseek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnderecoFragment extends Fragment {
    private Button btnConcluir;


    public EnderecoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_endereco, container, false);
        return rootView;

    }

}