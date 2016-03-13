package com.gejiahui.kanzhihu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.adapter.AnswersListAdapter;
import com.gejiahui.kanzhihu.base.BaseFragment;
import com.gejiahui.kanzhihu.base.EasyRecyclerViewAdapter;
import com.gejiahui.kanzhihu.model.Answer;
import com.gejiahui.kanzhihu.model.Constants;
import com.gejiahui.kanzhihu.net.Request4Answers;
import com.gejiahui.kanzhihu.net.RequestManager;
import com.gejiahui.kanzhihu.ui.AnswerActivity;
import com.orhanobut.logger.Logger;
import com.victor.loading.rotate.RotateLoading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/11.
 */
public class ContentFragment extends BaseFragment {
    private static String GET_ANSWER_CONTENT_URL = "http://api.kanzhihu.com/getpostanswers/";
    private static String YESTERDAY = "/yesterday";
    private static String RECENT = "/recent";
    private static String ARCHIVE = "/archive";

    private int type = 1;


    @Bind(R.id.swipe_refreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.rotateloading)
    RotateLoading rotateloading;
    public ContentFragment() {

    }

    public static ContentFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    Request4Answers request4Answers;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        ButterKnife.bind(this, view);
        rotateloading.start();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null){
            type = getArguments().getInt("type",0);
        }

        request4Answers = new Request4Answers(getContentURL(type),
                new Response.Listener<ArrayList<Answer>>() {
                    @Override
                    public void onResponse(ArrayList<Answer> response) {
                        AnswersListAdapter adapter = new AnswersListAdapter(response);
                        adapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(View view, int position, Object data) {
                                Intent intent = new Intent(getActivity(),AnswerActivity.class);
                                intent.putExtra("url",((Answer)data).getAnswerUrl());
                                startActivity(intent);
                            }
                        });
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setAdapter(adapter);
                        rotateloading.stop();
                        if(mSwipeRefreshLayout.isRefreshing()){
                            mSwipeRefreshLayout.setRefreshing(false);  //刷新成功，停止刷新动画
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                if(mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);  //刷新失败，停止刷新动画
                }
            }
        });
        RequestManager.addQueue(request4Answers);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RequestManager.addQueue(request4Answers);
            }
        });


    }

    private String getTodayTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    private  String getContentURL(int type){
        switch (type){
            case Constants.YESTERDAY_ANSWERS:
                return GET_ANSWER_CONTENT_URL+getTodayTime()+YESTERDAY;

            case Constants.RECENT_ANSWERS:
                return GET_ANSWER_CONTENT_URL+getTodayTime()+RECENT;

            case Constants.ARCHIVE_ANSWERS:
                return GET_ANSWER_CONTENT_URL+getTodayTime()+ARCHIVE;

            default:
                return GET_ANSWER_CONTENT_URL+getTodayTime()+YESTERDAY;
        }

    }

}
