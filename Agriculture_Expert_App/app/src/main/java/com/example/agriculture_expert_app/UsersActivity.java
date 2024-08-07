package com.example.agriculture_expert_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.agriculture_expert_app.adapter.UsersFireBaseAdapter;
import com.example.agriculture_expert_app.bean.UserFireBase;
import com.example.agriculture_expert_app.listeners.UserListener;
import com.example.agriculture_expert_app.utils.PreferenceManager;
import com.example.agriculture_expert_app.utils.SysRole;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersActivity extends AppCompatActivity implements UserListener {

    private ImageView imageBack;
    private RecyclerView userRecyclerView;
    private ProgressBar progreddBar;
    private List<UserFireBase> userFireBasesByRemoveSame;
    private TextView textErrorMessage;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_users);
        preferenceManager = new PreferenceManager(getApplicationContext());
        initView();
        getUser();
        setData();//进行数据的跳转设置
    }

    private void initView(){
        imageBack = (ImageView) findViewById(R.id.imageBack);
        userRecyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        progreddBar = (ProgressBar) findViewById(R.id.progreddBar);
        textErrorMessage = (TextView) findViewById(R.id.textErrorMessage);
    }

    private void getUser(){
        loading(true);//设置为可见进度条
        //查询数据库
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(SysRole.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);//查询到数据后取消进度条
                    String currentUserId = preferenceManager.getString(SysRole.KEY_USER_ID);
                    if(task.isSuccessful()  && task.getResult() != null){
                        List<UserFireBase> userFireBases = new ArrayList<>();
                        //遍历数据
                        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
//                            if(currentUserId.equals(queryDocumentSnapshot.getId())){
//                                continue;
//                            }
                            UserFireBase userFireBase = new UserFireBase();
                            userFireBase.setRole(queryDocumentSnapshot.getString("role"));
                            userFireBase.setName(queryDocumentSnapshot.getString(SysRole.KEY_NAME));
                            userFireBase.setIamge(queryDocumentSnapshot.getString(SysRole.KEY_IAMGE));
                            userFireBase.setToken(queryDocumentSnapshot.getString(SysRole.KEY_TOKEN));
                            userFireBase.setId(queryDocumentSnapshot.getId());//获取相关id
                            userFireBases.add(userFireBase);
                        }
                        //进行数据去重
                        Set<UserFireBase> removeSetData = new HashSet<>();
                        removeSetData.addAll(userFireBases);

                        //转化为arrylist
                        userFireBasesByRemoveSame = new ArrayList<UserFireBase>(removeSetData);

                        if(userFireBasesByRemoveSame.size() > 0 ){
                            //进行渲染
                            UsersFireBaseAdapter usersFireBaseAdapter = new UsersFireBaseAdapter(userFireBasesByRemoveSame,this);
                            userRecyclerView.setAdapter(usersFireBaseAdapter);
                            userRecyclerView.setVisibility(View.VISIBLE);
                        }else{
                            showErrorMessage();
                        }
                    }else{
                        showErrorMessage();
                    }
                });
    }


    //设置加载状态
    private void loading(Boolean isLoading){
        if(isLoading){
            progreddBar.setVisibility(View.VISIBLE);//可见
        }else{
            progreddBar.setVisibility(View.INVISIBLE);//设置为不可见
        }
    }

    private void showErrorMessage(){
        textErrorMessage.setText(String.format("%s","用户列表为空"));
        textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void setData(){
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsersActivity.this,ExpertOnlineActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onUserClicked(UserFireBase userFireBase) {
        //新建intent
        Intent intent = new Intent(getApplicationContext(),ChatConsultActivity.class);
        intent.putExtra(SysRole.KEY_USER,userFireBase);//携带相关的参数
        startActivity(intent);
        finish();
    }
}