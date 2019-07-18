package com.example.cho6.finalproject_guru2.Acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.adapter.ChoiceAdapter;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

        mTxtTitle.setText(mVoteBean.voteTitle);
        mTxtEx.setText(mVoteBean.voteSubTitle);
        //최초 데이터 세팅
        mChoiceAdapter = new ChoiceAdapter(this, mVoteBean.choiceList , false, false);
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
        String userEmail = mFirebaseAuth.getCurrentUser().getEmail();
        final String uuid = Utils.getUserIdFromUUID(userEmail);
        mFirebaseDB.getReference().child("members").child(uuid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MemberBean memberBean = dataSnapshot.getValue(MemberBean.class);

                if(memberBean.voteList == null) {
                    memberBean.voteList = new ArrayList<>();
                }

                boolean isUpdate = false;
                for(int i=0; i<memberBean.voteList.size(); i++) {
                    VoteBean voteBean = memberBean.voteList.get(i);
                    if( voteBean.voteID == mVoteBean.voteID ) {
                        memberBean.voteList.set(i, mVoteBean); //update
                        isUpdate = true;
                    }
                }

                if(!isUpdate) {
                    memberBean.voteList.add(mVoteBean);
                }

                mFirebaseDB.getReference().child("members").child(uuid).setValue(memberBean);

                Toast.makeText(VoteActivity.this, "투표가 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

}