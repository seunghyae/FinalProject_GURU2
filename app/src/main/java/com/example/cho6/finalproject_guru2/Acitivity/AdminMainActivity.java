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
import com.example.cho6.finalproject_guru2.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminMainActivity extends AppCompatActivity {

    public static ListView mLstVote;
    public static final int SAVE=1001;
    //원본 데이터
    List<VoteBean> voteList=new ArrayList<>();
    //어뎁터 생성및 적용
    ListAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();

        MemberBean memberBean = FileDB.getLoginMember(this);
        voteList = FileDB.getVoteList(this, memberBean.memId);
        adapter = new ListAdapter(voteList, this);
        mLstVote.setAdapter(adapter);
    }
    class ListAdapter extends BaseAdapter {
        List<VoteBean> items;  //원본 데이터
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<VoteBean> items, Context context) {
            this.items = items;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        public void setItems(List<VoteBean> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            // view_item.xml 획득
            view = inflater.inflate(R.layout.view_vote_admin, null);

            // 객체 획득

            final TextView txtVote = view.findViewById(R.id.txtVoteName);
            TextView txtVoteEx = view.findViewById(R.id.txtVoteEx);
            TextView txtDate = view.findViewById(R.id.txtDate);
            Button btnStartVote = view.findViewById(R.id.btnStartVote);
            Button btnFinishVote = view.findViewById(R.id.btnFinishVote);
            Button btnShowVote = view.findViewById(R.id.btnShowVote);

            // 원본에서 i번째 Item 획득
            final VoteBean item = items.get(i);

            // 원본 데이터를 UI에 적용

            txtVote.setText(item.voteTitle);
            txtDate.setText(item.voteDate);
            txtVoteEx.setText(item.voteSubTitle);




            return view;  // 완성된 UI 리턴
        }
    }


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
