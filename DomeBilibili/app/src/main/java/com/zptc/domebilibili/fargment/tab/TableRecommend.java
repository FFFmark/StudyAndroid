package com.zptc.domebilibili.fargment.tab;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zptc.domebilibili.activity.R;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TableRecommend extends Fragment {

    private View view;

    public TableRecommend() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_push_recommended,container,false);
        SendHttpClient();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SendHttpClient();
    }

    private void SendHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();

                    FormBody formBody = new FormBody.Builder().add("user","123456").build();

                    Request request = new Request.Builder().post(formBody).url("http://192.168.1.105:8080/CampusApp/CampusServlet").build();

                    Response response = httpClient.newCall(request).execute();

                    String result = response.body().string();

                    Log.d("SendHttpClient", result);

                    response.body().close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
