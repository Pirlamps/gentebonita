package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 21/06/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gentebonita.gentebonita.gentebonita.adapters.PerfilAdapter;
import com.gentebonita.gentebonita.gentebonita.models.Perfil;
import com.google.android.gms.maps.SupportMapFragment;

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

    LinearLayout tv1,tv2,tv3,tv4,tv5;
    ImageView i1,i1v,i2,i2v,i3,i3v,i4,i4v,i5,i5v;

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

        Perfil c1 = new Perfil(1,"Marcela Cálippo"," 25 anos","fotoslide1",9.52f);
        msglista3.add(c1);

        Perfil c2 = new Perfil(1,"Raíssa Mendes"," 25 anos","fotoslide2",10f);
        msglista3.add(c2);

        Perfil c3 = new Perfil(1,"Samara Oliveira"," 25 anos","",6.2f);
        msglista3.add(c3);

        Perfil c4 = new Perfil(1,"Roberta Pereira"," 25 anos","fotoslide3",4.75f);
        msglista3.add(c4);

        Perfil c5 = new Perfil(1,"Luíza Silva"," 25 anos","fotoslide1",8.28f);
        msglista3.add(c5);


        tv1 = (LinearLayout) getActivity().findViewById(R.id.busca1);
        tv2 = (LinearLayout) getActivity().findViewById(R.id.busca2);
        tv3 = (LinearLayout) getActivity().findViewById(R.id.busca3);
        tv4 = (LinearLayout) getActivity().findViewById(R.id.busca4);
        tv5 = (LinearLayout) getActivity().findViewById(R.id.busca5);

        i1  = (ImageView) getActivity().findViewById(R.id.mi1);
        i1v = (ImageView) getActivity().findViewById(R.id.mi1v);
        i2  = (ImageView) getActivity().findViewById(R.id.mi2);
        i2v = (ImageView) getActivity().findViewById(R.id.mi2v);
        i3  = (ImageView) getActivity().findViewById(R.id.mi3);
        i3v = (ImageView) getActivity().findViewById(R.id.mi3v);
        i4  = (ImageView) getActivity().findViewById(R.id.mi4);
        i4v = (ImageView) getActivity().findViewById(R.id.mi4v);
        i5  = (ImageView) getActivity().findViewById(R.id.mi5);
        i5v = (ImageView) getActivity().findViewById(R.id.mi5v);


        LinearLayout quemeuvotei = (LinearLayout) view.findViewById(R.id.mbotao1);
        quemeuvotei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);

                i1.setVisibility(View.GONE);
                i1v.setVisibility(View.VISIBLE);
                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);
                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);
                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
                i5.setVisibility(View.VISIBLE);
                i5v.setVisibility(View.GONE);
            }
        });

        LinearLayout quemvotouemmim = (LinearLayout) view.findViewById(R.id.mbotao2);
        quemvotouemmim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);
                i2.setVisibility(View.GONE);
                i2v.setVisibility(View.VISIBLE);
                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);
                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
                i5.setVisibility(View.VISIBLE);
                i5v.setVisibility(View.GONE);
            }
        });

        LinearLayout favoritos = (LinearLayout) view.findViewById(R.id.mbotao3);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);
                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);
                i3.setVisibility(View.GONE);
                i3v.setVisibility(View.VISIBLE);
                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
                i5.setVisibility(View.VISIBLE);
                i5v.setVisibility(View.GONE);
            }
        });


        LinearLayout vips = (LinearLayout) view.findViewById(R.id.mbotao4);
        vips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.VISIBLE);
                tv5.setVisibility(View.GONE);

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);
                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);
                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);
                i4.setVisibility(View.GONE);
                i4v.setVisibility(View.VISIBLE);
                i5.setVisibility(View.VISIBLE);
                i5v.setVisibility(View.GONE);
            }
        });


        LinearLayout bloqueados = (LinearLayout) view.findViewById(R.id.mbotao5);
        bloqueados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.VISIBLE);

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);
                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);
                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);
                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
                i5.setVisibility(View.GONE);
                i5v.setVisibility(View.VISIBLE);
            }
        });


    }



}
