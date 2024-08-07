package com.example.agriculture_expert_app;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;

import com.example.agriculture_expert_app.activity.BaseActivity;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.StringUtils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity  {

	
	private EditText et_account;
	
	private EditText et_pwd;
	
	private Button btn_login;
	
	private SharedPreferences sharePre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		//找到安卓页面的数据选项属性
		et_account = (EditText)findViewById(R.id.et_account);
		et_pwd = (EditText)findViewById(R.id.et_pwd);
		btn_login = (Button)findViewById(R.id.btn_login);
		
		btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//登录接口
				 String unname = et_account.getText().toString().trim();
				 String password = et_pwd.getText().toString().trim();
				login(unname,password);
			}
		});
	
	}
	
	private void login(final String unname,final String password){
		if(StringUtils.isEmpty(unname)) {
//			Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
			showToast("请输入用户名");
			return;
		}
		if(StringUtils.isEmpty(password)) {
//			Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			showToast("请输入密码");
			return;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
                String path=Appconfig.BASE_URL+"/androidaction/androidlogin.action?unname="+unname+"&password="+password;
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
                        Log.d("LoginActivity","run: "+result);
                        if (result.equals("login successfully!")){
                        	Looper.prepare();//使消息循环初始化
                        	//Looper作为线程之间的通信
                        	//并且调用Looper.loop()使消息循环一直处于运行状态
                        	//存储sp登录数据
                        	sharePre = getSharedPreferences("configeperts", MODE_PRIVATE);
                        	SharedPreferences.Editor edit = sharePre.edit();
                        	edit.putString("unname", unname);
                        	
                            Log.d("MainActivity","run2: "+result);
                            
                            Log.d("MainActivity","run3: "+result);
                            edit.commit();//提交事务
                            naticatToActivity(HomeActivity.class);//跳转到首页
                            showToast("登录成功");
                            
                           
                            Looper.loop();
                        }else {
                        	  Log.d("LoginActivity","run2: "+result);
                              Looper.prepare();
                              Log.d("LoginActivity","run3: "+result);
                              Toast.makeText(LoginActivity.this,"用户名密码输入错误",Toast.LENGTH_SHORT).show();
                              Log.d("LoginActivity","run4: "+result);
                              Looper.loop();//关闭线程
                        }
                    }catch (MalformedURLException e){}
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}}).start();
//			
//		});
	
	}
}
