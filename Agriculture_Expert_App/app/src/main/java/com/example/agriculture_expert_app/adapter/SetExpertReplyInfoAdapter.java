package com.example.agriculture_expert_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.ExpertReplyMoreDetailActivity;
import com.example.agriculture_expert_app.ImageUpdateActivity;
import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Consult;
import com.example.agriculture_expert_app.bean.Reply;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.PhoneTrueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.List;

//专家回复适配器
public class SetExpertReplyInfoAdapter extends BaseAdapter {
    private List<Reply> replies;
    private Bitmap bitmap;
    private Context context;
    private Consult consult = new Consult();

    //构造方法
    public SetExpertReplyInfoAdapter(List<Reply> replies,Context context){
        super();
        this.replies = replies;
        this.context = context;
    }



    @Override
    public int getCount() {
        return replies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.expert_reply_listview,parent,false);
        }

        //获取元素(时间)
        TextView expert_reply_date = (TextView)view.findViewById(R.id.expert_reply_date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        expert_reply_date.setText(format.format(replies.get(position).getDate()));

        //回复专家显示
        TextView expert_reply_name = (TextView)view.findViewById(R.id.expert_reply_name);
        expert_reply_name.setText("回复专家:"+replies.get(position).getExpert());

        ImageView image_phone_reply = (ImageView)view.findViewById(R.id.image_phone_reply);
        Button btn_reply_detail = (Button) view.findViewById(R.id.btn_reply_detail);

        //设置电话图片的点击事件
        image_phone_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用打电话按钮
                String name_expert = replies.get(position).getExpert();
                //检测电话号码
                if(PhoneTrueUtils.isPhone(name_expert)){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+name_expert));
                    //添加cotext的上下文按钮进行添加
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "专家手机号码不合法", Toast.LENGTH_LONG).show();
                }
            }
        });

        //查询详情的按钮点击事件
        btn_reply_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expert = replies.get(position).getExpert();
                String reply_content = replies.get(position).getContent();
                String date = format.format(replies.get(position).getDate());
                String replyuser = replies.get(position).getReplyuser();
                String forconsultid = replies.get(position).getForconsultid().toString();
                //开启线程根据forconsultid去查询咨询详情(省市区+area+专家头像+咨询问题的内容+标题+图片)
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        InputStream input = null;//设置输入流
                        String result = "";
                        String path = Appconfig.BASE_URL+"/androidaction/queryExpertReplyDetail.action?forconsultid="+forconsultid+"&replyuser="+replyuser+"&expert="+expert;
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

                            JSONObject jsonObject = (JSONObject) jsoninfoArray.get(0);


                            consult.setProvince(jsonObject.getString("province"));
                            consult.setCity(jsonObject.getString("city"));
                            consult.setDistrict(jsonObject.getString("district"));
                            consult.setArea(jsonObject.getString("area"));
                            consult.setTitle(jsonObject.getString("title"));
                            //咨询内容
                            consult.setContent(jsonObject.getString("content"));
                            consult.setPhoto(jsonObject.getString("photo"));

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

                        Intent intent1 = new Intent(context, ExpertReplyMoreDetailActivity.class);
                        //携带数据
                        intent1.putExtra("consult",(Serializable)consult);
                        intent1.putExtra("expert",expert);
                        intent1.putExtra("forconsultid",forconsultid);
                        intent1.putExtra("reply_content",reply_content);
                        intent1.putExtra("date",date);
                        context.startActivity(intent1);
                    }
                });
            }
        });
        return view;
    }
}
