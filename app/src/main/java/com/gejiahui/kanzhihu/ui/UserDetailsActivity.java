package com.gejiahui.kanzhihu.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.adapter.TabFragmentAdapter;
import com.gejiahui.kanzhihu.base.BaseActivity;
import com.gejiahui.kanzhihu.model.UserDetail;
import com.gejiahui.kanzhihu.ui.fragment.DetailsInfoFragment;
import com.gejiahui.kanzhihu.ui.fragment.HomePageFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/22.
 */
public class UserDetailsActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPage)
    ViewPager viewPager;
    @Bind(R.id.exit)
    ImageView exit;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.ans_avatar)
    SimpleDraweeView avatar;
    List<String> tabList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initTab();
        setListener();


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserDetailEvent(UserDetail userInfo) {
        avatar.setImageURI(Uri.parse(userInfo.getAvatar()));
        name.setText(userInfo.getName());

    }

    private void viewInit(){

    }

    private void initTab(){
        tabList.add("个人主页");
        tabList.add("详细信息");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new HomePageFragment());
        fragmentList.add(new DetailsInfoFragment());


        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(),tabList,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void setListener(){
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
