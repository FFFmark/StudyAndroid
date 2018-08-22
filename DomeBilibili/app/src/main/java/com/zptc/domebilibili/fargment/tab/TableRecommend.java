package com.zptc.domebilibili.fargment.tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zptc.domebilibili.activity.R;
import com.zptc.domebilibili.fargment.tab.JsonBean.Trailers;
import com.zptc.domebilibili.fargment.tab.recylerviewadapter.MyRecyerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
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
        view = inflater.inflate(R.layout.tab_push_recommended, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SendHttpClient();
    }


    private void SendHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();

                    FormBody formBody = new FormBody.Builder().add("user", "123456").build();

                    Request request = new Request.Builder().post(formBody).url("http://api.m.mtime.cn/PageSubArea/TrailerList.api").build();

                    Response response = httpClient.newCall(request).execute();

                    String result = response.body().string();

                    Log.d("SendHttpClient", result);

                    Message message = new Message();
                    message.what = 1;
                    message.obj = result;
                    handler.handleMessage(message);

                    response.body().close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Handler handler = new Handler() {

        private String key = null;
        private String obj = null;

        @Override
        public void handleMessage(Message msg) {

            List<Map<String, Object>> dataList = null;

            if (msg.what == 1) {
                String json = (String) msg.obj;
                /**
                 *
                 {
                 "trailers":[
                 {
                 "id":71574,
                 "movieName":"《阿尔法：狼伴归途》中文预告",
                 "coverImg":"http://img5.mtime.cn/mg/2018/08/13/083952.78968548_120X90X4.jpg",
                 "movieId":229758,
                 "url":"http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4",
                 "hightUrl":"http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4",
                 "videoTitle":"阿尔法：狼伴归途 定档预告",
                 "videoLength":60,
                 "rating":-1,
                 "type":[
                 "动作",
                 "冒险",
                 "家庭"
                 ],
                 "summary":"科达和“狼主角”阿尔法的不凡冒险"
                 },
                 {
                 "id":71571,
                 "movieName":"《无敌破坏王2》新预告",
                 "coverImg":"http://img5.mtime.cn/mg/2018/08/11/091008.76329000_120X90X4.jpg",
                 "movieId":226450,
                 "url":"http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
                 "hightUrl":"http://vfx.mtime.cn/Video/2018/08/11/mp4/180811091117469119.mp4",
                 "videoTitle":"无敌破坏王2 预告盖尔加朵配音亮相",
                 "videoLength":60,
                 "rating":-1,
                 "type":[
                 "动画",
                 "冒险",
                 "喜剧",
                 "家庭",
                 "奇幻",
                 "科幻"
                 ],
                 "summary":"迪士尼诸多公主大聚会"
                 },
                 */
                //返回的json形式，下面的解析失败
                /*
                JSONArray jsonArray = JSONArray.fromObject(json);
                List<Map<String,String>> mapListJson = (List)jsonArray;
                for (int i = 0 ; i<jsonArray.size() ; i++){
                    Map<String , String> objectMap = mapListJson.get(i);
                    for(Map.Entry<String,String> entry : objectMap.entrySet()){
                        key = entry.getKey();
                        obj = entry.getValue();
                    }
                }*/
                //这时 mapListJson 就是解析出来的一个List<Map<String , Obj>> 格式的数据
                //在这个线程中调用updata（更新UI函数）将 mapListJson 传出去更新UI 就可以完成初步的数据显示 ...
                //经过json在线的解析，和数据结构分析，我发现这一串json数据的组成格式应该是 Map< String , List<Map< String ,Obj >> >
                //是类似这样的格式，所以我分3步解析这个json数据串

                // 1 .先转换成 Map<String , Obj> map 格式
                // 2 .将 Map<String , Obj> 的obj通过 map.get("trailers") ,取出obj ，将obj转换成List<>数组
                // 3 .遍历 List 将List[i] 的内容( 就是我们目标的一个数据 )重新添加到 一个 new List<Map<String ,Obj>> mapList 中，解析完成

                JSONObject jsonMap = JSONObject.parseObject(json);
                Map<String, Object> map = jsonMap;
                Log.d("Json", "HandleMessage: " + map.get("trailers"));
                /**
                 * {"hightUrl":"http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4","rating":-1,"videoTitle":"阿尔法：狼伴归途 定档预告",
                 * "movieId":229758,"movieName":"《阿尔法：狼伴归途》中文预告","id":71574,"summary":"科达和“狼主角”阿尔法的不凡冒险",
                 * "videoLength":60,"url":"http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4",
                 * "coverImg":"http://img5.mtime.cn/mg/2018/08/13/083952.78968548_120X90X4.jpg","type":["动作","冒险","家庭"]}
                 */
                //解析后map.get("trailers")的打印信息。
                String mapObj = map.get("trailers").toString();
                List<Trailers> trailersList = JSON.parseArray(mapObj, Trailers.class);
                Log.d("TrailersList", "handleMessage: " + trailersList.get(1));
                /**
                 * Trailers{id=71574, movieId=229758, videoLength=60, rating=-1, movieName='《阿尔法：狼伴归途》中文预告',
                 * coverImg='http://img5.mtime.cn/mg/2018/08/13/083952.78968548_120X90X4.jpg',
                 * url='http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4',
                 * hightUrl='http://vfx.mtime.cn/Video/2018/08/13/mp4/180813084109743369.mp4',
                 * videoTitle='阿尔法：狼伴归途 定档预告', summary='科达和“狼主角”阿尔法的不凡冒险', type=[动作, 冒险, 家庭]}
                 */
                //以上trailersList.get(1)创建新的一个List<Map<String ,Obj>> dataList; 遍历trailersList,选出需要的数据填充

                dataList = new ArrayList<Map<String, Object>>();

                for (int i = 0; i < trailersList.size(); i++) {
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    Trailers data = trailersList.get(i);
                    dataMap.put("coverImg", data.getCoverImg());
                    dataMap.put("movieName", data.getMovieName());
                    dataMap.put("summary", data.getSummary());
                    Log.d("dataMap", "handleMessage: " + dataMap);
                    dataList.add(dataMap);
                }

                //进过这样一步一步解析下来，成功的拿到了 dataList 是我要传入rvAdapter中的List数据
                /*
                * 部分日志打印
                * handleMessage: {movieName=《寡妇特工》中文预告, summary=结合黑人和女权主题, coverImg=http://img5.mtime.cn/mg/2018/08/16/080004.76663845_120X90X4.jpg}
                * handleMessage: {movieName=《阿尔法：狼伴归途》中文预告, summary=科达和“狼主角”阿尔法的不凡冒险, coverImg=http://img5.mtime.cn/mg/2018/08/13/083952.78968548_120X90X4.jpg}
                * handleMessage: {movieName=《无敌破坏王2》新预告, summary=迪士尼诸多公主大聚会, coverImg=http://img5.mtime.cn/mg/2018/08/11/091008.76329000_120X90X4.jpg}
                * */

            }

            upDataView(dataList);

        }
    };

    private void upDataView(List<Map<String, Object>> list) {

        trRecyclerView = view.findViewById(R.id.push_rv);
        trRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        trRecyclerView.setAdapter(new MyRecyerViewAdapter(getContext(), list));

    }

}
