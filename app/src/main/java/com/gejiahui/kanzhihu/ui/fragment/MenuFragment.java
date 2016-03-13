package com.gejiahui.kanzhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.adapter.MenuAdapter;
import com.gejiahui.kanzhihu.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/13.
 */
public class MenuFragment extends BaseFragment {
    @Bind(R.id.menu_recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<String> mDatas = new ArrayList<>();
    public MenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
        MenuAdapter adapter = new MenuAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initDatas(){
        mDatas.add("昨日精华");
        mDatas.add("近日精华");
        mDatas.add("历史精华");
        mDatas.add("用户精华");
    }
}
