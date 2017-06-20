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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
 * Created by Vinicius on 14/06/2017.
 */

public class MainActivity extends AppCompatActivity { //implements ViewPager.OnPageChangeListener
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#000000"));
        }


        //pages
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*TextView newTab = (TextView) new TextView(this);
        newTab.setText("  Mapa");
        newTab.setGravity(Gravity.CENTER);
        newTab.setTextColor(Color.rgb(0, 117, 17));
        newTab.setCompoundDrawablesWithIntrinsicBounds(R.drawable.map, 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(newTab);*/

        //Votação
        tabLayout.getTabAt(0).setIcon(R.drawable.fogo);
        tabLayout.getTabAt(0).setContentDescription("Votação");

        int colorx = Color.parseColor("#dd4949");
        tabLayout.getTabAt(0).getIcon().setColorFilter(colorx, PorterDuff.Mode.SRC_IN);
        TextView textView1 = (TextView) findViewById(R.id.textoaba);
        textView1.setText("Votação");

        //Mapa
        tabLayout.getTabAt(1).setIcon(R.drawable.mapa);
        tabLayout.getTabAt(1).setContentDescription("Mapa");

        //Chat
        tabLayout.getTabAt(2).setIcon(R.drawable.chat);
        tabLayout.getTabAt(2).setContentDescription("Chat");

        //Busca
        tabLayout.getTabAt(3).setIcon(R.drawable.busca);
        tabLayout.getTabAt(3).setContentDescription("Busca");

        //Filtro
        tabLayout.getTabAt(4).setIcon(R.drawable.fil);
        tabLayout.getTabAt(4).setContentDescription("Filtro");

        int colorx2 = Color.parseColor("#343434");
        tabLayout.getTabAt(1).getIcon().setColorFilter(colorx2, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(colorx2, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(colorx2, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(4).getIcon().setColorFilter(colorx2, PorterDuff.Mode.SRC_IN);


        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int colorx = Color.parseColor("#dd4949");
                tab.getIcon().setColorFilter(colorx, PorterDuff.Mode.SRC_IN);

                TextView textView1 = (TextView) findViewById(R.id.textoaba);
                textView1.setText(tab.getContentDescription());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                int colorx = Color.parseColor("#343434");
                tab.getIcon().setColorFilter(colorx, PorterDuff.Mode.SRC_IN);

            }
        });

    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabVotacao(), "");
        adapter.addFragment(new TabVotacao(), "");
        adapter.addFragment(new TabVotacao(), "");
        adapter.addFragment(new TabVotacao(), "");
        adapter.addFragment(new TabVotacao(), "");

        viewPager.setAdapter(adapter);
    }

    public Fragment getFragment(int pos) {

        return adapter.getItem(pos);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        Drawable myDrawable;
        String title;
        @Override
        public CharSequence getPageTitle(int position) {
            title = mFragmentTitleList.get(position);
            SpannableStringBuilder sb = new SpannableStringBuilder(title); // space added before text for convenience

            return sb;
        }
    }
}

