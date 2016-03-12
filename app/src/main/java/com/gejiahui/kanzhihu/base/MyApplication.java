package com.gejiahui.kanzhihu.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by gejiahui on 2016/3/10.
 */
public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    public static Context getContext(){
        return mContext;
    }
}
