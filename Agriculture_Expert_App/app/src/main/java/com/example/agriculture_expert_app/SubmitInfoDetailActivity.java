package com.example.agriculture_expert_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.agriculture_expert_app.adapter.SetSubmitInfoAdapter;
import com.example.agriculture_expert_app.bean.Consult;

import java.util.List;

public class SubmitInfoDetailActivity extends Activity {

    private ListView my_submit_listview;
    private List<Consult> consults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_submit_info_detail);

        initView();//初始化XML元素
        getData();//获取intent中的list信息
        setAadapterData();//设置适配器操作
    }

    public void initView(){
        my_submit_listview = (ListView) findViewById(R.id.my_submit_listview);
    }

    public void getData(){
        Intent intent = getIntent();
        consults = (List<Consult>) intent.getSerializableExtra("consults");
    }

    public void setAadapterData(){
        SetSubmitInfoAdapter setSubmitInfoAdapter = new SetSubmitInfoAdapter(consults,SubmitInfoDetailActivity.this);
        my_submit_listview.setAdapter(setSubmitInfoAdapter);
    }

}