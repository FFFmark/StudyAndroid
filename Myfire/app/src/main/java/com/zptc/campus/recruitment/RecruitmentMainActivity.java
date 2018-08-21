package com.zptc.campus.recruitment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zptc.campus.action.MainActivity;
import com.zptc.campus.action.R;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import java.util.Map.Entry;

/**
 * Created by 50442 on 2018/5/24.
 */

public class RecruitmentMainActivity extends AppCompatActivity{

    private ListView lv_remt;
    private EditText edt_box;
    private Spinner spinner;
    private ImageView img_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        spinner = (Spinner) findViewById(R.id.sp_remt);
        img_search = (ImageView) findViewById(R.id.img_search);
        edt_box = (EditText) findViewById(R.id.edt_search_box);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id="222";
                String box=edt_box.getText().toString().trim();
                SendByHttpClient(id,box);

            }
        });

    }

    protected void uplistdata(String response){
        String key = null;
        Object value = null;
        lv_remt = (ListView) findViewById(R.id.lv_remt);

        JSONArray jsonArray = JSONArray.fromObject(response);
        List<Map<String,Object>> mapListJson = (List)jsonArray;
        Log.d("long", "" + mapListJson.size());
        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String,Object> obj=mapListJson.get(i);
            for(Entry<String,Object> entry : obj.entrySet()){
                key = entry.getKey();
                value = entry.getValue();
                Log.d("for2",key+","+ value);
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this,mapListJson,R.layout.item_recruitment_list,
                new String[]{"title","salary","company","place","experience","education","charge"},
                new int[]{R.id.tv_remt_title,R.id.tv_remt_salary,R.id.tv_remt_company,R.id.tv_remt_place,R.id.tv_remt_experience,R.id.tv_remt_education,R.id.tv_remt_charge}
        );
        lv_remt.setAdapter(adapter);
    }

    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_RESPONSE:
                    Map<String , String> map = new HashMap<String , String>();
                    String response=(String)msg.obj;
                    uplistdata(response);
                    //Toast.makeText(RecruitmentMainActivity.this, response, Toast.LENGTH_SHORT).show();
                    // Log.d("Message", response.toString());
                    break;
                default:
                    break;
            }
        }
    };
    public static final int USER_LOGIN=1;
    public void SendByHttpClient(final String id, final String box){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.1.157:8080/CampusApp/CampusServlet");//服务器地址，指向Servlet
                    List<NameValuePair> params=new ArrayList<NameValuePair>();//将id和pw装入list
                    params.add(new BasicNameValuePair("username",id));
                    params.add(new BasicNameValuePair("box",box));
                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");//以UTF-8格式发送
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        String response= EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
                        Gson gson = new Gson();
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
