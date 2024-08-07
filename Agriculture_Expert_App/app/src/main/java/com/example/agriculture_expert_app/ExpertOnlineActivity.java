package com.example.agriculture_expert_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.adapter.RecentConversionsAdapter;
import com.example.agriculture_expert_app.bean.ChatMessage;
import com.example.agriculture_expert_app.bean.UserFireBase;
import com.example.agriculture_expert_app.listeners.ConversionListener;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.PreferenceManager;
import com.example.agriculture_expert_app.utils.PreferenceManagerExpert;
import com.example.agriculture_expert_app.utils.SysRole;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//专家在线咨询按钮
public class ExpertOnlineActivity extends Activity implements ConversionListener {

    private ImageView imageProfilePhoto;
    private ImageView image_SignOut;
    private TextView textName;
    private Bitmap bitmap;
    private String unname;
    private Bundle bundle;
    private ProgressBar progressBar;
    private FloatingActionButton selectExpertList;
    private PreferenceManager preferenceManager;
    private SharedPreferences sharePre;
    private List<ChatMessage> conversions;
    private FirebaseFirestore database;
    private RecentConversionsAdapter conversionsAdapter;
    private RecyclerView conversionsRecyclerView;
    private PreferenceManagerExpert preferenceManagerExpert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expert_online);
        initView();
        preferenceManager = new PreferenceManager(getApplicationContext());
        //获取XML元素UI操作

        setData();
        getToken();
        listenConversions();
    }

    private void initView(){
        imageProfilePhoto = (ImageView) findViewById(R.id.imageProfilePhoto);
        image_SignOut = (ImageView) findViewById(R.id.image_SignOut);
        conversionsRecyclerView = (RecyclerView)findViewById(R.id.conversionsRecyclerView);
        textName = (TextView) findViewById(R.id.textName);
        selectExpertList = (FloatingActionButton) findViewById(R.id.selectExpertList);
        sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
        unname = sharePre.getString("unname","");
        conversions = new ArrayList<>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        conversionsAdapter = new RecentConversionsAdapter(conversions,this);
        conversionsRecyclerView.setAdapter(conversionsAdapter);
        database = FirebaseFirestore.getInstance();
    }

    public void setData(){
        textName.setText(unname);
        //获取并转化为bitmap
        //bundle = this.getIntent().getExtras();
        sharePre = getSharedPreferences("configeperts",MODE_PRIVATE);
        String pathImage =  sharePre.getString(SysRole.KEY_IAMGE,"");

        try{
            bitmap = getUserImage(pathImage);
            imageProfilePhoto.setImageBitmap(bitmap);
        }catch (Exception e){
             e.printStackTrace();
        }
        //Bitmap bitmap1 = (Bitmap) getUserImage(preferenceManager.getString(SysRole.KEY_IAMGE));
        //imageProfilePhoto.setImageBitmap(bitmap1);

        //设置退出登录清理token
        image_SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ExpertOnlineActivity.this,"退出咨询",Toast.LENGTH_SHORT).show();
                FirebaseFirestore database = FirebaseFirestore.getInstance();
                DocumentReference documentReference =
                        database.collection(SysRole.KEY_COLLECTION_USERS).document(
                                preferenceManager.getString(SysRole.KEY_USER_ID)
                        );
                HashMap<String,Object> updates = new HashMap<>();
                updates.put(SysRole.KEY_TOKEN, FieldValue.delete());
                documentReference.update(updates)
                        .addOnSuccessListener(unused -> {
                           preferenceManager.clear();
                            Intent intent = new Intent(getApplicationContext(),ExpertnlineWelcome.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ExpertOnlineActivity.this,"退出登录失败",Toast.LENGTH_SHORT).show();
                        });
            }
        });

        //点击添加按钮,跳转到咨询用户列表上面
        selectExpertList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToUserList = new Intent(getApplicationContext(),UsersActivity.class);
                startActivity(intentToUserList);
            }
        });

    }

    private void listenConversions(){
        database.collection(SysRole.KEY_CONVERSIONS)
                .whereEqualTo(SysRole.KEY_SENDER_ID,preferenceManager.getString(SysRole.KEY_USER_ID))
                .addSnapshotListener(eventListener);
        database.collection(SysRole.KEY_CONVERSIONS)
                .whereEqualTo(SysRole.KEY_RECEIVER_ID,preferenceManager.getString(SysRole.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }


    private EventListener<QuerySnapshot> eventListener = ((value, error) -> {
       if(error != null){
           return;
       }
       if(value != null){
           for(DocumentChange documentChange : value.getDocumentChanges()){
               if(documentChange.getType() == DocumentChange.Type.ADDED){
                   String senderId = documentChange.getDocument().getString(SysRole.KEY_SENDER_ID);
                   String receiverId = documentChange.getDocument().getString(SysRole.KEY_RECEIVER_ID);
                   ChatMessage chatMessage = new ChatMessage();
                   chatMessage.setSenderId(senderId);
                   chatMessage.setReceiverId(receiverId);
                   if(preferenceManager.getString(SysRole.KEY_USER_ID).equals(senderId)){
                       chatMessage.setConversionImage(documentChange.getDocument().getString(SysRole.KEY_RECEIVER_IMAGE));
                       chatMessage.setConversionName(documentChange.getDocument().getString(SysRole.KEY_RECEIVER_NAME));
                       chatMessage.setConversionId(documentChange.getDocument().getString(SysRole.KEY_RECEIVER_ID));
                   }else{
                       chatMessage.setConversionImage(documentChange.getDocument().getString(SysRole.KEY_SENDER_IMAGE));
                       chatMessage.setConversionName(documentChange.getDocument().getString(SysRole.KEY_SENDER_NAME));
                       chatMessage.setConversionId(documentChange.getDocument().getString(SysRole.KEY_SENDER_ID));
                   }
                   chatMessage.setMessage(documentChange.getDocument().getString(SysRole.KEY_LAST_MESSAGE));
                   chatMessage.setDataObject(documentChange.getDocument().getDate(SysRole.KEY_TIMESTAMP));
                   conversions.add(chatMessage);
               }else if(documentChange.getType() == DocumentChange.Type.MODIFIED){
                   for(int i = 0;i<conversions.size();i++){
                       String senderId = documentChange.getDocument().getString(SysRole.KEY_SENDER_ID);
                       String receiverId = documentChange.getDocument().getString(SysRole.KEY_RECEIVER_ID);
                       if(conversions.get(i).getSenderId().equals(senderId) && conversions.get(i).getReceiverId().equals(receiverId)){
                           conversions.get(i).setMessage(documentChange.getDocument().getString(SysRole.KEY_LAST_MESSAGE));
                           conversions.get(i).setDataObject(documentChange.getDocument().getDate(SysRole.KEY_TIMESTAMP));
                           break;
                       }
                   }
               }
           }
           Collections.sort(conversions,(obj1,obj2)-> obj2.dataObject.compareTo(obj1.dataObject));
           conversionsAdapter.notifyDataSetChanged();
           conversionsRecyclerView.smoothScrollToPosition(0);
           conversionsRecyclerView.setVisibility(View.VISIBLE);
           progressBar.setVisibility(View.GONE);
       }
    });



    //获取登录token
    public void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    //登录进入更新token
    public void updateToken(String token){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference = database.collection(SysRole.KEY_COLLECTION_USERS).document(
          preferenceManager.getString(SysRole.KEY_USER_ID)
        );
        documentReference.update(SysRole.KEY_TOKEN,token)
                .addOnSuccessListener(unused -> Toast.makeText(ExpertOnlineActivity.this,"update success",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(ExpertOnlineActivity.this,"unable to update",Toast.LENGTH_SHORT).show());
    }


    private Bitmap getUserImage(String encodeImage){
        byte[] bytes = Base64.decode(encodeImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    //实现点击事件
    @Override
    public void onConversionClicker(UserFireBase userFireBase) {
        Intent intent = new Intent(getApplicationContext(),ChatConsultActivity.class);
        intent.putExtra(SysRole.KEY_USER, userFireBase);
        startActivity(intent);
    }
}