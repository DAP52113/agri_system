package com.example.agriculture_expert_app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.agriculture_expert_app.activity.BaseActivity;
import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.StringUtils;

public class QuestionOnlineActivity extends BaseActivity {

	//引入android前端的页面布局
	private EditText question_title;//问题标题
	private EditText question_content;//问题内容
	private Spinner qustion_province;//省
	private Spinner question_city;//城市
	private Spinner question_dirct;//区域
	private Button btn_expert_question_subbmit;//得到提交按钮
	private SharedPreferences sharePre;//得到用户登录信息 
	private ArrayAdapter adapter;


//	private List<String> provinceList;//设置省市区存储列表
//    private List<String> citylist;
//    private List<String> arealist;
//    private Context context ;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除窗格顶部布局
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_question_online);
		question_title = (EditText)findViewById(R.id.question_title);
		question_content = (EditText)findViewById(R.id.question_content);
		
		qustion_province = (Spinner) findViewById(R.id.qustion_province);
		question_city  = (Spinner)findViewById(R.id.question_city);
		question_dirct = (Spinner)findViewById(R.id.question_dirct);
		
		
		sharePre = getSharedPreferences("configeperts", MODE_PRIVATE);
		//获取用户的登录信息
		final String counselor = sharePre.getString("unname", "");
		
		Intent intent = getIntent();
		//咨询领域
		final String  area = intent.getStringExtra("area");


		adapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.province, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		qustion_province.setAdapter(adapter);
		qustion_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Spinner spinner = (Spinner) parent;
				String pro = (String) spinner.getItemAtPosition(position);
				ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.sx, android.R.layout.simple_spinner_item);
				question_city.setAdapter(cityAdapter);
				if (pro.equals("陕西省")) {
					cityAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.sx, android.R.layout.simple_spinner_item);
					//cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					question_city.setAdapter(cityAdapter);
					question_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							Spinner spinner = (Spinner) parent;
							String cit = (String) spinner.getItemAtPosition(position);
							ArrayAdapter<CharSequence> countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.ya, android.R.layout.simple_spinner_item);
							if (cit.equals("延安市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.ya,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("商洛市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.sl,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("渭南市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.wn,
										android.R.layout.simple_spinner_item);
							}
							question_dirct.setAdapter(countyAdapter);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {

						}
					});
				} else if (pro.equals("辽宁省")) {
					cityAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.ln,
							android.R.layout.simple_spinner_item);
					cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					question_city.setAdapter(cityAdapter);
					question_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							Spinner spinner = (Spinner) parent;
							String cit = (String) spinner.getItemAtPosition(position);
							ArrayAdapter<CharSequence> countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.sy,
									android.R.layout.simple_spinner_item);
							if (cit.equals("沈阳市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.sy,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("大连市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.dl,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("锦州市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.jz,
										android.R.layout.simple_spinner_item);
							}
							question_dirct.setAdapter(countyAdapter);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {

						}
					});

				} else if (pro.equals("湖北省")) {
					cityAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.hb,
							android.R.layout.simple_spinner_item);
					cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					question_city.setAdapter(cityAdapter);
					question_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							Spinner spinner = (Spinner) parent;
							String cit = (String) spinner.getItemAtPosition(position);
							ArrayAdapter<CharSequence> countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.wh,
									android.R.layout.simple_spinner_item);
							if (cit.equals("延安市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.wh,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("商洛市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.yc,
										android.R.layout.simple_spinner_item);
							} else if (cit.equals("渭南市")) {
								countyAdapter = ArrayAdapter.createFromResource(QuestionOnlineActivity.this, R.array.hg,
										android.R.layout.simple_spinner_item);
							}
							question_dirct.setAdapter(countyAdapter);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {

						}
					});
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});






					//继续执行UI操作代码
					//获取ui数据
					
					btn_expert_question_subbmit = (Button)findViewById(R.id.btn_expert_question_subbmit);
					btn_expert_question_subbmit.setOnClickListener(new OnClickListener() {
						
					
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final String title = question_title.getText().toString().trim();
							final String content = question_content.getText().toString().trim();
							if(title.equals("")) {
								showToast("请输入问题标题");
								return;
							}
							if(content.equals("")) {
								showToast("请输入问题内容");
								return;
							}
							//开启子线程进行网络连接
							new Thread(new Runnable() {
								//提交参数要放在按钮点击事件内部
								final String province = qustion_province.getSelectedItem().toString().trim();
								final String city = question_city.getSelectedItem().toString().trim();
								final String district = question_dirct.getSelectedItem().toString().trim();
								String path = Appconfig.BASE_URL+"/androidaction/sublimit.action?title="+title+"&content="+content+"&province="+province+"&city="+city+"&district="+district+"&counselor="+counselor+"&area="+area;
								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
								
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
				                        if(result.equals("successful")) {
				                        	Looper.prepare();//使消息循环初始化
				                        	//Looper作为线程之间的通信
				                        	//并且调用Looper.loop()使消息循环一直处于运行状态
				                        	//存储sp登录数据
				                        
				                            naticatToActivity(QuestionActivity.class);//跳转到专家咨询页面
				                            showToast("问题提交成功");
				                            question_title.setText("");
				                            question_content.setText("");
				                           
				                            Looper.loop();
				                        }else {
				                        	 Looper.prepare();
				                              Log.d("LoginActivity","run3: "+result);
				                              Toast.makeText(QuestionOnlineActivity.this,"问题提交失败",Toast.LENGTH_SHORT).show();
				                              Log.d("LoginActivity","run4: "+result);
				                              Looper.loop();//关闭线程
				                        }
				                        
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}

								}
							}).start();
						}

					});
			

	}
}
