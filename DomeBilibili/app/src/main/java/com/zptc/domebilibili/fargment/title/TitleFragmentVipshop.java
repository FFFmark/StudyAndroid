package com.zptc.domebilibili.fargment.title;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zptc.domebilibili.activity.R;

public class TitleFragmentVipshop extends Fragment {

    public TitleFragmentVipshop() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vp_title_vipshop,container,false);
    }
}
