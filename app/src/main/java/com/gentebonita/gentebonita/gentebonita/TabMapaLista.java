package com.gentebonita.gentebonita.gentebonita;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vinicius on 22/06/2017.
 */

public class TabMapaLista extends Fragment{

    public static TabMapaLista newInstance() {
        TabMapaLista fragment = new TabMapaLista();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_mapa_lista, container, false);
    }
}
