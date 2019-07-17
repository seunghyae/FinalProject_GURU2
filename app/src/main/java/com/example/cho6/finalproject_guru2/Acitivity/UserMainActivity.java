package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Firebase.UserAdapter;
import com.example.cho6.finalproject_guru2.Firebase.VoteAdapter;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private ListView mListView;

    //원본 데이터
    private List<VoteBean> mVoteList = new ArrayList<>();
    //어뎁터 생성및 적용
    private UserAdapter mUserAdapter;

    @Override
    public void onResume() {
        super.onResume();

        VoteBean voteBean = new VoteBean();
        mFirebaseDB.getReference().child("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //데이터를 받아와서 List에 저장
                mVoteList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    VoteBean bean = snapshot.getValue(VoteBean.class);
                    mVoteList.add(0, bean);
                }
                //바뀐 데이터로 refresh 한다
                if(mUserAdapter != null){
                    mUserAdapter.notifyDataSetChanged();;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView=findViewById(R.id.LstUser);

        ImageButton mbtnSetting=findViewById(R.id.btnSetting);
        mbtnSetting.setOnClickListener(mbtnSettingClick);

        //최초 데이터 세팅
        mUserAdapter = new UserAdapter(this, mVoteList);
        mListView.setAdapter(mUserAdapter);
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
