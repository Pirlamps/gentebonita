package com.gentebonita.gentebonita.gentebonita;

/**
 * Created by Vinicius on 21/06/2017.
 */

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class TabMapa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    int value;
    RelativeLayout tv1,tv2,tv3,tv4;
    TextView t1,t2,t3,t4;
    ImageView i1,i1v,i2,i2v,i3,i3v,i4,i4v;
    FloatingActionButton filtromapa;
    String urlf = "https://cne.net.br/backend-corp/public/";
    ProgressDialog progress = null;
    Context ct1 = null;
    FuncoesBasicas fb = new FuncoesBasicas();
    Map<Marker, String> mapa1 = new HashMap<Marker, String>();

    static int GPS_REQUEST_MEU = 1700;
    static int GPS_REQUEST_MEU2 = 1701;

    Double latitudeperfil = -22.909938;
    Double longitudeperfil = -47.062633;

    public TabMapa() {
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
        return inflater.inflate(R.layout.tab_mapa, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        //inicia mapa

        /*ct1 = getActivity();
        SharedPreferences preferences =
                getActivity().getSharedPreferences("com.gentebonita.gentebonita.gentebonita", Context.MODE_PRIVATE);
        String tipo = preferences.getString("tipo", "");
        value = Integer.parseInt(tipo);*/

        FragmentManager fm = this.getChildFragmentManager();
        SupportMapFragment mMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);


        tv1 = (RelativeLayout) getActivity().findViewById(R.id.map);
        tv2 = (RelativeLayout) getActivity().findViewById(R.id.map2);
        tv3 = (RelativeLayout) getActivity().findViewById(R.id.map3);
        tv4 = (RelativeLayout) getActivity().findViewById(R.id.map4);

        t1 = (TextView) getActivity().findViewById(R.id.t1);
        t2 = (TextView) getActivity().findViewById(R.id.t2);
        t3 = (TextView) getActivity().findViewById(R.id.t3);
        t4 = (TextView) getActivity().findViewById(R.id.t4);

        i1  = (ImageView) getActivity().findViewById(R.id.i1);
        i1v = (ImageView) getActivity().findViewById(R.id.i1v);
        i2  = (ImageView) getActivity().findViewById(R.id.i2);
        i2v = (ImageView) getActivity().findViewById(R.id.i2v);
        i3  = (ImageView) getActivity().findViewById(R.id.i3);
        i3v = (ImageView) getActivity().findViewById(R.id.i3v);
        i4  = (ImageView) getActivity().findViewById(R.id.i4);
        i4v = (ImageView) getActivity().findViewById(R.id.i4v);


        LinearLayout botaomapa = (LinearLayout) view.findViewById(R.id.botaomapa);
        botaomapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);

                t1.setTextColor(Color.parseColor("#DD4949"));
                t2.setTextColor(Color.parseColor("#CCCCCC"));
                t3.setTextColor(Color.parseColor("#CCCCCC"));
                t4.setTextColor(Color.parseColor("#CCCCCC"));

                i1.setVisibility(View.GONE);
                i1v.setVisibility(View.VISIBLE);

                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);

                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);

                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);


            }
        });

        LinearLayout botaolista = (LinearLayout) view.findViewById(R.id.botaolista);
        botaolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);

                t1.setTextColor(Color.parseColor("#CCCCCC"));
                t2.setTextColor(Color.parseColor("#DD4949"));
                t3.setTextColor(Color.parseColor("#CCCCCC"));
                t4.setTextColor(Color.parseColor("#CCCCCC"));

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);

                i2.setVisibility(View.GONE);
                i2v.setVisibility(View.VISIBLE);

                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);

                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
            }
        });

        LinearLayout botaocheckin = (LinearLayout) view.findViewById(R.id.botaocheckin);
        botaocheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.GONE);

                t1.setTextColor(Color.parseColor("#CCCCCC"));
                t2.setTextColor(Color.parseColor("#CCCCCC"));
                t3.setTextColor(Color.parseColor("#DD4949"));
                t4.setTextColor(Color.parseColor("#CCCCCC"));

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);

                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);

                i3.setVisibility(View.GONE);
                i3v.setVisibility(View.VISIBLE);

                i4.setVisibility(View.VISIBLE);
                i4v.setVisibility(View.GONE);
            }
        });


        LinearLayout botaoruzados = (LinearLayout) view.findViewById(R.id.botaocruzados);
        botaoruzados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.VISIBLE);

                t1.setTextColor(Color.parseColor("#CCCCCC"));
                t2.setTextColor(Color.parseColor("#CCCCCC"));
                t3.setTextColor(Color.parseColor("#CCCCCC"));
                t4.setTextColor(Color.parseColor("#DD4949"));

                i1.setVisibility(View.VISIBLE);
                i1v.setVisibility(View.GONE);

                i2.setVisibility(View.VISIBLE);
                i2v.setVisibility(View.GONE);

                i3.setVisibility(View.VISIBLE);
                i3v.setVisibility(View.GONE);

                i4.setVisibility(View.GONE);
                i4v.setVisibility(View.VISIBLE);
            }
        });


        filtromapa = (FloatingActionButton) view.findViewById(R.id.fab);
        filtromapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), FiltroMapa.class);
                startActivity(myIntent);

                
                /*Intent myIntent = null;
                myIntent = new Intent(getActivity(), FiltroMapa.class);

                MainActivity actf = (MainActivity) getActivity();
                String distancia = actf.buscadistancia;
                String categoria = actf.buscacategoria;
                String ordenar = actf.buscaordenar;
                String localizacao = actf.buscalocalizacao;

                Bundle mBundle = new Bundle();
                mBundle.putString("distancia", distancia);
                mBundle.putString("categoria", categoria);
                mBundle.putString("ordenar", ordenar);
                mBundle.putString("localizacao", localizacao);

                myIntent.putExtras(mBundle);

                getActivity().startActivityForResult(myIntent, 1);*/
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == GPS_REQUEST_MEU) {
            if(grantResults!=null&&grantResults.length>0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //pesquisacandidatos2();
                }
            }
        }
        if (requestCode == GPS_REQUEST_MEU2) {
            if(grantResults!=null&&grantResults.length>0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //pesquisavagas2();
                }
            }
        }
    }

    private Location getLastBestLocation(LocationManager lm) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location locationGPS = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        }
        else {
            return locationNet;
        }
    }
    /*public void pesquisavagas() {

        MainActivity actf = (MainActivity) getActivity();
        String localizacao = actf.buscalocalizacao;
        if(localizacao.equals("1")) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        Tab1Fragment.GPS_REQUEST_MEU2);
            } else {
                pesquisavagas2();
            }
        }
        else
        {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            getActivity().findViewById(R.id.loadingPane0).setVisibility(View.VISIBLE);

            Map<String, String> params = new HashMap<String, String>();
            SharedPreferences preferences =
                    getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
            String token = preferences.getString("token", "");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, urlf+"getlatlong?token="+token ,new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            try
                            {
                                JSONObject jsonArray = response.getJSONObject("data");
                                latitudeperfil = Double.parseDouble(jsonArray.getString("latitude"));
                                longitudeperfil = Double.parseDouble(jsonArray.getString("longitude"));
                                pesquisavagas2();

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error.printStackTrace();

                            pesquisavagas2();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }
    }
    public String getbuscaparams()
    {
        MainActivity actf = (MainActivity) getActivity();
        String distancia = actf.buscadistancia;
        String categoria = actf.buscacategoria;
        String ordenar = actf.buscaordenar;
        String localizacao = actf.buscalocalizacao;

        String pt2 = "&distancia="+distancia+"&categoria="+categoria+"&ordenar="+ordenar+"&localizacao="+localizacao;
        return pt2;
    }

    public void pesquisavagas2() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if(mMap==null)      return;
        mapa1.clear();
        mMap.clear();

        //bloco de pegar localização
        MainActivity actf = (MainActivity) getActivity();
        String localizacao = actf.buscalocalizacao;
        Double latitude = -22.909938;
        Double longitude = -47.062633;
        if(localizacao.equals("1"))
        {
            //localização atual
            LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            //pega a melhor localização
            Location location = getLastBestLocation(lm);

            if(location!=null)
            {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        }
        else
        {
            latitude = latitudeperfil;
            longitude = longitudeperfil;
        }
        LatLng atual = new LatLng(latitude,longitude);
        //fim bloco

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(atual, 13.0f));
        mMap.addMarker(new MarkerOptions().position(atual).title("Sua posição"));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Map<String, String> params = new HashMap<String, String>();

        getActivity().findViewById(R.id.loadingPane0).setVisibility(View.VISIBLE);

        SharedPreferences preferences =
                getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");


        String pt2 = getbuscaparams();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, urlf+"pesquisavagas?token="+token+"&lat="+latitude.toString()+"&lng="+
                longitude.toString()+pt2 ,new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        RelativeLayout rl1 = (RelativeLayout) getActivity().findViewById(R.id.loadingPane0);
                        if(rl1!=null) rl1.setVisibility(View.GONE);
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject vaga = jsonArray.getJSONObject(i);
                                LatLng item = new LatLng( Double.parseDouble(vaga.getString("latitude")) , Double.parseDouble(vaga.getString("longitude")) );
                                MarkerOptions mk1 = new MarkerOptions().position(item).title(vaga.getString("titulo")).icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                                mapa1.put(mMap.addMarker(mk1), vaga.getString("idf"));

                            }
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    if(mapa1.containsKey(marker))
                                    {
                                        String id = mapa1.get(marker);

                                        Intent intent1 = new Intent(ct1, VagaActivity.class);

                                        Bundle mBundle = new Bundle();
                                        mBundle.putInt("empresaid", Integer.parseInt(id));
                                        intent1.putExtras(mBundle);
                                        getActivity().startActivityForResult(intent1, 1);
                                    }
                                    return false;
                                }
                            });

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error.printStackTrace();
                        RelativeLayout rl1 = (RelativeLayout) getActivity().findViewById(R.id.loadingPane0);
                        if(rl1!=null) rl1.setVisibility(View.GONE);

                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 400) {
                            Toast.makeText(getActivity().getApplicationContext(),"Token expirou, faça seu login novamente.", Toast.LENGTH_LONG).show();
                            SharedPreferences preferences =
                                    getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("token", "");
                            editor.clear();
                            editor.commit();

                            Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(myIntent);
                            getActivity().finish();
                        }

                        fb.mensagemerro("Erro ao pesquisar vagas", "Tente novamente e verifique sua conexão com a internet...", ct1);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }

    public void pesquisacandidatos() {
        MainActivity actf = (MainActivity) getActivity();
        String localizacao = actf.buscalocalizacao;
        if(localizacao.equals("1"))
        {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        Tab1Fragment.GPS_REQUEST_MEU);
            } else {
                pesquisacandidatos2();
            }
        }
        else
        {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            getActivity().findViewById(R.id.loadingPane0).setVisibility(View.VISIBLE);

            Map<String, String> params = new HashMap<String, String>();
            SharedPreferences preferences =
                    getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
            String token = preferences.getString("token", "");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, urlf+"getlatlong?token="+token ,new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            try
                            {
                                JSONObject jsonArray = response.getJSONObject("data");
                                latitudeperfil = Double.parseDouble(jsonArray.getString("latitude"));
                                longitudeperfil = Double.parseDouble(jsonArray.getString("longitude"));
                                pesquisacandidatos2();

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //error.printStackTrace();

                            pesquisacandidatos2();
                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }
    }
    public void pesquisacandidatos2() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mapa1.clear();
        mMap.clear();

        //bloco de pegar localização
        MainActivity actf = (MainActivity) getActivity();
        String localizacao = actf.buscalocalizacao;
        Double latitude = -22.909938;
        Double longitude = -47.062633;
        if(localizacao.equals("1"))
        {
            //localização atual
            LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            //pega a melhor localização
            Location location = getLastBestLocation(lm);

            if(location!=null)
            {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        }
        else
        {
            latitude = latitudeperfil;
            longitude = longitudeperfil;
        }
        LatLng atual = new LatLng(latitude,longitude);
        //fim bloco

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(atual, 13.0f));
        mMap.addMarker(new MarkerOptions().position(atual).title("Sua posição"));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Map<String, String> params = new HashMap<String, String>();

        getActivity().findViewById(R.id.loadingPane0).setVisibility(View.VISIBLE);

        SharedPreferences preferences =
                getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        String pt2 = getbuscaparams();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, urlf+"pesquisacandidato?token="+token+"&lat="+latitude.toString()+"&lng="+
                longitude.toString()+pt2 ,new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        RelativeLayout rl1 = (RelativeLayout) getActivity().findViewById(R.id.loadingPane0);
                        if(rl1!=null) rl1.setVisibility(View.GONE);
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject candidato = jsonArray.getJSONObject(i);
                                String nomef = "";
                                if(candidato.getString("completo").equals("1"))
                                {
                                    nomef = candidato.getString("nome");
                                }
                                else
                                {
                                    String nomeaux = candidato.getString("nome");
                                    String[] parts = nomeaux.split(" ");
                                    nomef = parts[0];
                                }
                                LatLng item = new LatLng( Double.parseDouble(candidato.getString("latitude")) , Double.parseDouble(candidato.getString("longitude")) );
                                MarkerOptions mk1 = new MarkerOptions().position(item).title(nomef).icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                                mapa1.put(mMap.addMarker(mk1), candidato.getString("id"));

                            }
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    if(mapa1.containsKey(marker))
                                    {
                                        String id = mapa1.get(marker);

                                        Intent intent1 = new Intent(ct1, ProfileActivity.class);
                                        Bundle mBundle = new Bundle();
                                        mBundle.putInt("candidatoid", Integer.parseInt(id));
                                        intent1.putExtras(mBundle);
                                        getActivity().startActivityForResult(intent1, 1);
                                    }
                                    return false;
                                }
                            });

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error.printStackTrace();
                        RelativeLayout rl1 = (RelativeLayout) getActivity().findViewById(R.id.loadingPane0);
                        if(rl1!=null) rl1.setVisibility(View.GONE);

                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 400) {
                            Toast.makeText(getActivity().getApplicationContext(),"Token expirou, faça seu login novamente.", Toast.LENGTH_LONG).show();
                            SharedPreferences preferences =
                                    getActivity().getSharedPreferences("com.cne.cne.cne", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("token", "");
                            editor.clear();
                            editor.commit();

                            Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(myIntent);
                            getActivity().finish();
                        }

                        fb.mensagemerro("Erro ao pesquisar currículos", "Tente novamente e verifique sua conexão com a internet...", ct1);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (value == 1) {
            //pesquisavagas();
        }
        else if(value==2)
        {
            //pesquisacandidatos();
        }
    }
}
