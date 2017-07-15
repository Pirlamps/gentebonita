package com.gentebonita.gentebonita.gentebonita;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Vinicius on 10/07/2017.
 */

public class FiltroMapa  extends AppCompatActivity {
    String urlf = "http://cne.softforlife.com/public/";
    ProgressDialog progress = null;
    Activity ct1;
    TextView t1;

    private static ViewPagerAdapter adapter;
    FuncoesBasicas fb = new FuncoesBasicas();

    private int dotsCount;
    private ImageView[] dots;
    private ImageView credito;
    private LinearLayout pager_indicator;

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
            Intent myIntent = new Intent(FiltroMapa.this, PerfilActivity.class);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_mapa);

        /*credito = (ImageView) findViewById(R.id.credito);
        credito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(FiltroMultiplo.this, Creditos.class);
                startActivity(myIntent);
            }
        });*/

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



    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        /*String param = savedInstanceState.getString("id");
        t1 = (TextView) view.findViewById(R.id.textoabafiltro);
        t1.setText(param);*/

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.elemento1:
                if (checked)
                    break;
            case R.id.elemento2:
                if (checked)
                    break;
        }
    }

    public void vaicreditos(View v)
    {
        Intent myIntent = new Intent(FiltroMapa.this, Creditos.class);
        startActivity(myIntent);
    }
}
