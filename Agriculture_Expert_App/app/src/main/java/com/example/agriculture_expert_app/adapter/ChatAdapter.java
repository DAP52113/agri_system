package com.example.agriculture_expert_app.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture_expert_app.bean.ChatMessage;
import com.example.agriculture_expert_app.databinding.ItemContainerReceivedMessageBinding;
import com.example.agriculture_expert_app.databinding.ItemContainerSendMessageBinding;

import java.util.List;

//聊天适配器
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ChatMessage> chatMessages;
    private Bitmap receiverProfileImage;
    private String senderId;

    public static int VIEW_TYPE_SENT=1;//发送
    public static int VIEW_TYPE_RECEIVED=2;//接受


    //构造方法
    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }



    static  class sentMessageViewHolder extends RecyclerView.ViewHolder{

        private ItemContainerSendMessageBinding binding;

        sentMessageViewHolder(ItemContainerSendMessageBinding itemContainerSendMessageBinding){
            super(itemContainerSendMessageBinding.getRoot());
            binding = itemContainerSendMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.getMessage());
            binding.textDateTime.setText(chatMessage.getDateTime());
        }

    }

    //接收信息
    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{

        private ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding){
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage chatMessage,Bitmap receiverProfileImage){
            binding.textMessage.setText(chatMessage.getMessage());
            binding.textDateTime.setText(chatMessage.getDateTime());
            binding.imageProfileUserPhoto.setImageBitmap(receiverProfileImage);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){//若是发送信息渲染发送信息适配器
            return new sentMessageViewHolder(
                    ItemContainerSendMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }else{
            return new ReceivedMessageViewHolder(//接受信息渲染适配器
                    ItemContainerReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_SENT){
            ((sentMessageViewHolder)holder).setData(chatMessages.get(position));
        }else{
            ((ReceivedMessageViewHolder)holder).setData(chatMessages.get(position),receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).getSenderId().equals(senderId)){
            return VIEW_TYPE_SENT;
        }else{
            return VIEW_TYPE_RECEIVED;
        }
    }


}
