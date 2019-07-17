package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Database.FileDB;
import com.example.cho6.finalproject_guru2.Firebase.VoteAdapter;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminMainActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();

    private ListView mListView;
    //원본 데이터
    private List<VoteBean> mVoteList = new ArrayList<>();
    //어뎁터 생성및 적용
    private VoteAdapter mVoteAdapter;

    @Override
    public void onResume() {
        super.onResume();

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
                if(mVoteAdapter != null){
                    mVoteAdapter.notifyDataSetChanged();;
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
        setContentView(R.layout.activity_admin_main);

        mListView = findViewById(R.id.LstAdmin);

        Button mbtnNewVote=findViewById(R.id.btnNewVote);
        ImageButton mbtnSetting=findViewById(R.id.btnSetting);

        //투표 만들기 버튼 클릭 이벤트 실행코드
        mbtnNewVote.setOnClickListener(mbtnNewVoteClick);
        mbtnSetting.setOnClickListener(mbtnSettingClick);

        //최초 데이터 세팅
        mVoteAdapter = new VoteAdapter(this, mVoteList);
        mListView.setAdapter(mVoteAdapter);
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
