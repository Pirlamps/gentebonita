package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 21/06/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gentebonita.gentebonita.gentebonita.adapters.PerfilAdapter;
import com.gentebonita.gentebonita.gentebonita.models.Perfil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabBusca  extends Fragment{

    String urlf = "http://cne.softforlife.com/public/";
    ProgressDialog progress = null;
    Activity ct1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static ViewPagerAdapter adapter;
    FuncoesBasicas fb = new FuncoesBasicas();

    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;

    private List<Perfil> msglista3 = new ArrayList<>();
    private RecyclerView recyclerView3;
    private PerfilAdapter mAdapter3;

    RequestQueue requestQueue;
    public TabBusca() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.tab_votacao, container, false);

        // TODO Auto-generated method stub
        final View rootView = inflater.inflate(R.layout.tab_busca, container,
                false);
        return rootView;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    public void onViewCreated(View view, Bundle savedInstanceState) {


        recyclerView3 = (RecyclerView) view.findViewById(R.id.lista1);

        recyclerView3.removeAllViews();
        msglista3.clear();

        mAdapter3 = new PerfilAdapter(msglista3, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setAdapter(mAdapter3);

        /*
            private Integer id;
            private String nome;
            private String idade;
            private String imagem;
            private Float nota;
        */

        Perfil c1 = new Perfil(1,"Pessoa 1"," 25 anos","",9.52f);
        msglista3.add(c1);

        Perfil c2 = new Perfil(1,"Pessoa 2"," 25 anos","",3.77f);
        msglista3.add(c2);

        Perfil c3 = new Perfil(1,"Pessoa 3"," 25 anos","",6.2f);
        msglista3.add(c3);

        Perfil c4 = new Perfil(1,"Pessoa 4"," 25 anos","",4.75f);
        msglista3.add(c4);

        Perfil c5 = new Perfil(1,"Pessoa 5"," 25 anos","",8.28f);
        msglista3.add(c5);


    }


}
