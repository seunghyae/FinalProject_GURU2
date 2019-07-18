package com.example.cho6.finalproject_guru2.Acitivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.ChoiceAdapter;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private TextView mTxtTitle, mTxtEx;
    private Button mBtnVoteSubmit;
    private ListView mLstChoice;
    private ChoiceAdapter mChoiceAdapter;
    private VoteBean mVoteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtEx = findViewById(R.id.txtVoteEx);
        mBtnVoteSubmit = findViewById(R.id.btnVoteSubmit);
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

        mBtnVoteSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteSubmit();
            }
        });
    }

    //투표하기 제출
    private void voteSubmit() {

        //회원정보를 가져온다.
        final String userEmail = mFirebaseAuth.getCurrentUser().getEmail();
        final String uuid = Utils.getUserIdFromUUID(userEmail);

        mFirebaseDB.getReference().child("votes").child(mVoteBean.voteID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VoteBean voteBean = dataSnapshot.getValue(VoteBean.class);

                if(voteBean.votedList == null) {
                    voteBean.votedList = new ArrayList<>();
                }

                boolean isAlreadyVote = false;
                for(int i=0; i<voteBean.votedList.size(); i++) {
                    VotedBean votedBean = voteBean.votedList.get(i);
                    if( TextUtils.equals(votedBean.userId, userEmail) ) {
                        isAlreadyVote = true;
                        break;
                    }
                }

                if(isAlreadyVote) {
                    Toast.makeText(VoteActivity.this, "이미 투표 하셨습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                VotedBean votedBean = new VotedBean();
                voteBean.voteID = voteBean.voteID;
                votedBean.userId = userEmail;
                votedBean.uuid = uuid;
                votedBean.choiceList = mChoiceAdapter.getChoiceList();
                //신규추가
                voteBean.votedList.add(votedBean);

                mFirebaseDB.getReference().child("votes").child(mVoteBean.voteID).setValue(voteBean);

                Toast.makeText(VoteActivity.this, "투표가 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

}