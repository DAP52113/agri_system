package com.example.agriculture_expert_app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_login;
	private Button btn_register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		btn_login = (Button)findViewById(R.id.btn_login);
		//登录按钮绑定事件
		btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(in);
			}
		});
		
		//得到注册按钮
		btn_register = (Button)findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ins = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(ins);
			}
		});
		//注册按钮绑定事件
		
	}
}
