package com.gejiahui.kanzhihu.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.adapter.MenuAdapter;
import com.gejiahui.kanzhihu.base.BaseFragment;
import com.gejiahui.kanzhihu.base.EasyRecyclerViewAdapter;
import com.gejiahui.kanzhihu.model.Constants;
import com.gejiahui.kanzhihu.model.MenuItem;
import com.gejiahui.kanzhihu.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/13.
 */
public class MenuFragment extends BaseFragment {
    @Bind(R.id.menu_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.today_time)
    TextView todayTime;

    private ArrayList<MenuItem> mDatas = new ArrayList<>();
    private MainActivity mMainActivity;
    public MenuFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity)context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        ButterKnife.bind(this,view);
        todayTime.setText(getTodayTime());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
        MenuAdapter adapter = new MenuAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                mMainActivity.replaceFragment(R.id.frame_content,((MenuItem) data).getFragmentInstance());
                mMainActivity.closeDrawer();
                mMainActivity.setToolbarTitle(((MenuItem) data).getTitle());

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initDatas(){
          mDatas.add(new MenuItem("昨日精选", Constants.YESTERDAY_ANSWERS,R.drawable.ic_loyalty_black_24dp));
          mDatas.add(new MenuItem("近日精选", Constants.RECENT_ANSWERS,R.drawable.ic_loyalty_black_24dp));
          mDatas.add(new MenuItem("历史精选", Constants.ARCHIVE_ANSWERS,R.drawable.ic_loyalty_black_24dp));

    }

    private String getTodayTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }
}
