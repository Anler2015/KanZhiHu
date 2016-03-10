package com.gejiahui.kanzhihu.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by gejiahui on 2016/3/10.
 */
public class MyApplication extends Application {
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }

    public Context getApplicationContext(){
        return mContext;
    }
}
