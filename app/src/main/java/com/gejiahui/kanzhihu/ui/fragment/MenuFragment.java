package com.gejiahui.kanzhihu.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.adapter.MenuAdapter;
import com.gejiahui.kanzhihu.adapter.ThemeColorAdapter;
import com.gejiahui.kanzhihu.base.BaseFragment;
import com.gejiahui.kanzhihu.base.EasyRecyclerViewAdapter;
import com.gejiahui.kanzhihu.model.Constants;
import com.gejiahui.kanzhihu.model.MenuItem;
import com.gejiahui.kanzhihu.model.ThemeColor;
import com.gejiahui.kanzhihu.ui.MainActivity;
import com.gejiahui.kanzhihu.utils.ThemeUtils;
import com.stylingandroid.prism.Prism;

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
    @Bind(R.id.theme)
    LinearLayout theme;
    @Bind(R.id.setting)
    LinearLayout setting;
    @Bind(R.id.header)
    RelativeLayout header;

    private ArrayList<MenuItem> mDatas = new ArrayList<>();
    private ArrayList<ThemeColor> themeColorList = new ArrayList<>();
    private MainActivity mMainActivity;
    private int selectedItem = 0;
    private MenuAdapter adapter;
    Prism prism;
    private ThemeColorAdapter themeColorAdapter = new ThemeColorAdapter();

    public MenuFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        header.setBackgroundColor(ThemeUtils.getThemeColor());
        todayTime.setText(getTodayTime());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
        initDatas();
        adapter = new MenuAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                mMainActivity.replaceFragment(R.id.frame_content, ((MenuItem) data).getFragmentInstance());
                mMainActivity.closeDrawer();
                mMainActivity.setToolbarTitle(((MenuItem) data).getTitle());
                selectedItem = position;
                cleanDatasSelected();
                ((MenuItem) data).setSelected(true);
                adapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void initDatas() {
        mDatas.add(new MenuItem("昨日精选", Constants.YESTERDAY_ANSWERS, R.drawable.ic_loyalty_black_24dp));
        mDatas.add(new MenuItem("近日精选", Constants.RECENT_ANSWERS, R.drawable.ic_loyalty_black_24dp));
        mDatas.add(new MenuItem("历史精选", Constants.ARCHIVE_ANSWERS, R.drawable.ic_loyalty_black_24dp));
        mDatas.get(0).setSelected(true);
        //
        themeColorAdapter = new ThemeColorAdapter();
        themeColorList.add(new ThemeColor(R.color.theme_blue));
        themeColorList.add(new ThemeColor(R.color.theme_blue_light));
        themeColorList.add(new ThemeColor(R.color.theme_lime));
        themeColorList.add(new ThemeColor(R.color.theme_teal));
        themeColorList.add(new ThemeColor(R.color.theme_green));
        themeColorList.add(new ThemeColor(R.color.theme_green_light));
        themeColorList.add(new ThemeColor(R.color.theme_brown));
        themeColorList.add(new ThemeColor(R.color.theme_red));
        themeColorAdapter.setDatas(themeColorList);
        themeColorAdapter.setOnItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                for (ThemeColor themeColor : themeColorList) {
                    themeColor.setChosen(false);
                }
                themeColorList.get(position).setChosen(true);
                themeColorAdapter.notifyDataSetChanged();
                ThemeUtils.setThemeColor(prism, getResources().getColor(((ThemeColor) data).getColor()));
            }
        });
    }

    private void setListener() {
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prism = Prism.Builder.newInstance()
                        .background(mMainActivity.toolbar)
                        .background(mMainActivity.getWindow())
                        .background(header)
                        .build();
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_theme_color, null, false);
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.theme_recycler_view);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                recyclerView.setAdapter(themeColorAdapter);
                android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("主题选择")
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void cleanDatasSelected() {
        for (MenuItem item : mDatas) {
            item.setSelected(false);
        }
    }


    private String getTodayTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }


    public int getSelectedItem() {
        return selectedItem;
    }
}
