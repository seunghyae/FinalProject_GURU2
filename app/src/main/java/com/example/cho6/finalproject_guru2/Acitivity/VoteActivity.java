package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Firebase.ChoiceAdapter;
import com.example.cho6.finalproject_guru2.Firebase.VoteAdapter;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    TextView mTxtTitle, mTxtEx;
    Button mBtnVoteSubmit;
    ListView mLstChoice;

    private List<ChoiceBean> mChoiceList = new ArrayList<>();
    private ChoiceAdapter mChoiceAdapter;

    VoteBean voteBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtEx = findViewById(R.id.txtVoteEx);

        mBtnVoteSubmit = findViewById(R.id.btnVoteSubmit);
        mLstChoice = findViewById(R.id.lstChoice);

        voteBean = (VoteBean)getIntent().getSerializableExtra("voteBean");

        mTxtTitle.setText(voteBean.voteTitle);
        mTxtEx.setText(voteBean.voteSubTitle);

        //최초 데이터 세팅
        mChoiceAdapter = new ChoiceAdapter(this, mChoiceList);
        mLstChoice.setAdapter(mChoiceAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        mFirebaseDB.getReference().child("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터를 받아와서 List에 저장
                mChoiceList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChoiceBean bean = snapshot.getValue(ChoiceBean.class);
                    mChoiceList.add(0, bean);
                }
                //바뀐 데이터로 refresh 한다
                if(mChoiceAdapter != null){
                    mChoiceAdapter.notifyDataSetChanged();;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });

    }


}
