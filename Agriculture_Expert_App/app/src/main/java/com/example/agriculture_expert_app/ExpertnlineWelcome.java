package com.example.agriculture_expert_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agriculture_expert_app.utils.Appconfig;
import com.example.agriculture_expert_app.utils.ImageUtils;
import com.example.agriculture_expert_app.utils.Model;
import com.example.agriculture_expert_app.utils.PreferenceManager;
import com.example.agriculture_expert_app.utils.PreferenceManagerExpert;
import com.example.agriculture_expert_app.utils.SysRole;
import com.google.firebase.firestore.FirebaseFirestore;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

//专家在线咨询跳转首页（欢迎页面）
public class ExpertnlineWelcome extends Activity {

    private EditText input_unname;
    private Spinner input_role;
    private SharedPreferences sharedPreferences;
    private PreferenceManagerExpert preferenceManagerExpert;
    private String unname;
    private Button login_to_chat;
    private boolean resultFromServer;
    private PreferenceManager preferenceManager;
    private RoundedImageView  imageProfile;
    private String encodeImage;
    private static final int REQUEST_CHOOSE_PHOTO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expertnline_welcome);
        //获取UI的相关元素
        initView();
        setData();

        preferenceManager = new PreferenceManager(getApplicationContext());
        //已登录
        if(sharedPreferences.getBoolean(SysRole.KEY_IS_SIGN_IN,false)){
            Intent intent = new Intent(getApplicationContext(),ExpertOnlineActivity.class);
            startActivity(intent);
        }
        //专家已登录
        preferenceManagerExpert = new PreferenceManagerExpert(getApplicationContext());
        if(preferenceManagerExpert.getBoolean(SysRole.KEY_IS_SIGN_IN)){
            Intent intent = new Intent(getApplicationContext(),ExpertOnlineActivity.class);
            startActivity(intent);
        }


    }

    //添加用户角色进入用户firebase数据库
    private void addDataToFireStoreAboutUser(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String,Object> user  = new HashMap<>();
        user.put(SysRole.KEY_NAME,unname);
        user.put(SysRole.KEY_ROLE,input_role.getSelectedItem().toString());
        user.put(SysRole.KEY_IAMGE,encodeImage);
        database.collection(SysRole.KEY_COLLECTION_USERS).add(user).addOnSuccessListener(documentReference -> {
            preferenceManager.putBoolean(SysRole.KEY_IS_SIGN_IN,true);
            preferenceManager.putString(SysRole.KEY_USER_ID,documentReference.getId());
            preferenceManager.putString(SysRole.KEY_NAME,unname);
            preferenceManager.putString(SysRole.KEY_ROLE,input_role.getSelectedItem().toString());
            preferenceManager.putString(SysRole.KEY_IAMGE,encodeImage);
        })
                .addOnFailureListener(exception->{
                    Toast.makeText(ExpertnlineWelcome.this,exception.getMessage(),Toast.LENGTH_SHORT).show();
        });
    }
    //添加专家数据进入firebase数据库
    private void addDataToFireStoreAboutExpert(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String,Object> expert  = new HashMap<>();
        expert.put(SysRole.KEY_NAME,unname);
        expert.put(SysRole.KEY_ROLE,input_role.getSelectedItem().toString());
        expert.put(SysRole.KEY_IAMGE,encodeImage);
        database.collection(SysRole.KEY_COLLECTION_EXPERTS).add(expert).addOnSuccessListener(documentReference -> {
            preferenceManagerExpert.putBoolean(SysRole.KEY_IS_SIGN_IN,true);
            preferenceManagerExpert.putString(SysRole.KEY_USER_ID,documentReference.getId());
            preferenceManagerExpert.putString(SysRole.KEY_NAME,unname);
            preferenceManagerExpert.putString(SysRole.KEY_ROLE,input_role.getSelectedItem().toString());
            preferenceManagerExpert.putString(SysRole.KEY_IAMGE,encodeImage);
        })
                .addOnFailureListener(exception->{
                    Toast.makeText(ExpertnlineWelcome.this,exception.getMessage(),Toast.LENGTH_SHORT).show();
                });
    }


    //相册选择图片
    public void selectPhoto(){
        try {
            //检查权限
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                //去打开相册
                doOpenPhoto();
            }else{
                //无权限去请求权限
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //操作函数相册
    public void doOpenPhoto(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }




    public void initView(){
        input_unname = (EditText) findViewById(R.id.input_unname);
        input_role = (Spinner) findViewById(R.id.input_role);
        login_to_chat = (Button)findViewById(R.id.login_to_chat);
        imageProfile = (RoundedImageView)findViewById(R.id.imageProfile);
        sharedPreferences = getSharedPreferences("configeperts",MODE_PRIVATE);
        //获取数据
        unname = sharedPreferences.getString("unname","");
    }


    public void saveImageInfo(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SysRole.KEY_IAMGE,encodeImage);
        editor.putBoolean(SysRole.KEY_IS_SIGN_IN,true);//已登录存入
        editor.commit();
    }

    //获取合法性
    public boolean getIsTrue(String unname){
        //设置下拉页面的点击时间
        input_role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String role = input_role.getSelectedItem().toString();
                if(role.equals(SysRole.ROLE_USER)){//选择的用户角色进行查询数据库
                    //开启线程进行查询
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String path= Appconfig.BASE_URL+"/androidaction/checkRoleIsTrue.action?unname="+unname+"&role="+role;
                            try {
                                try{
                                    URL url = new URL(path); //新建url并实例化
                                    //进行与服务器端的连接初始化
                                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                    connection.setRequestMethod("GET");//获取服务器数据
                                    connection.setReadTimeout(5000);//设置读取超时的毫秒数
                                    connection.setConnectTimeout(5000);//设置连接超时的毫秒数
                                    InputStream in = connection.getInputStream();//获取输入流
                                    //字符输入流中读取文本并缓冲字符
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                                    String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                                    Looper.prepare();//使消息循环初始化
                                    if (!result.equals(SysRole.RESULT_SUCCESS)){
                                        resultFromServer = false;
                                    }else {
                                        resultFromServer = true;
                                    }
                                    Looper.loop();
                                }catch (MalformedURLException e){}
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        return resultFromServer;
    }


    public void setData(){
        //设置用户
        input_unname.setText(unname);
        input_unname.setEnabled(false);

        //点击更换头像
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPhoto();
            }
        });



        //登录系统的点击按钮事件
        login_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(encodeImage == null){
                    Toast.makeText(ExpertnlineWelcome.this,"请选择聊天头像",Toast.LENGTH_SHORT).show();
                }

                resultFromServer = getIsTrue(unname);
                if(resultFromServer == true){//匹配成功
                    Toast.makeText(ExpertnlineWelcome.this,"用户角色匹配成功",Toast.LENGTH_SHORT).show();
                    //加入数据到firebase数据库(用户名+角色+头像)
                    if(input_role.getSelectedItem().toString().equals("用户")){
                        //进行firebase查询若已经存在数据则直接登录,不存在数据进行添加数据
                        FirebaseFirestore database_query = FirebaseFirestore.getInstance();
                        database_query.collection(SysRole.KEY_COLLECTION_USERS)
                                .whereEqualTo(SysRole.KEY_NAME,unname)
                                .whereEqualTo(SysRole.KEY_ROLE,input_role.getSelectedItem().toString())
                                .get()
                                .addOnCompleteListener(task -> {
                                   if(task.isSuccessful() && task.getResult()!=null && task.getResult().getDocuments().size() > 0){
                                       //说明已经存在了直接登录
                                       Toast.makeText(ExpertnlineWelcome.this,"登录成功",Toast.LENGTH_SHORT).show();
                                   }else{
                                       addDataToFireStoreAboutUser();
                                       Toast.makeText(ExpertnlineWelcome.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                                   }
                                });

                        //preferenceManager.putBoolean(SysRole.KEY_IS_SIGN_IN,true);
                        //Toast.makeText(ExpertnlineWelcome.this,"添加数据成功",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ExpertnlineWelcome.this,ExpertOnlineActivity.class);
                        saveImageInfo();
                        //bundle.putString(SysRole.KEY_IAMGE,encodeImage);
                        //intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        addDataToFireStoreAboutExpert();
                        //preferenceManager.putBoolean(SysRole.KEY_IS_SIGN_IN,true);
                        Intent intent = new Intent(ExpertnlineWelcome.this,ExpertOnlineActivity.class);
                        saveImageInfo();
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(ExpertnlineWelcome.this,"用户角色匹配失败",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //返回函数
    //获取返回结果
    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CHOOSE_PHOTO){
            if(Build.VERSION.SDK_INT < 19){
                Uri uri = data.getData();//获取操作数据
                String path = null;
                //操作数据库进行数据查询
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){//不为null
                    if(cursor.moveToFirst()){//moveToFirst这个函数使得下标被重置为0
                        path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    }
                    cursor.close();
                }
                //找到了相册的路径
                if(path != null){
                    Bitmap bitmap = BitmapFactory.decodeFile(path);//格式转换
                    imageProfile.setImageBitmap(bitmap);
                    //转换为64位
                    String imageToBase64 = ImageUtils.imageToBase64(bitmap);
                    encodeImage = imageToBase64;//转化为String字符集
                }
            }else{//当api大于19
                handleImage(data);
            }

        }
    }

    @TargetApi(19)
    private void handleImage(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String documentId = DocumentsContract.getDocumentId(uri);
            if(TextUtils.equals(uri.getAuthority(),"com.android.providers.media.documents")){
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                if (documentId != null && documentId.startsWith("msf:")) {
                    resolveMSFContent(uri, documentId);
                    return;
                }
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }

            if (imagePath != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                imageProfile.setImageBitmap(bitmap);
                String imageToBase64 = ImageUtils.imageToBase64(bitmap);
                encodeImage = imageToBase64;
            }
        }

    }


    private void resolveMSFContent(Uri uri, String documentId) {

        File file = new File(getCacheDir(), "temp_file" + getContentResolver().getType(uri).split("/")[1]);

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageProfile.setImageBitmap(bitmap);
            encodeImage = ImageUtils.imageToBase64(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //获取权限函数
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doOpenPhoto();
            }else{
                Toast.makeText(ExpertnlineWelcome.this, "无法读取相册，请先开启相册权限", Toast.LENGTH_LONG).show();
            }
        }
    }



}