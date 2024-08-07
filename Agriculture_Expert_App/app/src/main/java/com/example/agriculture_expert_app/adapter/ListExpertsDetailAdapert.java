package com.example.agriculture_expert_app.adapter;

import java.util.List;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Expert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListExpertsDetailAdapert extends ArrayAdapter<Expert> {

	 private int resourceId;
	 public ListExpertsDetailAdapert(Context context, int resource, List<Expert> objects) {
	        super(context, resource, objects);
	        resourceId = resource;
	    }

	 @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 Expert expert = getItem(position);
		 View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

		TextView question_expert_eno = (TextView)convertView.findViewById(R.id.question_expert_eno);
			
		TextView expert_name = (TextView)convertView.findViewById(R.id.expert_name);
			
		TextView expert_prof = (TextView)convertView.findViewById(R.id.expert_prof);
			
		question_expert_eno.setText(expert.getEno());
		expert_name.setText(expert.getEname());
		expert_prof.setText(expert.getEprof());
		 
		 
		return convertView;
		
	}
	
}
