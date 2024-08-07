package com.example.agriculture_expert_app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.agriculture_expert_app.activity.AppBaseactivity;
import com.example.agriculture_expert_app.adapter.Expert_AreaAdapter;
import com.example.agriculture_expert_app.adapter.SearchAdapter;
import com.example.agriculture_expert_app.bean.Expert;
import com.example.agriculture_expert_app.bean.Infomation;

import com.example.agriculture_expert_app.utils.Appconfig;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//用户在线咨询anctivity控制器
public class QuestionActivity extends AppBaseactivity  implements View.OnClickListener{

	private ListView listview;
	private Expert_AreaAdapter expertAdapter;
	private Button btn_photoandword_quesion;
	private Button btn_phone_quesion;
	private ArrayList<Expert> experts;
	private LinearLayout ll_left;
	private LinearLayout ll_right;
	private TextView question_expert_eno;
	private TextView zixun_record;



	private Handler hand  = new Handler(Looper.getMainLooper()){

		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message message) {
			if(message.what == 1)
			{

				ll_right.removeAllViews();//刷新右侧布局
				ArrayList<Expert> experts = (ArrayList<Expert>) message.obj;

				if(experts != null) {
					for(Expert expert : experts) {
						addRightExpert(expert);
					}
				}else {
					Toast.makeText(QuestionActivity.this,"未查询到相关专家",Toast.LENGTH_SHORT).show();
					ll_right.removeAllViews();
					View view = getLayoutInflater().inflate(R.layout.view_empty, null);
					ll_right.addView(view);
				}
			}
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_question);

		//setRightText("咨询记录");
		initLeft();
		initRight();
		question_expert_eno = (TextView)findViewById(R.id.question_expert_eno);

	}
	//设置右侧标题栏
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.right_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case R.id.appoint_record:
				//跳转到预约记录页面
				Toast.makeText(QuestionActivity.this,"正在跳转到预约记录页面...",Toast.LENGTH_SHORT).show();
				break;
			case R.id.online_consult:
				//跳转到在线咨询页面
				Toast.makeText(QuestionActivity.this,"正在跳转到在线咨询页面...",Toast.LENGTH_SHORT).show();
				break;
			case R.id.consult_reply:
				//跳转到咨询回复页面
				Toast.makeText(QuestionActivity.this,"正在跳转到咨询回复页面...",Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	//添加全部专家领域
	public void initLeft() {
		ll_left = (LinearLayout)findViewById(R.id.ll_left);
		addLeftArea("种植");
		addLeftArea("养殖");
		addLeftArea("园艺");
		addLeftArea("植保");
		addLeftArea("农业工程");
		addLeftArea("农业经济");
		addLeftArea("政策法规");
		addLeftArea("其他");
	}

	//增加一个专家领域
	public void addLeftArea(final String area) {
		View view = getLayoutInflater().inflate(R.layout.expert_area_itemview, null);
		TextView expert_area_name = (TextView)view.findViewById(R.id.expert_area_name);
		expert_area_name.setText(area);
		//设置点击事件颜色的改变
		if(area.equals("种植")) {
			expert_area_name.setTextColor(0xff4da8ff);
		}
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i = 0;i<ll_left.getChildCount();i++) {
					View child = ll_left.getChildAt(i);//选择哪个线性布局
					TextView tv = (TextView)child.findViewById(R.id.expert_area_name);
					//选中内容
					if(tv.getText().toString().equals(area)) {
						tv.setTextColor(0xff4da8ff);
						child.setBackgroundColor(0xfff8f8f8);
					}else {
						tv.setTextColor(0xff666666);
						child.setBackgroundColor(0xffffffff);
					}
				}
				getExperts(area);

			}

		});
		ll_left.addView(view);
	}

	//设置右侧布局
	private void initRight() {
		// TODO Auto-generated method stub
		ll_right = (LinearLayout)findViewById(R.id.ll_right);

		getExperts("种植");
		Log.d("发查询专家请求了","种植");

	}

	//添加单个专家对象信息
	public void addRightExpert(Expert expert) {
		View view = getLayoutInflater().inflate(R.layout.question_right, null);
		TextView tv =  (TextView)view.findViewById(R.id.question_expert_eno);
		tv.setText(expert.getEname().toString());
		final String eno = (String)expert.getEno().toString();
		final String area = (String)expert.getArea().toString();
		//在线咨询按钮调用
		view.findViewById(R.id.btn_photoandword_quesion).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(QuestionActivity.this,QuestionOnlineActivity.class);
				intent.putExtra("eno", eno);
				intent.putExtra("area", area);
				startActivity(intent);
			}
		});

		//电话咨询按钮调用
		view.findViewById(R.id.btn_phone_quesion).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				//调用打电话按钮
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"+eno.toString()));
				startActivity(intent);
			}
		});

		ll_right.addView(view);
	}


	//未查询到专家的相关信息
	public void showEmpty() {
		ll_right.removeAllViews();
		View view = getLayoutInflater().inflate(R.layout.view_empty, null);

		ll_right.addView(view);
	}


	//查询专家列表，没有记录进行处理
	private void getExperts(final String area) {
		List<Expert> experts =  new ArrayList<Expert>();

		new Thread(new Runnable() {

			InputStream input = null;//设置输入流

			String data ="";
			@Override
			public void run() {
				try {
				// TODO Auto-generated method stub
					HttpURLConnection conn;
					conn = (HttpURLConnection)new URL(Appconfig.BASE_URL+"/androidaction/getexpertbyarea.action?area="+java.net.URLEncoder.encode(area)).openConnection();

					conn.setRequestMethod("GET");
					conn.setReadTimeout(3000);//设置读取超时的毫秒数
					conn.setConnectTimeout(3000);//设置连接超时的毫秒数

					conn.setDoInput(true);
					conn.setUseCaches(false);

					input  = conn.getInputStream();//得到连接输入流
					InputStreamReader inputStreamReader = new InputStreamReader(input);
					//为得到JSON数据创建BufferedReader
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

					String line = "";

					while((line = bufferedReader.readLine()) != null) {
						data  = data + line+"\n";
					}

					//转换JSON
					JSONArray jsoninfoArray = new JSONArray(data);
					for(int i = 0;i<jsoninfoArray.length();i++) {
						JSONObject jsonObject = (JSONObject)jsoninfoArray.get(i);
						Expert expert = new Expert();
						//为expert实体类设置返回的json属性信息
						expert.setEno(jsonObject.getString("eno"));
						expert.setEname(jsonObject.getString("ename"));
						expert.setArea(jsonObject.getString("area"));
						expert.setProvince(jsonObject.getString("province"));
						expert.setCity(jsonObject.getString("city"));
						expert.setDistrict(jsonObject.getString("district"));
						experts.add(expert);
					}
					Log.d("请求后端之后的结果experts",experts.toString());
					Message message = new Message();
					message.what=1;
					message.obj = experts;
					hand.sendMessage(message);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}

	//通过数据库领域去查询专家信息
	private ArrayList<Expert> GetExpertByArea(final String area) {
//		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				List<Expert> experts =  new ArrayList<Expert>();
				// TODO Auto-generated method stub
				String path = Appconfig.BASE_URL+"/androidaction/getexpertbyarea.action?area="+area;
				try {
					String data ="";
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");//获取服务器数据
					conn.setReadTimeout(5000);//设置读取超时的毫秒数
					conn.setConnectTimeout(5000);//设置连接超时的毫秒数
					InputStream in = conn.getInputStream();//获取输入流
					//字符输入流中读取文本并缓冲字符
					InputStreamReader inputReader = new InputStreamReader(in);
					//为输出创建bufferedReader
					BufferedReader bufferedReader = new BufferedReader(inputReader);
					String line = "";

					while((line = bufferedReader.readLine()) != null) {
						data  = data + line+"\n";
					}
					//转换JSON
					JSONArray jsoninfoArray = new JSONArray(data);
					for(int i = 0;i<jsoninfoArray.length();i++) {
						JSONObject jsonObject = (JSONObject)jsoninfoArray.get(i);
						Expert expert = new Expert();
						//为expert实体类设置返回的json属性信息
						expert.setEno(jsonObject.getString("eno"));
						expert.setEname(jsonObject.getString("ename"));
						expert.setArea(jsonObject.getString("area"));
						expert.setProvince(jsonObject.getString("province"));
						expert.setCity(jsonObject.getString("city"));
						expert.setDistrict(jsonObject.getString("district"));
						experts.add(expert);
					}


				} catch (Exception e) {
					// TODO: handle exception
				}


			}
		}).start();


		return experts;
	}


}
