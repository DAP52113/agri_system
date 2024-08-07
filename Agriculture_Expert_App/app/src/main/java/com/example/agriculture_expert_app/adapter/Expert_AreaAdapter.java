package com.example.agriculture_expert_app.adapter;

import com.example.agriculture_expert_app.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//农业专家领域适配器
public class Expert_AreaAdapter extends BaseAdapter{

	private Context context;
	private String[] experts_area;
	//构造函数
	public Expert_AreaAdapter(Context context, String[] experts_area) {
		super();
		this.context = context;
		this.experts_area = experts_area;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return experts_area.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return experts_area[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position,  View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.expert_area_itemview, parent, false);
		}
		TextView expert_area_name = (TextView)convertView.findViewById(R.id.expert_area_name);
		expert_area_name.setText(experts_area[position].toString().trim());
		expert_area_name.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View convertView = LayoutInflater.from(context).inflate(R.layout.expert_area_itemview, null);
				convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
			}
		});
		return convertView;
	}

}
