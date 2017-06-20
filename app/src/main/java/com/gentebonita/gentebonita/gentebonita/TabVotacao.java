package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 19/06/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TabVotacao extends Fragment{

    int value;
    TabVotacao minhatab;

    String urlf = "https://popmarry.com/api/public/";
    ProgressDialog progress = null;
    FuncoesBasicas fb = new FuncoesBasicas();

    //private List<Oferta> msglista3 = new ArrayList<>();
    private RecyclerView recyclerView3;
    private ViewPager viewPager;
    //private OfertaAdapter mAdapter3;

    RequestQueue requestQueue;
    public TabVotacao() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_votacao, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        criaslideimg();

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

    private void criaslideimg() {
        /*List<Fragment> list = new ArrayList<Fragment>();

        ImageSlider img1 = new ImageSlider();
        img1.url="defaultx";
        list.add(img1);

        ImageSlider img2 = new ImageSlider();
        img2.url="defaultx";
        list.add(img2);

        ImageSlider img3 = new ImageSlider();
        img3.url="defaultx";
        list.add(img3);

        com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter a = new com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter(getSupportFragmentManager(), list);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager2);
        pager.setAdapter(a);
        pager.addOnPageChangeListener(this);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        dotsCount = a.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
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
    }
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPageSelected(int position)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot, null));
            }
            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot, null));
        }
        else
        {
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            }
            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        }

    }*/
    }
}
