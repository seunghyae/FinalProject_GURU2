package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.R;

public class RealAdminMainActivity extends AppCompatActivity {

    TextView mTxtMakeVote, mTxtMyVote, mTxtEndVote, mTxtMyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_admin_main);

        mTxtMakeVote = findViewById(R.id.txtMakeVote);
        mTxtMyVote = findViewById(R.id.txtMyVote);
        mTxtEndVote = findViewById(R.id.txtEndVote);
        mTxtMyInfo = findViewById(R.id.txtMyInfo);

        mTxtMakeVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RealAdminMainActivity.this,RegVoteActivity.class);
                startActivity(i);
            }
        });

        mTxtMyVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealAdminMainActivity.this, AdminMainActivity.class);
                startActivity(i);
            }
        });

        mTxtEndVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealAdminMainActivity.this, AdminVoteActivity.class);
                startActivity(i);
            }
        });

        mTxtMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RealAdminMainActivity.this, MyInfoActivity.class);
                startActivity(i);
            }
        });


    }
}
