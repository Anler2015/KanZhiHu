package com.gejiahui.kanzhihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.BaseActivity;
import com.gejiahui.kanzhihu.utils.ThemeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/30.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("设置");
    }
}
