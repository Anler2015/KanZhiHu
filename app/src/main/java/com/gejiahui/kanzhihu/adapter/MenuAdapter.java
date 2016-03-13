package com.gejiahui.kanzhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.EasyRecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/13.
 */
public class MenuAdapter extends EasyRecyclerViewAdapter<String> {

    public MenuAdapter(List<String> mDatas) {
        super(mDatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        ((MenuViewHolder)viewHolder).title.setText(data);

    }

    class MenuViewHolder extends EasyViewHolder{
        @Bind(R.id.menu_item_title)
        TextView title;
        @Bind(R.id.menu_item_img)
        ImageView img;
        public MenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
