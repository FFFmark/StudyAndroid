package com.zptc.domebilibili.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zptc.domebilibili.fargment.push.FragmentChannel;
import com.zptc.domebilibili.fargment.push.FragmentDynamic;
import com.zptc.domebilibili.fargment.push.FragmentHome;
import com.zptc.domebilibili.fargment.push.FragmentVipshop;
import com.zptc.domebilibili.fargment.title.TitleFragmentChannel;
import com.zptc.domebilibili.fargment.title.TitleFragmentDynamic;
import com.zptc.domebilibili.fargment.title.TitleFragmentHome;
import com.zptc.domebilibili.fargment.title.TitleFragmentVipshop;
import com.zptc.domebilibili.viewpager.MyViewPager;
import com.zptc.domebilibili.viewpager.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private LinearLayout layout_bottom,layout_home,layout_channel,layout_dynamic,layout_vipshop,layout_menu,dl_left_menu;
    private DrawerLayout drawerLayout;
    private TextView tv_home,tv_channle,tv_dynamic,tv_vipshop;
    private ImageView img_home,img_channel,img_dynamic,img_vipshop;
    private MyViewPager vp_title,vp_push;
    private List<Fragment> list_title,list_push;
    private FragmentHome fragment_home;
    private FragmentChannel fragment_channel;
    private FragmentDynamic fragment_dynamic;
    private FragmentVipshop fragment_vipshop;
    private TitleFragmentHome title_fragment_home;
    private TitleFragmentChannel title_fragment_channel;
    private TitleFragmentDynamic title_fragment_dynamic;
    private TitleFragmentVipshop title_fragment_vipsho;
    private MyPagerAdapter mFragmentAdapter,mTitleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        vp_push.setOffscreenPageLimit(4);
        vp_push.setAdapter(mFragmentAdapter);
        vp_push.setCurrentItem(0,false);

        vp_title.setOffscreenPageLimit(4);
        vp_title.setAdapter(mTitleAdapter);
        vp_title.setCurrentItem(0,false);

        MyOnClick();

    }

    public void initViews(){

        list_title = new ArrayList<Fragment>();
        list_push = new ArrayList<Fragment>();

        layout_bottom = findViewById(R.id.main_layout_bottom);
        layout_home = findViewById(R.id.mian_layout_home);
        layout_channel = findViewById(R.id.main_layout_channel);
        layout_dynamic = findViewById(R.id.main_layout_dynamic);
        layout_vipshop = findViewById(R.id.main_layout_vipshop);
        layout_menu = findViewById(R.id.main_layout_menu);
        drawerLayout = findViewById(R.id.main_layout_drawermenu);
        dl_left_menu = findViewById(R.id.main_left_menu);

        tv_home = findViewById(R.id.main_tv_home);
        tv_channle = findViewById(R.id.main_tv_channel);
        tv_dynamic = findViewById(R.id.main_tv_dynamic);
        tv_vipshop = findViewById(R.id.main_tv_vipshop);

        img_channel = findViewById(R.id.main_img_channel);
        img_dynamic = findViewById(R.id.main_img_dynamic);
        img_home = findViewById(R.id.main_img_home);
        img_vipshop = findViewById(R.id.main_img_vipshop);

        fragment_home = new FragmentHome();
        fragment_channel = new FragmentChannel();
        fragment_dynamic = new FragmentDynamic();
        fragment_vipshop = new FragmentVipshop();

        title_fragment_home = new TitleFragmentHome();
        title_fragment_channel = new TitleFragmentChannel();
        title_fragment_dynamic = new TitleFragmentDynamic();
        title_fragment_vipsho = new TitleFragmentVipshop();

        list_push.add(fragment_home);
        list_push.add(fragment_channel);
        list_push.add(fragment_dynamic);
        list_push.add(fragment_vipshop);

        list_title.add(title_fragment_home);
        list_title.add(title_fragment_channel);
        list_title.add(title_fragment_dynamic);
        list_title.add(title_fragment_vipsho);

        vp_push = findViewById(R.id.vp_push);
        vp_title = findViewById(R.id.vp_title);

        mFragmentAdapter = new MyPagerAdapter(this.getSupportFragmentManager(),list_push);
        mTitleAdapter = new MyPagerAdapter(this.getSupportFragmentManager(),list_title);

    }

    public void MyOnClick(){

        MySetOnClick onClick = new MySetOnClick();

        layout_home.setOnClickListener(onClick);
        layout_channel.setOnClickListener(onClick);
        layout_dynamic.setOnClickListener(onClick);
        layout_vipshop.setOnClickListener(onClick);
        layout_menu.setOnClickListener(onClick);

    }

    private class MySetOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mian_layout_home:
                    vp_push.setCurrentItem(0,false);
                    vp_title.setCurrentItem(0,false);
                    ChangeColor(0);
                    break;
                case R.id.main_layout_channel:
                    vp_push.setCurrentItem(1,false);
                    vp_title.setCurrentItem(1,false);
                    ChangeColor(1);
                    break;
                case R.id.main_layout_dynamic:
                    vp_push.setCurrentItem(2,false);
                    vp_title.setCurrentItem(2,false);
                    ChangeColor(2);
                    break;
                case R.id.main_layout_vipshop:
                    vp_push.setCurrentItem(3,false);
                    vp_title.setCurrentItem(3,false);
                    ChangeColor(3);
                    break;
                case R.id.main_layout_menu:
                    if (!drawerLayout.isDrawerOpen(dl_left_menu)){
                        drawerLayout.openDrawer(dl_left_menu);
                    }
                    break;
            }
        }
    }

    private void ChangeColor (int i){

        int colorture = getResources().getColor(R.color.colorAccent);
        int colorfalse = getResources().getColor(R.color.colorTextGray);

        tv_home.setTextColor(colorfalse);
        tv_channle.setTextColor(colorfalse);
        tv_dynamic.setTextColor(colorfalse);
        tv_vipshop.setTextColor(colorfalse);

        img_home.setColorFilter(colorfalse);
        img_channel.setColorFilter(colorfalse);
        img_dynamic.setColorFilter(colorfalse);
        img_vipshop.setColorFilter(colorfalse);

        switch (i){
            case 0:
                tv_home.setTextColor(colorture);
                img_home.setColorFilter(colorture);
                break;
            case 1:
                tv_channle.setTextColor(colorture);
                img_channel.setColorFilter(colorture);
                break;
            case 2:
                tv_dynamic.setTextColor(colorture);
                img_dynamic.setColorFilter(colorture);
                break;
            case 3:
                tv_vipshop.setTextColor(colorture);
                img_vipshop.setColorFilter(colorture);
                break;
        }

    }

}
