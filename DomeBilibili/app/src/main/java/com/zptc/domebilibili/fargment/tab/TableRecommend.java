package com.zptc.domebilibili.fargment.tab;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zptc.domebilibili.activity.R;
import com.zptc.domebilibili.fargment.tab.recylerviewadapter.MyRecyerViewAdapter;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TableRecommend extends Fragment {

    private View view;
    private RecyclerView trRecyclerView;

    public TableRecommend() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_push_recommended,container,false);
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

                    Request request = new Request.Builder().post(formBody).url("http://api.m.mtime.cn/PageSubArea/TrailerList.api").build();

                    Response response = httpClient.newCall(request).execute();

                    String result = response.body().string();

                    Log.d("SendHttpClient", result);

                    Message message = new Message();
                    message.what = 1;
                    message.obj = result;
                    handler.handleMessage(message);

                    response.body().close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Handler handler = new Handler(){

        private String key = null;
        private String obj = null;

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                String json = (String) msg.obj;

                /**
                 *
                 * {
                 "id": 71574,
                 "movieName": "《阿尔法：狼伴归途》中文预告",
                 "coverImg": "http://img5.mtime.cn/mg/2018/08/13/083952.78968548_120X90X4.jpg",
                 "movieId": 229758,
                 "url": "http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4",
                 "hightUrl": "http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4",
                 "videoTitle": "阿尔法：狼伴归途 定档预告",
                 "videoLength": 60,
                 "rating": -1,
                 "type": ["动作", "冒险", "家庭"],
                 "summary": "科达和“狼主角”阿尔法的不凡冒险"
                 }, {
                 "id": 71571,
                 "movieName": "《无敌破坏王2》新预告",
                 "coverImg": "http://img5.mtime.cn/mg/2018/08/11/091008.76329000_120X90X4.jpg",
                 "movieId": 226450,
                 "url": "http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
                 "hightUrl": "http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
                 "videoTitle": "无敌破坏王2 预告盖尔加朵配音亮相",
                 "videoLength": 60,
                 "rating": -1,
                 "type": ["动画", "冒险", "喜剧", "家庭", "奇幻", "科幻"],
                 "summary": "迪士尼诸多公主大聚会"
                 }
                 *
                 */

                //返回的json形式，下面的解析失败

                /*JSONArray jsonArray = JSONArray.fromObject(json);
                List<Map<String,String>> mapListJson = (List)jsonArray;
                for (int i = 0 ; i<jsonArray.size() ; i++){
                    Map<String , String> objectMap = mapListJson.get(i);
                    for(Map.Entry<String,String> entry : objectMap.entrySet()){
                        key = entry.getKey();
                        obj = entry.getValue();
                    }
                }
                //这时 mapListJson 就是解析出来的一个List<Map<String , Obj>> 格式的数据
                //在这个线程中调用updata（更新UI函数）将 mapListJson 传出去更新UI 就可以完成初步的数据显示 ...
                upDataView(mapListJson);*/

            }
        }
    };

    private void upDataView(List<Map<String , String>> list){

        trRecyclerView = view.findViewById(R.id.push_rv);
        trRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        trRecyclerView.setAdapter(new MyRecyerViewAdapter( getContext() ,list));

    }

}
