package com.example.agriculture_expert_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.Reply;

import java.util.List;

public class SetReplyContentAdapter extends BaseAdapter {

    private List<Reply> replies;
    private Context context;

    public SetReplyContentAdapter(List<Reply> replies,Context context){
        super();
        this.replies = replies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return replies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.expert_reply_more_detail_item,parent,false);

        }

        TextView reply_expert_name = (TextView) view.findViewById(R.id.reply_expert_name);
        reply_expert_name.setText(replies.get(position).getExpert()+" : ");

        TextView reply_content = (TextView) view.findViewById(R.id.reply_content);
        reply_content.setText(replies.get(position).getContent());

        return view;
    }

    public void addComment(Reply reply){
        replies.add(reply);
//        notifyDataSetChanged();
    }


}
