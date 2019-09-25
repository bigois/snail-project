package br.com.fiap.appglasseek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.fiap.appglasseek.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartaoFragment extends Fragment {
    private Button btnProximo;



    public CartaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cartao, container, false);

        btnProximo = rootView.findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnderecoFragment enderecoFragment = new EnderecoFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id. enderecoConstr, enderecoFragment, enderecoFragment.getTag())
                        .commit();
            }
        });
        return rootView;
    }

}
