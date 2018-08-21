package com.zptc.campus.action;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zptc.campus.action.R;
import com.zptc.campus.recruitment.RecruitmentMainActivity;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView img_add;
    private ListView lv_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SendByHttpClient("123","123");

    }

    public void upview(String response){

        String key = null;
        Object value = null;
        lv_activity = (ListView) findViewById(R.id.lv_activity);

        JSONArray jsonArray = JSONArray.fromObject(response);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        Log.d("long", "" + mapListJson.size());
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String,Object> obj=mapListJson.get(i);
            for(Map.Entry<String,Object> entry : obj.entrySet()){
                key = entry.getKey();
                value = entry.getValue();
                Log.d("for2",key+","+ value);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this,mapListJson,R.layout.item_activity_list,
                new String[]{"organization","title","place","time"},
                new int[]{R.id.tv_organization,R.id.tv_title,R.id.tv_place,R.id.tv_time}
        );
        lv_activity.setAdapter(adapter);

    }

    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    Map<String , String> map = new HashMap<String , String>();
                    String response=(String)msg.obj;
                    upview(response);
                    //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    // Log.d("Message", response.toString());
                    break;
                default:
                    break;
            }
        }
    };
    public static final int USER_LOGIN=1;
    public void SendByHttpClient(final String id, final String pw){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.157:8080/CampusApp/ActivityServlet");//服务器地址，指向Servlet
                    List<NameValuePair> params=new ArrayList<NameValuePair>();//将id和pw装入list
                    params.add(new BasicNameValuePair("username",id));
                    params.add(new BasicNameValuePair("password",pw));
                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");//以UTF-8格式发送
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        String response= EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
                        Gson gson = new Gson();
                        //   Map<String, Object> map = new HashMap<String, Object>();
                        //    map = gson.fromJson(response, map.getClass());
                        Message message=new Message();
                        message.what=USER_LOGIN;
                        message.obj=response;
                        Log.d("map", "map");
                        handler.sendMessage(message);//使用Message传递消息给线程
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
