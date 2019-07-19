package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.UserVoteResultAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultVoteActivity extends AppCompatActivity {

    public Context mContext;
    public ListView mListView;
    private TextView mTxtTitle, mTxtEx;
    public UserVoteResultAdapter mUserVoteResultAdapter;
    private VoteBean mVoteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_vote);

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtEx = findViewById(R.id.txtVoteEx);
        mListView = findViewById(R.id.lstResultChoice);

        mVoteBean = (VoteBean) getIntent().getSerializableExtra(VoteBean.class.getName());

        mTxtTitle.setText(mVoteBean.voteTitle);
        mTxtEx.setText(mVoteBean.voteSubTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseDatabase.getInstance().getReference().child("votes").child(mVoteBean.voteID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                VoteBean voteBean = dataSnapshot.getValue(VoteBean.class);
                //실시간 데이터 세팅
                mUserVoteResultAdapter = new UserVoteResultAdapter(mContext, voteBean);
                mListView.setAdapter(mUserVoteResultAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }

}
