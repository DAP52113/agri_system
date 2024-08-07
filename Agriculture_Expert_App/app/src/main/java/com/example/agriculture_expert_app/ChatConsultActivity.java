package com.example.agriculture_expert_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.adapter.ChatAdapter;
import com.example.agriculture_expert_app.bean.ChatMessage;
import com.example.agriculture_expert_app.bean.UserFireBase;
import com.example.agriculture_expert_app.utils.PreferenceManager;
import com.example.agriculture_expert_app.utils.SysRole;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatConsultActivity extends AppCompatActivity {

    private View viewBackground;
    private AppCompatImageView imageBack;
    private AppCompatImageView imageInfo;
    private TextView textName;
    private RecyclerView chatRecycleView;
    private ProgressBar processBar;
    private FrameLayout layoutSend;
    private EditText inputMessage;
    private UserFireBase receiverUser;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;
    private Bitmap bitmap_user;
    private String conversionId = null;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database_user_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_chat_consult);
        initView();//获取相关参数
        loadReceiverDetails();//加载用户名
        setListener();//点击事件处理函数
        listenerMessage();
    }

    private Bitmap getBitmapFromEncodeString(String encodeImage){
        byte[] bytes = Base64.decode(encodeImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    private void initView(){
        viewBackground = (View) findViewById(R.id.viewBackground);
        imageBack = (AppCompatImageView) findViewById(R.id.imageBack);
        imageInfo =(AppCompatImageView) findViewById(R.id.imageInfo);
        textName = (TextView) findViewById(R.id.textName);
        chatRecycleView = (RecyclerView) findViewById(R.id.chatRecycleView);
        layoutSend = (FrameLayout)findViewById(R.id.layoutSend);
        processBar = (ProgressBar) findViewById(R.id.processBar);
        inputMessage = (EditText) findViewById(R.id.inputMessage);
        preferenceManager = new PreferenceManager(getApplicationContext());
        chatMessages = new ArrayList<>();
        try{
            bitmap_user = getBitmapFromEncodeString(receiverUser.getIamge());
        }catch (Exception e){
            e.printStackTrace();
        }
        chatAdapter = new ChatAdapter(
                chatMessages,bitmap_user,
                preferenceManager.getString(SysRole.KEY_USER_ID)
        );
        chatRecycleView.setAdapter(chatAdapter);
        database_user_chat = FirebaseFirestore.getInstance();
    }

    private void loadReceiverDetails(){
        //收到数据
        receiverUser = (UserFireBase) getIntent().getSerializableExtra(SysRole.KEY_USER);
        textName.setText(receiverUser.getName());
    }

    private void setListener(){
        //退出聊天点击事件
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //发送信息点击事件
        layoutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });


    }

    //消息发送核心函数
    private void sendMessage(){
        HashMap<String,Object> message = new HashMap<>();
        //发送为用户id
        message.put(SysRole.KEY_SENDER_ID,preferenceManager.getString(SysRole.KEY_USER_ID));
        message.put(SysRole.KEY_RECEIVER_ID,receiverUser.getId());//接收者为接收者数据信息
        message.put(SysRole.KEY_MESSAGE,inputMessage.getText().toString());
        message.put(SysRole.KEY_TIMESTAMP,new Date());
        //数据库添加消息
        database_user_chat.collection(SysRole.KEY_COLLECT_CHAT).add(message);
        if(conversionId != null){
            updateConversion(inputMessage.getText().toString());
        }else{
            HashMap<String,Object> conversion = new HashMap<>();
            conversion.put(SysRole.KEY_SENDER_ID,preferenceManager.getString(SysRole.KEY_USER_ID));
            conversion.put(SysRole.KEY_SENDER_IMAGE,preferenceManager.getString(SysRole.KEY_IAMGE));
            conversion.put(SysRole.KEY_SENDER_NAME,preferenceManager.getString(SysRole.KEY_NAME));
            conversion.put(SysRole.KEY_RECEIVER_ID,receiverUser.getId());
            conversion.put(SysRole.KEY_RECEIVER_NAME,receiverUser.getName());
            conversion.put(SysRole.KEY_RECEIVER_IMAGE,receiverUser.getIamge());
            conversion.put(SysRole.KEY_LAST_MESSAGE,inputMessage.getText().toString());
            conversion.put(SysRole.KEY_TIMESTAMP,new Date());
            addConversion(conversion);
        }
        inputMessage.setText(null);
    }

    //查询firebase数据库的聊天记录
    private void listenerMessage(){
        database_user_chat.collection(SysRole.KEY_COLLECT_CHAT)
                .whereEqualTo(SysRole.KEY_SENDER_ID,preferenceManager.getString(SysRole.KEY_USER_ID))
                .whereEqualTo(SysRole.KEY_RECEIVER_ID,receiverUser.getId())
                .addSnapshotListener(eventListener);
        database_user_chat.collection(SysRole.KEY_COLLECT_CHAT)
                .whereEqualTo(SysRole.KEY_SENDER_ID,receiverUser.getId())
                .whereEqualTo(SysRole.KEY_RECEIVER_ID,preferenceManager.getString(SysRole.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }

    //设置相关的信息遍历数据
    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
       if(error != null){
           return;
       }
       if(value != null){
           int count = chatMessages.size();
           for(DocumentChange documentChange : value.getDocumentChanges()){
               if(documentChange.getType() == DocumentChange.Type.ADDED){
                   ChatMessage chatMessage = new ChatMessage();
                   chatMessage.setSenderId(documentChange.getDocument().getString(SysRole.KEY_SENDER_ID));
                   chatMessage.setReceiverId(documentChange.getDocument().getString(SysRole.KEY_RECEIVER_ID));
                   chatMessage.setMessage(documentChange.getDocument().getString(SysRole.KEY_MESSAGE));
                   chatMessage.setDateTime(getReadableDateTime(documentChange.getDocument().getDate(SysRole.KEY_TIMESTAMP)));
                   chatMessage.setDataObject(documentChange.getDocument().getDate(SysRole.KEY_TIMESTAMP));
                   chatMessages.add(chatMessage);//添加入list
               }
           }
           Collections.sort(chatMessages,(obj1,obj2)-> obj1.dataObject.compareTo(obj2.dataObject));
           if(count == 0){
               chatAdapter.notifyDataSetChanged();
           }else{
               chatAdapter.notifyDataSetChanged();
               chatRecycleView.smoothScrollToPosition(chatMessages.size()-1);
           }
           chatRecycleView.setVisibility(View.VISIBLE);
       }
       processBar.setVisibility(View.GONE);
       if(conversionId == null){
           checkForConversion();
       }
    };

    //时间格式化
    private String getReadableDateTime(Date date){
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }

    private void checkForConversion(){
        if(chatMessages.size() != 0){
            checkForConversionRemotely(preferenceManager.getString(SysRole.KEY_USER_ID),receiverUser.getId());
            checkForConversionRemotely(receiverUser.getId(), preferenceManager.getString(SysRole.KEY_USER_ID));

        }
    }

    private void addConversion(HashMap<String,Object> conversion){
        database_user_chat.collection(SysRole.KEY_CONVERSIONS)
                .add(conversion)
                .addOnSuccessListener(documentReference -> conversionId = documentReference.getId());

    }


    private void updateConversion(String message){
        DocumentReference documentReference = database_user_chat.collection(SysRole.KEY_CONVERSIONS).document(conversionId);
        documentReference.update(
                SysRole.KEY_LAST_MESSAGE,message,
                SysRole.KEY_TIMESTAMP,new Date()
        );
    }


    private void checkForConversionRemotely(String senderId,String receiverId){
        database_user_chat.collection(SysRole.KEY_CONVERSIONS)
                .whereEqualTo(SysRole.KEY_SENDER_ID,senderId)
                .whereEqualTo(SysRole.KEY_RECEIVER_ID,receiverId)
                .get()
                .addOnCompleteListener(completeListener);
    }

    private OnCompleteListener<QuerySnapshot> completeListener = task -> {
        if(task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0){
            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
            conversionId = documentSnapshot.getId();
        }
    };

}