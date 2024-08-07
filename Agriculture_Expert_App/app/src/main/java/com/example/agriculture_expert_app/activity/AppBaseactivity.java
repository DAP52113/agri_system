package com.example.agriculture_expert_app.activity;
import com.example.agriculture_expert_app.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AliasActivity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class AppBaseactivity extends Activity implements View.OnClickListener{


	public Context mcontext;
    public TextView tvTitle;
    public ImageView ivBack;
    public AppBaseactivity context;
    public TextView tvRight;
    public ImageView ivRight;
    



    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		mcontext = this;
    }


//    public void initBaseViews() {
//        tvTitle = findViewById(R.id.tv_title);
//        tvRight = findViewById(R.id.tv_right);
//        ivRight = findViewById(R.id.iv_right);
//        ivBack = findViewById(R.id.iv_back);
//       
//        ivRight.setOnClickListener(this);
//    }

    public void setTitle(String title) {
        tvTitle.setText(title);
        tvTitle.setOnClickListener(this);
    }

    public void setRightText(String text) {
        ivRight.setVisibility(View.GONE);
        tvRight.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.INVISIBLE);
        tvRight.setText(text);
        tvRight.setOnClickListener(this);
    }

    String getTitleText() {
        return tvTitle.getText().toString();
    }

    public void setRightIcon(int resId) {
        ivRight.setVisibility(View.VISIBLE);
        tvRight.setVisibility(View.INVISIBLE);
        ivRight.setImageResource(resId);
        ivRight.setOnClickListener(this);
        //右上角的图标是否被更替，默认false
        boolean isTopRightIconChange = true;
    }

    protected void onDestroy() {
        super.onDestroy();
   
        ActivityManager.removeActivity(this);
    }




    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_back:
//                finish();
//                break;
//        }
    }
	
}
