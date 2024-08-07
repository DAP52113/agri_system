package com.example.agriculture_expert_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.agriculture_expert_app.adapter.SetCollectDataAdapter;
import com.example.agriculture_expert_app.bean.Infomation;

import java.util.List;

public class CollectDetailActivity extends Activity {

    private ListView my_collect_listview;
    private List<Infomation> informations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_collect_detail);
        initView();//获取相关的xml元素
        getData();//获取intent中的list信息
        setDataView();//设置UIlistview的数据
    }




    private void initView(){
        my_collect_listview = (ListView) findViewById(R.id.my_collect_listview);
    }


    private void getData(){
        Intent intent = getIntent();
        informations = (List<Infomation>) intent.getSerializableExtra("informationlist");
    }

    public void setDataView(){
        SetCollectDataAdapter adapter = new SetCollectDataAdapter(informations, CollectDetailActivity.this);
        my_collect_listview.setAdapter(adapter);
    }


}