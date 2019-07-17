package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.example.cho6.finalproject_guru2.Fragment.AdminVoteFragment;
import com.example.cho6.finalproject_guru2.Fragment.MyInfoFragment;
import com.example.cho6.finalproject_guru2.Fragment.VoteListFragment;
import com.example.cho6.finalproject_guru2.R;
import com.google.android.material.tabs.TabLayout;

public class AdminSettingActivity extends AppCompatActivity {
    private MyPagerAdapter mMyPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);
    }
    //텝 이동

    class MyPagerAdapter extends FragmentPagerAdapter{
        int tabSize;//텝수

        public MyPagerAdapter(FragmentManager fm,int count){
            super(fm);
            this.tabSize=count;//텝의 수
          }

          @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new AdminVoteFragment();
                case 1:
                    return new MyInfoFragment();
            }
            return null;
          }
          @Override
        public int getCount(){return this.tabSize;}
    }
}
