package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.ResultChoiceAdapter;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ResultVoteDetailActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private ListView mLstChoiceResult;
    private ResultChoiceAdapter mResultChoiceAdapter;
    private VoteBean mVoteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_vote_detail);

        mLstChoiceResult=findViewById(R.id.lstChoiceResult);

        mVoteBean = (VoteBean)getIntent().getSerializableExtra(VoteBean.class.getName());
        List<ChoiceBean> choiceList = mVoteBean.choiceList;

        String userEmail = mFirebaseAuth.getCurrentUser().getEmail();
        String uuid = Utils.getUserIdFromUUID(userEmail);

        //최초 데이터 세팅
        if(mVoteBean.votedList != null) {
            for(VotedBean votedBean : mVoteBean.votedList) {
                for(ChoiceBean choiceBean:votedBean.choiceList){
                    if(choiceBean.isSelect==true){
                        choiceList=votedBean.choiceList;
                    }
                }
            }
        }

        mResultChoiceAdapter=new ResultChoiceAdapter(this,choiceList);

    }
}