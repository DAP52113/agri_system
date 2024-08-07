package com.example.agriculture_expert_app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Expert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
 * 专家信息咨询适配器
 * */
public class Expert_Questin_DetailAdapter extends BaseAdapter{

	private Context context;

	ArrayList<Expert> experts = new ArrayList<Expert>();

	public Expert_Questin_DetailAdapter(Context context, ArrayList<Expert> experts) {
		super();
		this.context = context;
		this.experts = experts;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return experts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return experts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		ViewHolder viewHolder = null;
		if(view == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.question_right, null);
			viewHolder = new ViewHolder();
			viewHolder.expert_name = (TextView)view.findViewById(R.id.expert_name);
			viewHolder.expert_prof = (TextView)view.findViewById(R.id.expert_prof);
			viewHolder.question_expert_eno = (TextView)view.findViewById(R.id.question_expert_eno);
			view.setTag(viewHolder);
		}
		// TODO Auto-generated method stub
		viewHolder = (ViewHolder)view.getTag();
		Expert expert = experts.get(position);
		viewHolder.question_expert_eno.setText(expert.getEno());
		viewHolder.expert_name.setText(expert.getEname());
		viewHolder.expert_prof.setText(expert.getEprof());
		return view;
	}

	class ViewHolder{
		private  TextView question_expert_eno;
		private  TextView expert_name;
		private  TextView expert_prof;
	}
	
	
}
