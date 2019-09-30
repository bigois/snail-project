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
    private Button btnSalvar;


    public EnderecoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_endereco, container, false);

        getActivity().setTitle("Endereço");

        btnSalvar = rootView.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //UserService.updateAddress();
            }
        });
        return rootView;

    }

}
