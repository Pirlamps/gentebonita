package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 19/06/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TabVotacao extends Fragment
{

    String urlf = "http://cne.softforlife.com/public/";
    ProgressDialog progress = null;
    Activity ct1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static ViewPagerAdapter adapter;
    FuncoesBasicas fb = new FuncoesBasicas();

    private int dotsCount;
    private ImageView[] dots;
    private ImageView credito;
    private LinearLayout pager_indicator;

    //private OfertaAdapter mAdapter3;

    RequestQueue requestQueue;
    public TabVotacao() {
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
        final View rootView = inflater.inflate(R.layout.tab_votacao, container,
                false);
        return rootView;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    public void onViewCreated(View view, Bundle savedInstanceState) {

        List<Fragment> list = new ArrayList<Fragment>();

        /*ImageSlider img1 = new ImageSlider();
        img1.url="0";
        list.add(img1);*/

        ImageSlider img2 = new ImageSlider();
        img2.url="1";
        list.add(img2);

        ImageSlider img3 = new ImageSlider();
        img3.url="2";
        list.add(img3);

        ImageSlider img4 = new ImageSlider();
        img4.url="3";
        list.add(img4);

        com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter a = new com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter(getActivity().getSupportFragmentManager(), list);
        ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager2);
        pager.setAdapter(a);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        pager_indicator = (LinearLayout) view.findViewById(R.id.viewPagerCountDots);

        dotsCount = a.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot,null));
            }
            else
            {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(8, 0, 8, 0);

            pager_indicator.addView(dots[i], params);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot,null));
        }
        else
        {
            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        }

        //nota1

        final TextView nota1 = (TextView) view.findViewById(R.id.nota1);
        final TextView nota2 = (TextView) view.findViewById(R.id.nota2);
        final TextView nota3 = (TextView) view.findViewById(R.id.nota3);
        final TextView nota4 = (TextView) view.findViewById(R.id.nota4);
        final TextView nota5 = (TextView) view.findViewById(R.id.nota5);
        final TextView nota6 = (TextView) view.findViewById(R.id.nota6);
        final TextView nota7 = (TextView) view.findViewById(R.id.nota7);
        final TextView nota8 = (TextView) view.findViewById(R.id.nota8);
        final TextView nota9 = (TextView) view.findViewById(R.id.nota9);
        final TextView nota10 = (TextView) view.findViewById(R.id.nota10);

        nota1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(R.drawable.circulovermelho);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(R.drawable.circulovermelho);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(R.drawable.circulovermelho);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(R.drawable.circulovermelho);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(R.drawable.circulovermelho);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(R.drawable.circulovermelho);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(R.drawable.circulovermelho);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(R.drawable.circulovermelho);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(0);

            }
        });

        nota9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(R.drawable.circulovermelho);
                nota10.setBackgroundResource(0);

            }
        });

        nota10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nota1.setBackgroundResource(0);
                nota2.setBackgroundResource(0);
                nota3.setBackgroundResource(0);
                nota4.setBackgroundResource(0);
                nota5.setBackgroundResource(0);
                nota6.setBackgroundResource(0);
                nota7.setBackgroundResource(0);
                nota8.setBackgroundResource(0);
                nota9.setBackgroundResource(0);
                nota10.setBackgroundResource(R.drawable.circulovermelho);

            }
        });

    }






        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        /*recyclerView3 = (RecyclerView) view.findViewById(R.id.lista1);

        recyclerView3.removeAllViews();
        msglista3.clear();

        mAdapter3 = new OfertaAdapter(msglista3, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setAdapter(mAdapter3);

        //Oferta header = new Oferta("0","0","",0f,0f,0,0,0);
        //msglista3.add(header);

        Oferta c1 = new Oferta("Titulo do Produto","Aqui vai a descrição do produto resumida em aproximadamente 100 caracters","",10.99f,11f,5,1,1);
        msglista3.add(c1);

        Oferta c2 = new Oferta("Titulo do Produto","Aqui vai a descrição do produto resumida em aproximadamente 100 caracters","",223.50f,11f,5,1,1);
        msglista3.add(c2);

        Oferta c3 = new Oferta("Titulo do Produto","Aqui vai a descrição do produto resumida em aproximadamente 100 caracters","",47.50f,11f,5,1,1);
        msglista3.add(c3);

        Oferta c4 = new Oferta("Titulo do Produto","Aqui vai a descrição do produto resumida em aproximadamente 100 caracters","",50f,11f,5,1,1);
        msglista3.add(c4);

        Oferta c5 = new Oferta("Titulo do Produto","Aqui vai a descrição do produto resumida em aproximadamente 100 caracters","",85f,11f,5,1,1);
        msglista3.add(c5);*/

}
