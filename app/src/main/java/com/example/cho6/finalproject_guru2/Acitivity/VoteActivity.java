package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.R;

public class VoteActivity extends AppCompatActivity {

    TextView mTxtTitle, mTxtEx;
    Button mBtnVoteSubmit;
    ListView mLstChoice;

    String voteTitle, voteEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtEx = findViewById(R.id.txtVoteEx);

        mBtnVoteSubmit = findViewById(R.id.btnVoteSubmit);
        mLstChoice = findViewById(R.id.lstChoice);

        voteTitle = getIntent().getStringExtra("voteTitle");
        voteEx = getIntent().getStringExtra("voteEx");

        mTxtTitle.setText(voteTitle);
        mTxtEx.setText(voteEx);

    }


}
