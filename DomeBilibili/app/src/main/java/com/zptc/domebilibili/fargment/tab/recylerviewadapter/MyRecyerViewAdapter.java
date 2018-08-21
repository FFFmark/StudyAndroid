package com.zptc.domebilibili.fargment.tab.recylerviewadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zptc.domebilibili.activity.R;

import java.util.List;
import java.util.Map;

public class MyRecyerViewAdapter extends RecyclerView.Adapter<MyRecyerViewAdapter.GradViewAdapter>{

    private Context mContext;
    private List<Map<String,String>> mlist;

    public MyRecyerViewAdapter(Context context , List<Map<String, String>> list) {
        super();
        this.mContext = context;
        this.mlist = list;
    }

    @NonNull
    @Override
    public MyRecyerViewAdapter.GradViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MyRecyerViewAdapter.GradViewAdapter view = new GradViewAdapter(LayoutInflater.from(mContext).inflate(R.layout.rv_item,parent,false));

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyerViewAdapter.GradViewAdapter holder, int position) {

        String imgURL = mlist.get(position).get("coverImg");
        String titleText = mlist.get(position).get("movieName");
        String Classify = mlist.get(position).get("summary");

        Glide.with(mContext)
                .load(imgURL)
                .into(holder.rvImg);
        holder.rvTitle.setText(titleText);
        holder.rvClassify.setText(Classify);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class GradViewAdapter extends RecyclerView.ViewHolder{

        private LinearLayout rvLinear;
        private ImageView rvImg;
        private TextView rvTitle,rvClassify;

        public GradViewAdapter(View itemView) {
            super(itemView);
            rvLinear = itemView.findViewById(R.id.rv_layout);
            rvImg = itemView.findViewById(R.id.rv_img);
            rvTitle = itemView.findViewById(R.id.rv_title);
            rvClassify = itemView.findViewById(R.id.rv_classify);
        }
    }

}
