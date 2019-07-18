package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.cho6.finalproject_guru2.Fragment.AdminVoteFragment;
import com.example.cho6.finalproject_guru2.Fragment.MyInfoFragment;
import com.example.cho6.finalproject_guru2.Fragment.VoteListFragment;
import com.example.cho6.finalproject_guru2.R;
import com.google.android.material.tabs.TabLayout;

public class SettingActivity extends AppCompatActivity {
    private MyPagerAdapter mMyPagerAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //Tab 생성
        tabLayout.addTab(tabLayout.newTab().setText("투표한 목록"));
        tabLayout.addTab(tabLayout.newTab().setText("내 정보"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
    //텝 이동

    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabSize;//텝수

        public MyPagerAdapter(FragmentManager fm, int count){
            super(fm);
            this.tabSize=count;//텝의 수
        }
        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new VoteListFragment();
                case 1:
                    return new MyInfoFragment();

            }
            return null;
        }
        @Override
        public int getCount(){return this.tabSize;}
    }
}
