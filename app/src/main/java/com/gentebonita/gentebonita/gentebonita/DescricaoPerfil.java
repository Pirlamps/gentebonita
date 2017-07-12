package com.gentebonita.gentebonita.gentebonita;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Vinicius on 12/07/2017.
 */

public class DescricaoPerfil extends AppCompatActivity {

    String urlf = "";
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_perfil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finalizar tela ao clicar no voltar
                finish();

            }
        });


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

        com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter a = new com.gentebonita.gentebonita.gentebonita.ViewPagerAdapter(this.getSupportFragmentManager(), list);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager2);
        pager.setAdapter(a);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            Intent myIntent = new Intent(DescricaoPerfil.this, PerfilActivity.class);
            startActivity(myIntent);
        }
        else if(id == R.id.logout) {

            finish();
        }
        else if (id == R.id.descadastrar) {
            //Intent myIntent = new Intent(MainActivity.this, NewEmpregadorActivity.class);
            //startActivity(myIntent);
        }


        return super.onOptionsItemSelected(item);
    }

    public void vaicreditos(View v)
    {
        Intent myIntent = new Intent(DescricaoPerfil.this, Creditos.class);
        startActivity(myIntent);
    }
}
