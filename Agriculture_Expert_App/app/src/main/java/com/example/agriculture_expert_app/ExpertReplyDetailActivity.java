package com.example.agriculture_expert_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.agriculture_expert_app.adapter.SetExpertReplyInfoAdapter;
import com.example.agriculture_expert_app.adapter.SetSubmitInfoAdapter;
import com.example.agriculture_expert_app.bean.Reply;

import java.util.List;

public class ExpertReplyDetailActivity extends Activity {

    private ListView my_expertReply_listview;
    private List<Reply> replies_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expert_reply_detail);
        initView();//初始化相关的元素操作
        getData();//获取intent中的list信息
        setAadapterData();//设置适配器操作
    }

    private void initView(){
        my_expertReply_listview = (ListView) findViewById(R.id.my_expertReply_listview);
    }

    public void getData(){
        Intent intent = getIntent();
        replies_new = (List<Reply>) intent.getSerializableExtra("replies");
    }

    public void setAadapterData(){
        SetExpertReplyInfoAdapter setExpertReplyInfoAdapter = new SetExpertReplyInfoAdapter(replies_new,ExpertReplyDetailActivity.this);
        my_expertReply_listview.setAdapter(setExpertReplyInfoAdapter);
    }

}