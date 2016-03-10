package com.gejiahui.kanzhihu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gejiahui on 2016/3/10.
 */
public class BaseActivity extends AppCompatActivity {
    Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }

    @Override
    public void finish() {
        super.finish();
    }
}
