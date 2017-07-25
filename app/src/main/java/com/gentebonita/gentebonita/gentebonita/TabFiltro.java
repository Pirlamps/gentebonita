package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 21/06/2017.
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
import com.appyvet.rangebar.RangeBar;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFiltro  extends Fragment{

    String urlf = "https://gentebonita.net.br/api/public/";
    ProgressDialog progress = null;
    Activity ct1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static ViewPagerAdapter adapter;
    private RangeBar notagb, distancia;
    private TextView genero, fumante, pets, religiao, signo, interesses, alimentacao;
    private EditText idadede, idadeate, palavras;
    FuncoesBasicas fb = new FuncoesBasicas();

    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;

    RequestQueue requestQueue;
    public TabFiltro() {
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
        final View rootView = inflater.inflate(R.layout.tab_filtro, container,
                false);
        return rootView;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    public void onViewCreated(View view, Bundle savedInstanceState) {

        notagb = (RangeBar) view.findViewById(R.id.notagb);
        distancia = (RangeBar) view.findViewById(R.id.distancia);

        final TextView notafiltro = (TextView) view.findViewById(R.id.notafiltro);
        final TextView valordistancia = (TextView) view.findViewById(R.id.valordistancia);

        // Sets the display values of the indices
        notagb.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                notafiltro.setText("(Nota " + (rightPinIndex+1) + ")");
            }

        });

        distancia.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                if((rightPinIndex+1) == 150)  valordistancia.setText("(Sem limite)");
                else valordistancia.setText("(" + (rightPinIndex+1) + " Km)");
            }

        });

        //private TextView genero, fumante, pets, religiao, signo, interesses, alimentacao;
        //private EditText idadede, idadeate, palavras;

        genero = (TextView) view.findViewById(R.id.genero);
        genero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Genero");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);

                //Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                //getActivity().startActivity(myIntent);
            }
        });

        fumante = (TextView) view.findViewById(R.id.fumante);
        fumante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Fumante");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });

        pets = (TextView) view.findViewById(R.id.pets);
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Pets");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });

        religiao = (TextView) view.findViewById(R.id.religiao);
        religiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Religião");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });

        signo = (TextView) view.findViewById(R.id.signo);
        signo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Signo");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });

        interesses = (TextView) view.findViewById(R.id.interesses);
        interesses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Interesses");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });

        alimentacao = (TextView) view.findViewById(R.id.alimentacao);
        alimentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), FiltroMultiplo.class);
                Bundle b = new Bundle();
                b.putString("qual_filtro", "Alimentação");
                b.putString("tela_origem", "filtro");
                myIntent.putExtras(b); //Put your id to your next Intent
                getActivity().startActivityForResult(myIntent, 1);
            }
        });


    }

}
