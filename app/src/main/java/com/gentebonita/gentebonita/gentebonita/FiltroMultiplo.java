package com.gentebonita.gentebonita.gentebonita;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vinicius on 10/07/2017.
 */

public class FiltroMultiplo  extends AppCompatActivity {
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

    RequestQueue requestQueue;

    //Qual combobox foi clicado
    String qual_filtro = "";

    //Pode ser Perfil pessoal ou Filtro
    String tela_origem = "";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);


        Bundle b = this.getIntent().getExtras();
        if(b != null)
        {
            qual_filtro = b.getString("qual_filtro");
            tela_origem = b.getString("tela_origem");
        }

        /*String param = savedInstanceState.getString("id");*/
        t1 = (TextView) findViewById(R.id.textoabafiltro);
        t1.setText(qual_filtro);

        carregatela(qual_filtro,tela_origem);


        return true;
    }

    public void carregatela(String qual_filtro,String tela_origem)
    {
        progress = new ProgressDialog(ct1);
        progress.setTitle("Aguarde");
        progress.setMessage("recebendo dados...");
        progress.show();

        SharedPreferences preferences =
                ct1.getSharedPreferences("com.gentebonita.gentebonita.gentebonita", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        requestQueue = Volley.newRequestQueue(ct1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, urlf+"tabelasvotacao", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONObject jsonArray = response.getJSONObject("data");

                            JSONArray jsontipos = jsonArray.getJSONArray("tipos");
                            //categories.add("Todos");
                            //itemsmap.put("Todos",-1);
                            for(int i=0;i< jsontipos.length();i++)
                            {
                                JSONArray blocotipo = jsontipos.getJSONArray(i);
                                for(int j=0;j< blocotipo.length();j++)
                                {
                                    JSONObject blocotipo2 = blocotipo.getJSONObject(j);

                                    String descricao = blocotipo2.getString("descricao");
                                    String tipo = blocotipo2.getString("tipo");
                                    String idtipo = blocotipo2.getString("id");

                                    if(tipo.equals("2")) {
                                        //categories.add(descricao);
                                        //itemsmap.put(descricao,Integer.parseInt(idtipo));
                                    }

                                }

                            }
                            progress.dismiss();
                            //iniciaform();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        fb.mensagemerro("Problema com a conexão", "Problema ao ler dados, verifique sua conexão com a internet...", ct1);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            Intent myIntent = new Intent(FiltroMultiplo.this, PerfilActivity.class);
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
        setContentView(R.layout.filtro_multiplo);

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
        Intent myIntent = new Intent(FiltroMultiplo.this, Creditos.class);
        startActivity(myIntent);
    }
}
