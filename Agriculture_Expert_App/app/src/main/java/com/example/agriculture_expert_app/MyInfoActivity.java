package com.example.agriculture_expert_app;

import android.app.Activity;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.bean.Consult;
import com.example.agriculture_expert_app.bean.Infomation;
import com.example.agriculture_expert_app.bean.Reply;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//个人主页
public class MyInfoActivity extends Activity implements View.OnClickListener{

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private AlertDialog dialog = null;
    private Button btn_login_out;
    private Context mContext;
    private RelativeLayout rl_skin;
    private ImageView img_header;//头像显示
    private LinearLayout reply_info_user;
    private LinearLayout submit_info_Detail;
    private RelativeLayout rl_collect;
    private TextView collectinfo_num;
    private TextView like_num;
    private TextView unname_info;
    private TextView consult_num;
    private TextView reply_num;
    private Uri imageUri;
    private String Image_info_64;
    private static final int REQUEST_CHOOSE_PHOTO = 0;
    private SharedPreferences sharedPreferences;
    private LinearLayout background;
    private SharedPreferences sharePre;
    private boolean isOnclick;
    private List<Infomation> informationlist = new ArrayList<Infomation>();
    private List<Consult> consults = new ArrayList<>();
    private List<Reply> replies = new ArrayList<Reply>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_my_info);
        mContext = MyInfoActivity.this;
        init_view();//获取xml的元素

        //获取前台的参数
        Intent intent = getIntent();
        String collectCountNum = intent.getStringExtra("collectCountNum");
        String likeCountNum = intent.getStringExtra("likeCountNum");
        String unname = intent.getStringExtra("unname");
        String consultCountNum = intent.getStringExtra("consultCountNum");
        String replyCountNum = intent.getStringExtra("replyCountNum");
        img_header.setImageResource(R.mipmap.header);
        //更新UI
        collectinfo_num.setText(collectCountNum);
        like_num.setText(likeCountNum);
        consult_num.setText(consultCountNum);
        unname_info.setText(unname.toString());
        reply_num.setText(replyCountNum);

    }
    //回调刷新UI函数
    @Override
    protected void onResume() {
        super.onResume();
        getDataInfo();//获取数据函数
    }


    //获取数据
    private void getDataInfo(){
        sharedPreferences = getSharedPreferences("spfRecord", MODE_PRIVATE);
        String Image_info_64 = sharedPreferences.getString("Image_info_64", "");
        if(Image_info_64 == null){
            img_header.setImageResource(R.mipmap.my_header);
        }else{
            img_header.setImageBitmap(ImageUtils.Base64ToImage(Image_info_64));
        }

    }

    private void init_view() {
        btn_login_out = (Button)findViewById(R.id.btn_login_out);
        rl_skin = (RelativeLayout)findViewById(R.id.rl_skin);
        img_header = (ImageView)findViewById(R.id.img_header);
        submit_info_Detail = (LinearLayout)findViewById(R.id.submit_info_Detail);
        reply_info_user = (LinearLayout)findViewById(R.id.reply_info_user);
        rl_collect = (RelativeLayout)findViewById(R.id.rl_collect);
        collectinfo_num = (TextView)findViewById(R.id.collectinfo_num);
        like_num = (TextView)findViewById(R.id.like_num);
        consult_num = (TextView)findViewById(R.id.consult_num);
        unname_info = (TextView) findViewById(R.id.unname_info);
        reply_num = (TextView) findViewById(R.id.reply_num);
        background = (LinearLayout) findViewById(R.id.background);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
        //选择退出登录按钮
            case R.id.btn_login_out:
                alert = null;//创建对话框
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.icon_collect).setTitle("系统提示：")
                        .setMessage("确定要退出吗？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alert.dismiss();//对话框消失
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent out = new Intent(MyInfoActivity.this,LoginActivity.class);
                                startActivity(out);
                            }
                        }).create();
                alert.show();
                break;

                //个性换肤
            case R.id.rl_skin:
                Toast.makeText(MyInfoActivity.this,"正在更换背景....",Toast.LENGTH_SHORT).show();
                if(isOnclick == false){
                    isOnclick = true;
                    background.setBackgroundResource(R.mipmap.bk_login);
                }else{
                    background.setBackgroundResource(R.drawable.shape_my_info_bg);
                }
                break;

                //更换头像
            case R.id.img_header:
                Toast.makeText(MyInfoActivity.this,"正在跳转头像更换页面",Toast.LENGTH_SHORT).show();
                GotoUpdateImage();
                break;

                //信息发布数量查看
            case R.id.submit_info_Detail:
                Toast.makeText(MyInfoActivity.this,"正在跳转到咨询发布页面....",Toast.LENGTH_SHORT).show();
                GotoInfoSubmit();
                break;

                //专家回复查看
            case R.id.reply_info_user:
                Toast.makeText(MyInfoActivity.this,"正在跳转到专家回复页面....",Toast.LENGTH_SHORT).show();
                GoToExpertReply();
                break;

                //收藏信息查看
            case R.id.rl_collect:
                Toast.makeText(MyInfoActivity.this,"收藏数量查看",Toast.LENGTH_SHORT).show();
                GotoCollectDetail();
                break;
            default:
                break;

        }
    }



    //跳转更换头像函数
    public void GotoUpdateImage(){
        Intent intent1 = new Intent(MyInfoActivity.this,ImageUpdateActivity.class);
        img_header.setDrawingCacheEnabled(Boolean.TRUE);
        intent1.putExtra("img_header",img_header.getDrawingCache());
        startActivity(intent1);
    }

    //跳转到个人收藏页面
    public void GotoCollectDetail(){
        //开启线程查询相关收藏数据
        sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
        String unname = sharePre.getString("unname","");

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                InputStream input = null;//设置输入流
                String result = "";
                String path = Appconfig.BASE_URL+"/androidaction/queryMyCollectInformation.action?unname="+unname;
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
                        Infomation information = new Infomation();
                        information.setId(jsonObject.getInt("id"));
                        information.setArea(jsonObject.getString("area"));
                        information.setDate(jsonObject.getString("date"));
                        information.setTitle(jsonObject.getString("title"));
                        information.setCity(jsonObject.getString("city"));
                        information.setContent(jsonObject.getString("content"));
                        information.setDistrict(jsonObject.getString("district"));
                        information.setEno(jsonObject.getString("eno"));
                        information.setProvince(jsonObject.getString("province"));
                        information.setWentiphoto(jsonObject.getString("wentiphoto"));
                        information.setLikeNum(jsonObject.getInt("likeNum"));
                        information.setCollectNum(jsonObject.getInt("collectNum"));
                        //这里写获取图片
                        informationlist.add(information);//加入集合
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
                }finally {
                    if(input != null) {
                        try {
                            input.close();
                        } catch (Exception e2) {
                            // TODO: handle exception
                            e2.printStackTrace();
                        }
                    }
                }
                //处理信息操作
                Intent intentGoto = new Intent(MyInfoActivity.this,CollectDetailActivity.class);
                intentGoto.putExtra("informationlist", (Serializable) informationlist);
                startActivity(intentGoto);
            }
        });
    }

    //跳转到信息发布页面
    public void GotoInfoSubmit(){
        sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
        String counselor = sharePre.getString("unname","");
        //开启线程进行查询数据
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                InputStream input = null;//设置输入流
                String result = "";
                String path = Appconfig.BASE_URL+"/androidaction/queryMySubmitInformation.action?counselor="+counselor;
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
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Consult consult = new Consult();
                        consult.setCno(jsonObject.getInt("cno"));
                        consult.setProvince(jsonObject.getString("province"));
                        consult.setCity(jsonObject.getString("city"));
                        consult.setDistrict(jsonObject.getString("district"));
                        consult.setArea(jsonObject.getString("area"));
                        consult.setTitle(jsonObject.getString("title"));
                        consult.setContent(jsonObject.getString("content"));
                        consult.setPhoto(jsonObject.getString("photo"));

                       // Date json_date = JSONDateToDate.JSONDateToFomalDate(jsonObject_date);

                        consult.setDate(format.parse(jsonObject.getString("date")));
                        consults.add(consult);
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
                } catch (ParseException e) {
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
                //处理信息操作
                Intent intent_toSubmit = new Intent(MyInfoActivity.this,SubmitInfoDetailActivity.class);
                intent_toSubmit.putExtra("consults",(Serializable) consults);
                startActivity(intent_toSubmit);
            }
        });
    }

    //跳转到专家回复页面
    public void GoToExpertReply(){
        sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
        String replyuser = sharePre.getString("unname","");
        //开启线程进行查询数据
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                InputStream input = null;//设置输入流
                String result = "";
                String path = Appconfig.BASE_URL+"/androidaction/queryExpertReply.action?replyuser="+replyuser;
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
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Reply reply = new Reply();
                        reply.setExpert(jsonObject.getString("expert"));
                        reply.setContent(jsonObject.getString("content"));
                        reply.setReplyuser(jsonObject.getString("replyuser"));
                        reply.setDate(format.parse(jsonObject.getString("date")));
                        reply.setForconsultid(jsonObject.getInt("forconsultid"));
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
                } catch (ParseException e) {
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
                //处理信息操作
                Intent intent_toExpertReply = new Intent(MyInfoActivity.this,ExpertReplyDetailActivity.class);
                //序列化进行传递数值
                intent_toExpertReply.putExtra("replies",(Serializable) replies);
                startActivity(intent_toExpertReply);
            }
        });
    }
}