package com.example.agriculture_expert_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.adapter.SetReplyContentAdapter;
import com.example.agriculture_expert_app.bean.Consult;
import com.example.agriculture_expert_app.bean.Expert;
import com.example.agriculture_expert_app.bean.Reply;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//专家回复详情activity
public class ExpertReplyMoreDetailActivity extends Activity {

    private TextView reply_expert;
    private TextView reply_expert_area;
    private SetReplyContentAdapter setReplyContentAdapter;
    private TextView consult_content;
    private ImageView consult_images;
    private Bitmap bitmap;
    private TextView reply_date;
    private TextView work_button;
    private TextView consult_province;
    private TextView consult_city;
    private TextView consult_district;
    private ListView reply_list;
    private ImageView reply_comment;
    private ImageView back;
    private Consult consult;
    private String expert;
    private SharedPreferences sharePre;
    private String reply_content;
    private String date;
    private int forconsultid;
    private RelativeLayout layout_gone_set;
    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;
    private List<Reply> replies = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_expert_reply_more_detail);

        initView();//绑定相关组件
        getData();//获取intent携带数据
        setData();//对数据进行渲染
    }

    private Handler hand = new Handler(Looper.getMainLooper()){

        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message message) {
            if(message.what == 1)
            {
                Bitmap bitmap_new = (Bitmap) message.obj;
                consult_images = (ImageView) findViewById(R.id.consult_images);
                consult_images.setImageBitmap(bitmap_new);
            }
        }
    };

    public void initView(){
        reply_expert = (TextView) findViewById(R.id.reply_expert);
        reply_expert_area = (TextView) findViewById(R.id.reply_expert_area);
        consult_content = (TextView) findViewById(R.id.consult_content);
        consult_images = (ImageView) findViewById(R.id.consult_images);
        reply_date = (TextView) findViewById(R.id.reply_date);
        work_button = (TextView) findViewById(R.id.work_button);
        consult_province = (TextView) findViewById(R.id.consult_province);
        consult_city = (TextView) findViewById(R.id.consult_city);
        consult_district = (TextView) findViewById(R.id.consult_district);
        reply_list = (ListView) findViewById(R.id.reply_list);
        //两个按钮
        reply_comment = (ImageView) findViewById(R.id.reply_comment);
        back = (ImageView) findViewById(R.id.back);
        hide_down = (TextView)findViewById(R.id.hide_down);
        comment_content = (EditText)findViewById(R.id.comment_content);
        comment_send = (Button)findViewById(R.id.comment_send);
        layout_gone_set = (RelativeLayout) findViewById(R.id.layout_gone_set);
    }

    public void getData(){
        Intent getIntent = getIntent();
        consult = (Consult) getIntent.getSerializableExtra("consult");
        expert = getIntent.getStringExtra("expert");
        reply_content = getIntent.getStringExtra("reply_content");
        date = getIntent.getStringExtra("date");
        forconsultid = Integer.parseInt(getIntent.getStringExtra("forconsultid"));
    }

    //设置组件数据
    public void setData(){
        reply_expert.setText(expert);
        reply_expert_area.setText(consult.getArea());
        consult_content.setText(consult.getContent());
        String path = Appconfig.BASE_URL+Appconfig.IMAGEADDPATH+consult.getPhoto();//获取路径
        //开启线程进行加载图片
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                bitmap = ImageUtils.getImage(path);
                Message message = new Message();
                message.what = 1;
                message.obj = bitmap;
                hand.sendMessage(message);
            }
        });

        reply_date.setText(date);
        consult_province.setText(consult.getProvince());
        consult_city.setText(consult.getCity());
        consult_district.setText(consult.getDistrict());

        //这里使用listview对其进行评论数据显示
        //使用foruserid进行查询，对reply的内容进行展示，并显示到listview中
        //listview的item为expert_detail_more_detail等
        //开启线程进行数据进行评论查找
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                InputStream input = null;//设置输入流
                String result = "";
                String path = Appconfig.BASE_URL+"/androidaction/queryReplyContentDetail.action?forconsultid="+forconsultid;
                HttpURLConnection conn;
                try {
                    conn = (HttpURLConnection)new URL(path).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(3000);//设置读取超时的毫秒数
                    conn.setConnectTimeout(3000);//设置连接超时的毫秒数
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    input = conn.getInputStream();//得到连接输入流
                    InputStreamReader inputStreamReader = new InputStreamReader(input);
                    //为得到JSON数据创建BufferedReader
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String inputline = "";
                    while((inputline = bufferedReader.readLine()) != null) {
                        result = result + inputline+"\n";
                    }
                    //JSON转换为字符串
                    JSONArray jsoninfoArray = new JSONArray(result);

                    for(int i= 0;i<jsoninfoArray.length();i++) {
                        JSONObject jsonObject = (JSONObject) jsoninfoArray.get(i);
                        //格式化时间
                        Reply reply = new Reply();
                        reply.setExpert(jsonObject.getString("expert"));
                        reply.setContent(jsonObject.getString("content"));
                        replies.add(reply);
                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if(input != null) {
                        try {
                            input.close();
                        } catch (Exception e2) {
                            // TODO: handle exception
                            e2.printStackTrace();
                        }
                    }
                }

                setReplyContentAdapter = new SetReplyContentAdapter(replies,ExpertReplyMoreDetailActivity.this);
                reply_list.setAdapter(setReplyContentAdapter);
            }
        });

        //对返回和评论按钮的点击事件进行设置
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //评论实现
        reply_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将其显示
                layout_gone_set.setVisibility(View.VISIBLE);
            }
        });

        //隐藏功能实现
        hide_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置元素消失
                layout_gone_set.setVisibility(View.GONE);
            }
        });

        //对发送按钮评论按钮进行点击时间进行补充
        comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comment_content.getText().toString().trim().equals("")){//内容为空
                    Toast.makeText(ExpertReplyMoreDetailActivity.this,"评论内容为空",Toast.LENGTH_SHORT).show();
                }else{

                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
                            //xiaozhang
                            String unname = sharePre.getString("unname","");
                            String expert_new = unname;
                            //内容
                            String content = comment_content.getText().toString().trim();
                            //zhuanjia
                            String replyuser_new = expert;
                            //开启线程存储相关的评论信息
                            // TODO Auto-generated method stub
                            String path=Appconfig.BASE_URL+"/androidaction/addCommentReply.action?expert="+expert_new+"&forconsultid="+forconsultid+"&replyuser="+replyuser_new+"&content="+content;
                            try {
                                try{
                                    URL url = new URL(path); //新建url并实例化
                                    //进行与服务器端的连接初始化
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    connection.setRequestMethod("GET");//获取服务器数据
                                    connection.setReadTimeout(5000);//设置读取超时的毫秒数
                                    connection.setConnectTimeout(5000);//设置连接超时的毫秒数
                                    InputStream in = connection.getInputStream();//获取输入流
                                    //字符输入流中读取文本并缓冲字符
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                                    String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据

                                    if (result.equals("successful")){
                                        Looper.prepare();
                                        Toast.makeText(ExpertReplyMoreDetailActivity.this,"评论内容成功",Toast.LENGTH_SHORT).show();
                                        Reply reply = new Reply();
                                        reply.setContent(comment_content.getText().toString().trim());
                                        reply.setExpert(unname);
                                        setReplyContentAdapter.addComment(reply);

//                                        comment_content.setText("");//清空数据
                                        Looper.loop();//关闭线程
                                    }else {
                                        Toast.makeText(ExpertReplyMoreDetailActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
                                    }
                                }catch (MalformedURLException e){}
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}