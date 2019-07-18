package com.example.cho6.finalproject_guru2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Database.FileDB;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AdminVoteAdapter extends BaseAdapter {

    private Context mContext;
    private List<VoteBean> mVoteList;
    LayoutInflater inflater;
    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();


    public AdminVoteAdapter(Context context, List <VoteBean> voteList){
        mContext = context;
        mVoteList = voteList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mVoteList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVoteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_vote_admin, null);

        TextView txtVote = view.findViewById(R.id.txtVoteName);
        TextView txtVoteEx = view.findViewById(R.id.txtVoteEx);
        TextView txtStartDate = view.findViewById(R.id.txtDate);
        final Button btnStartVote = view.findViewById(R.id.btnStartVote);
        Button btnFinishVote = view.findViewById(R.id.btnFinishVote);
        Button btnShowVote = view.findViewById(R.id.btnShowVote);
        ImageView imgLock = view.findViewById(R.id.imgLock);

        final VoteBean voteBean = mVoteList.get(i);

        txtVote.setText(voteBean.voteTitle);
        txtStartDate.setText(voteBean.voteStartDate);
        txtVoteEx.setText(voteBean.voteSubTitle);

        final VoteBean item = mVoteList.get(i);

        if(voteBean.Lock == true){
            imgLock.setVisibility(View.VISIBLE);
        }


        // 투표 시작 버튼이 눌리면 버튼 텍스트 변경
        if (voteBean.startVote == true) {
            btnStartVote.setText("투표중");
        } else
            btnStartVote.setText("투표시작");

        //투표 종료 버튼이 눌리면 버튼 텍스트 변경
        if (voteBean.endVote == true && voteBean.startVote == true) {
            btnFinishVote.setVisibility(view.GONE);


        } else
            btnFinishVote.setText("투표종료");

        //투표 시작 버튼 클릭 시 수행 될 작업
        btnStartVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteBean.startVote = true;
                DatabaseReference dbRef = mFirebaseDatabase.getReference();
                dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);
            }
        });

        //투표 종료 버튼 클릭 시 수행 될 작업
        btnFinishVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("투표종료");
                alert.setMessage("정말로 종료 하시겠습니까?");

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 투표 시작 전에 종료를 누른 경우
                        if (voteBean.startVote == false) {
                            Toast.makeText(mContext, "먼저 투표가 진행되어야 합니다.", Toast.LENGTH_LONG).show();
                            voteBean.endVote = false;
                            DatabaseReference dbRef = mFirebaseDatabase.getReference();
                            dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);
                        } else if (voteBean.startVote) {
                            voteBean.endVote = true;
                            DatabaseReference dbRef = mFirebaseDatabase.getReference();
                            dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);

                   /* FileDB.delMemo(getActivity(), memberBean.memId, item.memoID);

                    mVoteList = FileDB.getMemoList(getActivity(), memberBean.memId);
                    notifyDataSetChanged(); //갱신해라*/


                        }
                    }
                });

                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                alert.show();


                /* for(int i=0; i<mVoteList.size(); i++) {
                    VoteBean voteBean = mVoteList.get(i);
                    if(memberBean.isAdmin) {
                        mVoteList.remove(i);
                        break;
                    }
                }
                */

            }
        });


        return view;
    }

}
