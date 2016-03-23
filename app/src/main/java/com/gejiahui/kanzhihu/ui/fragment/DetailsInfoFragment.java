package com.gejiahui.kanzhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.BaseFragment;
import com.gejiahui.kanzhihu.model.UserDetail;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/23.
 */
public class DetailsInfoFragment extends BaseFragment {
    @Bind(R.id.thumb)
    TextView thumb;
    @Bind(R.id.thanks)
    TextView thanks;
    @Bind(R.id.fav)
    TextView fav;
    @Bind(R.id.share)
    TextView share;

    public DetailsInfoFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_info,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserDetailEvent(UserDetail userInfo) {
        Logger.d("yrr  :"+userInfo);
        Logger.d("yrr  :"+userInfo.getDetail());
//        thumb.setText(userInfo.getDetail().getPost());
//        thanks.setText(userInfo.getDetail().getThanks());
//        fav.setText(userInfo.getDetail().getFav());
    }



}
