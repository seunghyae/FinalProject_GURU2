package com.example.cho6.finalproject_guru2.Firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cho6.finalproject_guru2.Acitivity.VoteActivity;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class VoteAdapter extends BaseAdapter {

    private Context mContext;
    private List<VoteBean> mVoteList;

    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    public VoteAdapter(Context context, List <VoteBean> voteList){
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
        view = inflater.inflate(R.layout.view_vote_admin, null);

        TextView txtVote = view.findViewById(R.id.txtVoteName);
        TextView txtVoteEx = view.findViewById(R.id.txtEx);
        TextView txtDate = view.findViewById(R.id.txtDate);
        Button btnStartVote = view.findViewById(R.id.btnStartVote);
        Button btnFinishVote = view.findViewById(R.id.btnFinishVote);
        Button btnShowVote = view.findViewById(R.id.btnShowVote);

        final VoteBean voteBean = mVoteList.get(i);

        txtVote.setText(voteBean.voteTitle);
        txtDate.setText(voteBean.voteDate);
        txtVoteEx.setText(voteBean.voteSubTitle);

        btnStartVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteBean.startVote = true;

                DatabaseReference dbRef = mFirebaseDatabase.getReference();
                dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);

            }
        });
        return view;
    }
}
