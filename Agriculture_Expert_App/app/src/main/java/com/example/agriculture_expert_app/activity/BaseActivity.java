package com.example.agriculture_expert_app.activity;

import com.example.agriculture_expert_app.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;

import android.widget.Toast;

public class BaseActivity extends Activity{

	public Context mcontext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mcontext = this;
		
	}
	public void showToast(String msg) {
		//子线程添加
	
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	}
	
	//打印子线程消息
	public void showToastSyn(String msg) {
		//子线程添加
		Looper.prepare();//设置消息队列
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		Looper.loop();
	}
	
	//跳转页面
	public void naticatToActivity(Class cls) {
		Intent in = new Intent(mcontext,cls);
		startActivity(in);
	}
	
}
