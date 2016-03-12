package com.gejiahui.kanzhihu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gejiahui.kanzhihu.R;
import com.gejiahui.kanzhihu.base.EasyRecyclerViewAdapter;
import com.gejiahui.kanzhihu.model.Answer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/3/12.
 */
public class AnswersListAdapter extends EasyRecyclerViewAdapter<Answer> {
    public AnswersListAdapter(List<Answer> mDatas) {
        super(mDatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);

        return new AnswerViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Answer data) {
        ((AnswerViewHolder)viewHolder).title.setText(data.getTitle());
        ((AnswerViewHolder)viewHolder).body.setText(data.getSummary());
        ((AnswerViewHolder)viewHolder).vote.setText(data.getVote());

    }

    class AnswerViewHolder extends EasyRecyclerViewAdapter.EasyViewHolder{
        @Bind(R.id.title)
        TextView  title;
        @Bind(R.id.body)
        TextView  body;
        @Bind(R.id.vote)
        TextView  vote;
        @Bind(R.id.avatar)
        ImageView avatar;
        public AnswerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
