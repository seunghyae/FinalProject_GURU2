package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.R;

public class RealUserMainActivity extends AppCompatActivity {

    TextView mTxtUserVote, mTxtMyInfo, mTxtVoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_user_main);

        mTxtUserVote = findViewById(R.id.txtUserVote);
        mTxtMyInfo = findViewById(R.id.txtMyInfo);
        mTxtVoteList = findViewById(R.id.txtVoteList);

        mTxtUserVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealUserMainActivity.this, MyVoteListActivity.class);
                startActivity(i);
            }
        });

        mTxtMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealUserMainActivity.this, MyInfoActivity.class);
                startActivity(i);
            }
        });

        mTxtVoteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealUserMainActivity.this, UserMainActivity.class);
                startActivity(i);
            }
        });
    }
}
