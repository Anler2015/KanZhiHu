package com.gejiahui.kanzhihu.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Created by gejiahui on 2016/3/10.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Fresco.initialize(this);
       // Logger.init().hideThreadInfo().setMethodCount(1).setLogLevel(LogLevel.NONE);
    }

    public static Context getContext() {
        return mContext;
    }
}
