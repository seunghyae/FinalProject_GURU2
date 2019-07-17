package com.example.cho6.finalproject_guru2.Firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Acitivity.VoteActivity;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private List<VoteBean> mVoteList;

    public UserAdapter(Context context, List <VoteBean> voteList){
        mContext = context;
        mVoteList = voteList;
    }

    @Override
    public int getCount() {
        return mVoteList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVoteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_vote_user, null);

        TextView txtVote = view.findViewById(R.id.txtVoteName);
        TextView txtVoteEx = view.findViewById(R.id.txtEx);
        TextView txtDate = view.findViewById(R.id.txtDate);
        Button btnVote = view.findViewById(R.id.btnVote);
        Button btnShowResult = view.findViewById(R.id.btnShowResult);

        final VoteBean voteBean = mVoteList.get(i);

        txtVote.setText(voteBean.voteTitle);
        txtDate.setText(voteBean.voteDate);
        txtVoteEx.setText(voteBean.voteSubTitle);

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, VoteActivity.class);
                i.putExtra("voteTitle", voteBean.voteTitle);
                i.putExtra("voteEx", voteBean.voteSubTitle);
                mContext.startActivity(i);
            }

        });

        return view;
    }
}
