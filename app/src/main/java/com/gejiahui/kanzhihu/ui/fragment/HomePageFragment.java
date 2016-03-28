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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/24.
 */
public class HomePageFragment extends BaseFragment {
    @Bind(R.id.signature)
    TextView signature;
    @Bind(R.id.followee)
    TextView followee;
    @Bind(R.id.follower)
    TextView follower;
    @Bind(R.id.post)
    TextView post;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.thumb)
    TextView thumb;
    @Bind(R.id.thanks)
    TextView thanks;

    public HomePageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_homepage, container, false);
        ButterKnife.bind(this, view);
        return view;
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserDetailEvent(UserDetail userInfo) {
        signature.setText(userInfo.getSignature());
        description.setText(userInfo.getDescription());
        followee.setText(userInfo.getDetail().getString("followee"));
        follower.setText(userInfo.getDetail().getString("follower"));
        post.setText(userInfo.getDetail().getString("post"));
        thumb.setText(userInfo.getDetail().getString("agree"));
        thanks.setText(userInfo.getDetail().getString("thanks"));
    }

}
