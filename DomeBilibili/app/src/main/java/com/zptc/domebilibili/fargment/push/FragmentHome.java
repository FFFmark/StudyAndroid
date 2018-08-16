package com.zptc.domebilibili.fargment.push;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zptc.domebilibili.activity.R;
import com.zptc.domebilibili.fargment.tab.TableChaseAnime;
import com.zptc.domebilibili.fargment.tab.TableLive;
import com.zptc.domebilibili.fargment.tab.TableOther;
import com.zptc.domebilibili.fargment.tab.TableRecommend;
import com.zptc.domebilibili.viewpager.adapter.MyPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentHome extends Fragment {

    public View view;
    private MagicIndicator mag_tab;
    private ViewPager vp_push;
    private List<String> list_tab = null;
    private String[] Date = {"直播","推荐","追番"};
    private List<Fragment> list_push;
    private TableLive tab_live;
    private TableRecommend tab_recommend;
    private TableChaseAnime tab_anime;
    private TableOther tab_other;

    public FragmentHome() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vp_push_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    public void initViews(){

        list_tab = Arrays.asList(Date);
        list_push = new ArrayList<Fragment>();

        vp_push = view.findViewById(R.id.push_vp);
        mag_tab = view.findViewById(R.id.push_mag);

        tab_live = new TableLive();
        tab_recommend = new TableRecommend();
        tab_anime = new TableChaseAnime();
        tab_other = new TableOther();

        list_push.add(tab_live);
        list_push.add(tab_recommend);
        list_push.add(tab_anime);

        vp_push.setAdapter(new MyPagerAdapter(getFragmentManager(),list_push,list_tab));
        mag_tab.setBackgroundColor(getResources().getColor(R.color.colorWhite));//设置背景色
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list_tab.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {

                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.colorItemGray));//设置未选中时文本颜色
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorAccent));//设置选择后文本颜色
                simplePagerTitleView.setText(list_tab.get(i));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_push.setCurrentItem(i);
                    }
                });

                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {

                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(linePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.colorAccent));

                return linePagerIndicator;
            }
        });

        mag_tab.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mag_tab,vp_push);
        vp_push.setCurrentItem(1,false);

    }

}
