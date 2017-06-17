package com.gentebonita.gentebonita.gentebonita;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Vinicius on 15/06/2017.
 */

public class TutorialActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    Activity ct1;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
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
