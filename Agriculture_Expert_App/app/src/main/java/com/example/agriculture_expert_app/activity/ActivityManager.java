package com.example.agriculture_expert_app.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private static List<Activity> listActivity = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        listActivity.add(activity);
    }


    public static void removeActivity(Activity activity) {
        listActivity.remove(activity);
    }

    public static void finishAll() {
        for (int i = 0; i < listActivity.size(); i++) {
            Activity activity = listActivity.get(i);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
