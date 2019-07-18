package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.ChoiceAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class VoteResultActivity extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private TextView mTxtTitle, mTxtEx;
    private ListView mLstChoice;
    private ChoiceAdapter mChoiceAdapter;
    private VoteBean mVoteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtEx = findViewById(R.id.txtVoteEx);
        mLstChoice = findViewById(R.id.lstChoice);

        mVoteBean = (VoteBean)getIntent().getSerializableExtra(VoteBean.class.getName());

        List<ChoiceBean> choiceList = mVoteBean.choiceList;
        String userEmail = mFirebaseAuth.getCurrentUser().getEmail();

        mTxtTitle.setText(mVoteBean.voteTitle);
        mTxtEx.setText(mVoteBean.voteSubTitle);
        //최초 데이터 세팅
        if(mVoteBean.votedList != null) {
            for(VotedBean votedBean : mVoteBean.votedList) {
                if( TextUtils.equals(votedBean.userId, userEmail) ) {
                    choiceList = votedBean.choiceList;
                    break;
                }
            }
        }

        mChoiceAdapter = new ChoiceAdapter(this, choiceList, false, false);
        mLstChoice.setAdapter(mChoiceAdapter);

    }
}
