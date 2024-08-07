package com.example.agriculture_expert_app.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agriculture_expert_app.R;
import com.example.agriculture_expert_app.bean.UserFireBase;
import com.example.agriculture_expert_app.databinding.ItemContainerUserBinding;
import com.example.agriculture_expert_app.listeners.UserListener;
import com.example.agriculture_expert_app.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

//firebase数据库渲染数据
public class UsersFireBaseAdapter extends RecyclerView.Adapter<UsersFireBaseAdapter.UserViewHolder>{

    //创建list集合
    private  List<UserFireBase> users = new ArrayList<>();
    private UserListener userListener;

    public UsersFireBaseAdapter(List<UserFireBase> users,UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false);
        return new UserViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        ItemContainerUserBinding binding;

        UserViewHolder(ItemContainerUserBinding itemContainerUserBinding){
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(UserFireBase user){
            binding.textName.setText(user.getName());
            binding.textRole.setText(user.getRole());
            binding.imageProfile.setImageBitmap(getUserImage(user.getIamge()));
            binding.getRoot().setOnClickListener(view -> userListener.onUserClicked(user));
        }
    }


    private Bitmap getUserImage(String encodeImage){
        byte[] bytes = Base64.decode(encodeImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);

    }
}
