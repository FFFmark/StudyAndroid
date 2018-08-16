package com.example.a50442.mypro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a50442.mypro.uitwo.fireFragment;
import com.example.a50442.mypro.uitwo.fouthFragment;
import com.example.a50442.mypro.uitwo.oneFragment;
import com.example.a50442.mypro.uitwo.threeFragment;
import com.example.a50442.mypro.uitwo.twoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView  item_zb, item_zh, item_ys, item_zl ,item_gf;
    private ImageView btn;
    private DrawerLayout dl;
    private LinearLayout menuLL;
    private ViewPager vp;
    private oneFragment oneFragment;
    private twoFragment twoFragment;
    private threeFragment threeFragment;
    private fouthFragment fouthFragment;
    private fireFragment fireFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ImageView) findViewById(R.id.menuimg);
        dl = (DrawerLayout) findViewById(R.id.drawer);
        menuLL = (LinearLayout) findViewById(R.id.drawermenu);
        initViews();
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(5);
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(1);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dl.isDrawerOpen(menuLL)) {
                    dl.openDrawer(menuLL);
                }
            }
        });
    }

    private void initViews() {
        item_zb = (TextView) findViewById(R.id.item_zb);
        item_zh = (TextView) findViewById(R.id.item_zh);
        item_gf = (TextView) findViewById(R.id.item_gf);
        item_ys = (TextView) findViewById(R.id.item_ys);
        item_zl = (TextView) findViewById(R.id.item_zl);

        item_zb.setOnClickListener(this);
        item_zh.setOnClickListener(this);
        item_gf.setOnClickListener(this);
        item_ys.setOnClickListener(this);
        item_zl.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.mainViewPager);
        oneFragment = new oneFragment();
        twoFragment = new twoFragment();
        threeFragment = new threeFragment();
        fouthFragment = new fouthFragment();
        fireFragment = new fireFragment();
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fouthFragment);
        mFragmentList.add(fireFragment);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_zb:
                vp.setCurrentItem(0, false);
                break;
            case R.id.item_zh:
                vp.setCurrentItem(1, false);
                break;
            case R.id.item_gf:
                vp.setCurrentItem(2, false);
                break;
            case R.id.item_ys:
                vp.setCurrentItem(3, false);
                break;
            case R.id.item_zl:
                vp.setCurrentItem(4,false);
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void changeTextColor(int position) {
        if (position == 0) {
            item_zb.setTextColor(Color.parseColor("#ffffff"));
            item_zh.setTextColor(Color.parseColor("#EEDFCC"));
            item_gf.setTextColor(Color.parseColor("#EEDFCC"));
            item_ys.setTextColor(Color.parseColor("#EEDFCC"));
            item_zl.setTextColor(Color.parseColor("#EEDFCC"));
        } else if (position == 1) {
            item_zb.setTextColor(Color.parseColor("#EEDFCC"));
            item_zh.setTextColor(Color.parseColor("#ffffff"));
            item_gf.setTextColor(Color.parseColor("#EEDFCC"));
            item_ys.setTextColor(Color.parseColor("#EEDFCC"));
            item_zl.setTextColor(Color.parseColor("#EEDFCC"));
        } else if (position == 2) {
            item_zb.setTextColor(Color.parseColor("#EEDFCC"));
            item_zh.setTextColor(Color.parseColor("#EEDFCC"));
            item_gf.setTextColor(Color.parseColor("#ffffff"));
            item_ys.setTextColor(Color.parseColor("#EEDFCC"));
            item_zl.setTextColor(Color.parseColor("#EEDFCC"));
        } else if (position == 3) {
            item_zb.setTextColor(Color.parseColor("#EEDFCC"));
            item_zh.setTextColor(Color.parseColor("#EEDFCC"));
            item_gf.setTextColor(Color.parseColor("#EEDFCC"));
            item_ys.setTextColor(Color.parseColor("#ffffff"));
            item_zl.setTextColor(Color.parseColor("#EEDFCC"));
        }else if (position == 4) {
            item_zb.setTextColor(Color.parseColor("#EEDFCC"));
            item_zh.setTextColor(Color.parseColor("#EEDFCC"));
            item_gf.setTextColor(Color.parseColor("#EEDFCC"));
            item_ys.setTextColor(Color.parseColor("#EEDFCC"));
            item_zl.setTextColor(Color.parseColor("#ffffff"));
        }
    }
    public void dlView(View v){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, Fragmentindex.class);
        MainActivity.this.startActivity(intent);
    }

}