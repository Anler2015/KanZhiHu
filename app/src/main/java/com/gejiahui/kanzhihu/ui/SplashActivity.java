package com.gejiahui.kanzhihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.BaseActivity;
import com.gejiahui.kanzhihu.utils.ThemeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/29.
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.shimmer_view_container)
    ShimmerFrameLayout  shimmer;
    @Bind(R.id.background)
    RelativeLayout background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        background.setBackgroundColor(ThemeUtils.getThemeColor());
        shimmer.startShimmerAnimation();

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);//跳转到MainActivity
                SplashActivity.this.finish();//结束SplashActivity
            }
        }, 3000);//给postDelayed()方法传递延迟参数
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.anim_none);
    }

}
