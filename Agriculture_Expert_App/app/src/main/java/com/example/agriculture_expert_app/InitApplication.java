package com.example.agriculture_expert_app;

import android.app.Application;

import com.example.agriculture_expert_app.utils.Model;

public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化model
        Model.getInstance().init(this);
    }
}
