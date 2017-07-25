package com.gentebonita.gentebonita.gentebonita;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinicius on 15/06/2017.
 */

public class TutorialActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    String urlf = "https://gentebonita.net.br/api/public/";
    Activity ct1;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;

    FuncoesBasicas fb = new FuncoesBasicas();

    ProgressDialog progress = null;

    //public static CallbackManager mCallbackManager;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
        ct1 = this;

        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new Tut1Fragment());
        list.add(new Tut2Fragment());
        list.add(new Tut3Fragment());
        list.add(new Tut4Fragment());
        ViewPagerAdapter a = new ViewPagerAdapter(getSupportFragmentManager(), list);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,
                resultCode, data);
    }

    public void loginface2(final String id,final String genero, final String birthday, final String first_name, final String last_name, final String email, final String foto, final String link)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<String, String>();

        String emailfinal = email;
        String datafinal = birthday;

        // tratar data



        if(emailfinal == "" || emailfinal == null || emailfinal == "null" || emailfinal == "NULL")
        {
            emailfinal = id + "@facebook.com";
        }

        params.put("idfacebook", id);
        params.put("sexo", genero);
        params.put("birthday", birthday);
        params.put("firstname", first_name);
        params.put("lastname", last_name);
        params.put("name", first_name + ' ' + last_name);
        params.put("fotofacebook", foto);
        params.put("linkfacebook", link);
        params.put("email", emailfinal);

        progress = new ProgressDialog(this);
        progress.setTitle("Aguarde");
        progress.setMessage("Verificando conta...");
        progress.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.POST, urlf+"create", new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            progress.dismiss();
                            JSONObject jsonArray = response.getJSONObject("data");

                            //salvando sessão
                            SharedPreferences preferences =
                                    getSharedPreferences("com.gentebonita.gentebonita.gentebonita", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            /*
                                setCookie('fotoperfil',data.data.fotoperfil,30);
                                setCookie('nome',data.data.name,30);
                                setCookie('email',data.data.email,30);
                                setCookie('token',data.data.token,30);
                                setCookie('flag1avez',data.data.flag1avez,30);
                                setCookie('flag1avez2','0',30);
                                setCookie('gb',data.data.flaggentebonita,30);
                             */
                            editor.putString("token", jsonArray.getString("token"));
                            editor.putString("fotoperfil", jsonArray.getString("fotoperfil"));
                            editor.putString("nome", jsonArray.getString("name") );
                            editor.putString("email", jsonArray.getString("email") );
                            editor.putString("flag1avez", jsonArray.getString("flag1avez") );
                            editor.putString("gb", jsonArray.getString("flaggentebonita") );
                            editor.commit();

                            //verificando tipo de usuário
                            /*if(jsonArray.getString("tipo").equals("1")) {
                                //Intent myIntent = new Intent(LoginActivity.this, NewPerfilActivity.class);
                                //startActivity(myIntent);
                            }
                            else
                            {
                                //Intent myIntent = new Intent(LoginActivity.this, NewEmpregadorActivity.class);
                                //startActivity(myIntent);
                            }*/

                            Log.v("Login Facebook", editor.toString());
                            finish();
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            fb.mensagemerro("Erro ao verificar conta", "Email ainda não foi cadastrado...", ct1);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        fb.mensagemerro("Erro ao verificar conta", "Email ainda não foi cadastrado...",ct1);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);


    }

    public void fazlogin()
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        Log.v("Login Facebook", "entrou fazlogin");

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.v("Login Facebook", "sucesso Facebook");

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        //Log.v("LoginActivity", response.toString());

                                        // Application code
                                        try {
                                            String id = object.getString("id");
                                            String genero = object.getString("gender");
                                            String birthday = object.getString("birthday");
                                            String first_name = object.getString("first_name");
                                            String last_name = object.getString("last_name");
                                            String email = object.getString("email");
                                            String foto = "https://graph.facebook.com/" + id + "/picture";
                                            String link = object.getString("link");
                                            Log.v("Login Facebook", birthday);
                                            //loginface2(id, genero, birthday, first_name, last_name, email, foto, link);
                                        } catch (JSONException e) {
                                            Log.v("Login Facebook", "erro");
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                Bundle parameters = new Bundle();
                                parameters.putString("fields", "id,name,email");
                                request.setParameters(parameters);
                                request.executeAsync();
                                /*Log.d("teste", loginResult.getAccessToken().getUserId());*/
                    }

                    @Override
                    public void onCancel() {
                        Log.v("Login Facebook", "entrou cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.v("Login Facebook", "entrou erro");
                    }
                });

        //Intent myIntent = new Intent(getActivity(), MainActivity.class);
        //getActivity().startActivity(myIntent);
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
    public void onPageSelected(int position) {
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

    }
}
