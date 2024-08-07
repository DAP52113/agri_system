package com.example.agriculture_expert_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agriculture_expert_app.utils.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//头像修改定义类
public class ImageUpdateActivity extends Activity implements View.OnClickListener{

    private ImageView ivAvatar;
    private Button take_photo;
    private Button photograph;
    private Button back_Up;
    private Uri imageUri;
    private String Image_info_64;
    private static final int REQUEST_CHOOSE_PHOTO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_image_update);

        initView();//获取相关的按钮

        initData();

    }

    //获取xml元素
    private void initView(){
        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        take_photo = (Button) findViewById(R.id.take_photo);
        photograph = (Button) findViewById(R.id.photograph);
        back_Up = (Button) findViewById(R.id.back_Up);
    }

    //初始化上一个activity传递的参数
    public void initData(){
        //渲染更换信息按钮
        Intent intent = getIntent();
        if(intent  != null && intent.getParcelableExtra("img_header") != null){
            Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("img_header");
            ivAvatar.setImageBitmap(bitmap);
        }
    }


    //设置点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.take_photo:
                //拍照
                TakePhoto();
                break;

            case R.id.photograph:
                //相册选择图片
                selectPhoto();
                break;

            case R.id.back_Up:
                //保存并返回,跳转到主页
                SaveData();//保存相关数据进入 sp中
                Toast.makeText(ImageUpdateActivity.this, "正在更新系统头像，需要返回到主页面", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ImageUpdateActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    //拍照点击事件
    public void TakePhoto(){
        try {
            //检查权限
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                //去拍照
                doTakePhoto();
            }else{
                //无权限去请求权限
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //实际操作函数
    public void doTakePhoto(){
        File imageTemp = new File(getExternalCacheDir(),"imageOut.jpg");
        if(imageTemp .exists()){
            imageTemp.delete();
        }
        try{//进行数据图片文件进行创建
            imageTemp.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //拿到文件的路径
        //判断所需要的SDK版本
        if(Build.VERSION.SDK_INT > 24){
            //FileProvider
            imageUri = FileProvider.getUriForFile(this,"com.example.agriculture_expert_app.fileprovider",imageTemp);
            //这里需要在ManiFest.xml中指出Provider提供器属性

        }else{
            imageUri = Uri.fromFile(imageTemp);
        }

        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,1);//开启拍照
    }


    //相册选择图片函数
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

    //相册选择实际操作函数
    public void doOpenPhoto(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    //返回函数
    //获取返回结果
    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){//代表打开照相机
            //获取拍摄的照片
            if(resultCode == RESULT_OK){
                //开启输入流,将文件转换为流二进制
                try{
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    //将二进制流解析为Bitmap图片对象
                    Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
                    //显示图像
                    ivAvatar.setImageBitmap(bitmap);
                    //对其进行转化为字符串数据进行存储
                    String Image_info = ImageUtils.imageToBase64(bitmap);

                    //存储
                    Image_info_64 = Image_info;

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }

            }
            //执行选择相册权限
        }else if(requestCode == REQUEST_CHOOSE_PHOTO){
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
                    ivAvatar.setImageBitmap(bitmap);
                    //转换为64位
                    String imageToBase64 = ImageUtils.imageToBase64(bitmap);
                    Image_info_64 = imageToBase64;
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
                ivAvatar.setImageBitmap(bitmap);
                String imageToBase64 = ImageUtils.imageToBase64(bitmap);
                Image_info_64 = imageToBase64;
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
            ivAvatar.setImageBitmap(bitmap);
            Image_info_64 = ImageUtils.imageToBase64(bitmap);

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
        if(requestCode == 1){//接受拍照权限
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doTakePhoto();
            }else{
                Toast.makeText(ImageUpdateActivity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
            }
            //申请读取相册权限
        }else if(requestCode == 0){
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doOpenPhoto();
            }else{
                Toast.makeText(ImageUpdateActivity.this, "无法读取相册，请先开启相册权限", Toast.LENGTH_LONG).show();
            }
        }
    }


    //保存数据进入
    public void SaveData(){
        SharedPreferences spfRecord = getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spfRecord.edit();
        edit.putString("Image_info_64",Image_info_64);
        edit.commit();
    }



}