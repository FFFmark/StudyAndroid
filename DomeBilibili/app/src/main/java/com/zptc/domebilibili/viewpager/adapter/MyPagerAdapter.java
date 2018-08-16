package com.zptc.domebilibili.viewpager.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = null;
    private List<String> strings = null;

    public MyPagerAdapter(FragmentManager fm , List<Fragment> list , List<String> stringList) {
        super(fm);
        this.list = list;
        this.strings = stringList;
    }

    public MyPagerAdapter(FragmentManager fm , List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    /*
     * getItem 这一类方法在大部分Adapter中都有，position值是Item编号,用来对特定的Item进行操作??
     * 因为这个方法的一个返回值类型是Fragment类型，即我们的目标视图
     * */
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    /*
     * getCount这一类方法在大部分Adapter中都有，是用来控制显示的Item个数的
     * */
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
