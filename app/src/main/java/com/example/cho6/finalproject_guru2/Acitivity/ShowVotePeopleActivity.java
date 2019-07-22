package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.EmailBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.ShowPeopleAdapter;
import com.example.cho6.finalproject_guru2.adapter.UserVoteResultAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowVotePeopleActivity extends AppCompatActivity {

    private TextView mTxtTitle;
    public ListView mListView;
    public ShowPeopleAdapter mShowPeopleAdapter;
    public VoteBean mVoteBean;
    public List<String> mEmailList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vote_people);

        mTxtTitle = findViewById(R.id.txtTitle);
        mListView = findViewById(R.id.lstPeople);

        mVoteBean = (VoteBean) getIntent().getSerializableExtra(VoteBean.class.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseDatabase.getInstance().getReference().child("votes").child(mVoteBean.voteID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                VoteBean voteBean = dataSnapshot.getValue(VoteBean.class);

                try{
                    if(voteBean.choiceList != null && voteBean.choiceList.size()>0) {
                        for(int i=0; i<voteBean.choiceList.size(); i++) {
                            ChoiceBean choiceBean = voteBean.choiceList.get(i);
                            if(choiceBean.selectUserIdList != null) {
                                mEmailList.clear();
                                for (int k = 0; k < choiceBean.selectUserIdList.size(); k++) {
                                    String email = choiceBean.selectUserIdList.get(k);
                                    mEmailList.add(email);
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    //에러시 수행
                    e.printStackTrace();
                }

                //실시간 데이터 세팅
                mTxtTitle.setText("투표한 사람 총: " + mEmailList.size() + "명");
                mShowPeopleAdapter = new ShowPeopleAdapter(ShowVotePeopleActivity.this, mEmailList);
                mListView.setAdapter(mShowPeopleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}