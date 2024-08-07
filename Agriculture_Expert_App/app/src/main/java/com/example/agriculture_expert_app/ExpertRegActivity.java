package com.example.agriculture_expert_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.params.HttpConnectionParams;

import com.example.agriculture_expert_app.activity.BaseActivity;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.StringUtils;
import com.example.agriculture_expert_app.utils.SysRole;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpertRegActivity extends BaseActivity {

	private EditText et_account;//专家注册用户名
	private EditText et_pwd;//专家密码
	private EditText et_name;//姓名
	private Button btn_expert_reg_request;//专家注册按钮
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_expert_reg);
		
		initView();
		
		//初始化监听
		initListener();

		
	}
	
	public void initListener() {
		
		//注册按钮
		btn_expert_reg_request.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String eno = et_account.getText().toString();//得到专家用户名
				String epsw = et_pwd.getText().toString();//密码
				String ename = et_name.getText().toString();//专家姓名
				expert_Login(eno,epsw,ename);
			}
		});	
		
	}
	
	
	private void expert_Login(final String eno, final String epsw, final String ename) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(eno)) {
			showToast("请输入注册手机号");
			return;
		}
		if(!StringUtils.validateMobilePhone(eno)) {
			showToast("请输入正确的手机号");
			return;
		}
		if(StringUtils.isEmpty(epsw)) {
			showToast("请输入密码");
			return;
		}
		if(StringUtils.isEmpty(ename)) {
			showToast("请输入专家姓名");
			return;
		}
		//开启线程发送数据给服务器
		Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				InputStream input = null;
				String data = "";
				//跳转到专家登录页面

				String path= Appconfig.BASE_URL+"/androidaction/expertregister.action?eno="+eno+"&epsw="+epsw+"&ename="+ename;
				try {
					try {
						URL url = new URL(path); //新建url并实例化
						//进行与服务器端的连接初始化
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");//获取服务器数据
						connection.setReadTimeout(8000);//设置读取超时的毫秒数
						connection.setConnectTimeout(8000);//设置连接超时的毫秒数
						InputStream in = connection.getInputStream();//获取输入流
						//字符输入流中读取文本并缓冲字符
						BufferedReader reader = new BufferedReader(new InputStreamReader(in));

						String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据

						//获取result结果进行效验
						if(result.equals("expertregister is successful")){
							Looper.prepare();//使消息循环初始化
							Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
							//跳转到专家登陆页面
							Intent intent = new Intent(ExpertRegActivity.this,ExpertLoginActivity.class);
							startActivity(intent);
							Looper.loop();

						}else{
							Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
							//跳转到专家登陆页面
							return;
						}
					} catch (MalformedURLException e) {
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		



		
	}


	private void initView() {
		// TODO Auto-generated method stub
		 et_account = (EditText) findViewById(R.id.et_account);//专家注册用户名
		 et_pwd = (EditText) findViewById(R.id.et_pwd);//专家密码
		 et_name = (EditText) findViewById(R.id.et_name);//姓名
		 btn_expert_reg_request = (Button)findViewById(R.id.btn_expert_reg_request);//专家注册按钮
	}
	
	
	
	

	
	
}
