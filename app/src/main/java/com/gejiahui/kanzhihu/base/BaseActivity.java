package com.gejiahui.kanzhihu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gejiahui.kanzhihu.R;

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
       overridePendingTransition(R.anim.anim_right_in,R.anim.anim_right_out);
    }

    public void replaceFragment(int id, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();

    }
}
