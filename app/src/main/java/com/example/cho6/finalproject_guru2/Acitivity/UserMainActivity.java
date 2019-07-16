package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cho6.finalproject_guru2.R;

public class UserMainActivity extends AppCompatActivity {
    private ViewPager viewPager;//표시할 영역

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mbtnSetting=findViewById(R.id.btnSetting);
        mbtnSetting.setOnClickListener(mbtnSettingClick);

    }
    //설정 버튼 클릭 이벤트
    private View.OnClickListener mbtnSettingClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ii=new Intent(UserMainActivity.this,SettingActivity.class);
            startActivity(ii);

        }
    };
}
