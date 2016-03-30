package com.gejiahui.kanzhihu.utils;

import android.widget.Toast;

import com.gejiahui.kanzhihu.base.MyApplication;

/**
 * Created by gejiahui on 2016/3/30.
 */
public class ToastUtils  {

    public static void show( CharSequence sequence) {
        Toast.makeText(MyApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
    }

}
