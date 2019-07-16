package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cho6.finalproject_guru2.R;

public class AdminMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);


        Button mbtnNewVote=findViewById(R.id.btnNewVote);
        ImageButton mbtnSetting=findViewById(R.id.btnSetting);

        //투표 만들기 버튼 클릭 이벤트 실행코드
        mbtnNewVote.setOnClickListener(mbtnNewVoteClick);
        mbtnSetting.setOnClickListener(mbtnSettingClick);
    }

    //투표 만들기 버튼 클릭 이벤트
  private View.OnClickListener mbtnNewVoteClick=new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent i=new Intent(AdminMainActivity.this,RegVoteActivity.class);
          startActivity(i);
      }
  };
    //설정 버튼 클릭 이벤트
    private View.OnClickListener mbtnSettingClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ii=new Intent(AdminMainActivity.this,AdminSettingActivity.class);
            startActivity(ii);

        }
    };
}
