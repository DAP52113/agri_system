package com.example.agriculture_expert_app.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture_expert_app.bean.ChatMessage;
import com.example.agriculture_expert_app.bean.UserFireBase;
import com.example.agriculture_expert_app.databinding.ItemContainerRecentConversionBinding;
import com.example.agriculture_expert_app.listeners.ConversionListener;

import java.util.List;

public class RecentConversionsAdapter extends RecyclerView.Adapter<RecentConversionsAdapter.ConversionViewHolder>{

    private List<ChatMessage> chatMessages;
    private ConversionListener conversionListener;

    public RecentConversionsAdapter(List<ChatMessage> chatMessages,ConversionListener conversionListener){
        this.chatMessages = chatMessages;
        this.conversionListener = conversionListener;
    }


    class ConversionViewHolder extends RecyclerView.ViewHolder{
        ItemContainerRecentConversionBinding binding;

        ConversionViewHolder(ItemContainerRecentConversionBinding itemContainerRecentConversionBinding){
            super(itemContainerRecentConversionBinding.getRoot());
            binding = itemContainerRecentConversionBinding;
        }
        void setData(ChatMessage chatMessage){
            binding.imageProfile.setImageBitmap(getConversionImage(chatMessage.getConversionImage()));
            binding.textName.setText(chatMessage.getConversionName());
            binding.textRecentMessage.setText(chatMessage.getMessage());
            binding.getRoot().setOnClickListener(view -> {
                UserFireBase userFireBase = new UserFireBase();
                userFireBase.setId(chatMessage.getConversionId());
                userFireBase.setName(chatMessage.getConversionName());
                userFireBase.setIamge(chatMessage.getConversionImage());
                conversionListener.onConversionClicker(userFireBase);
            });
        }

    }



    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                ItemContainerRecentConversionBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }


    //字符集转化为bitmap
    private Bitmap getConversionImage(String encodeImage){
        byte[] bytes = Base64.decode(encodeImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

}
