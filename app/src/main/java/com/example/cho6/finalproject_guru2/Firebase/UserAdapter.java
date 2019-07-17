package com.example.cho6.finalproject_guru2.Firebase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cho6.finalproject_guru2.Acitivity.VoteActivity;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private List<VoteBean> mVoteList;

    public UserAdapter(Context context, List <VoteBean> voteList){
        mContext = context;
        mVoteList = voteList;
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

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_vote_user, null);

        TextView txtVote = view.findViewById(R.id.txtVoteName);
        TextView txtVoteEx = view.findViewById(R.id.txtVoteEx);
        TextView txtDate = view.findViewById(R.id.txtDate);
        Button btnVote = view.findViewById(R.id.btnVote);
        Button btnShowResult = view.findViewById(R.id.btnShowResult);


        final VoteBean voteBean = mVoteList.get(i);

        txtVote.setText(voteBean.voteTitle);
        txtDate.setText(voteBean.voteDate);
        txtVoteEx.setText(voteBean.voteSubTitle);

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, VoteActivity.class);
                i.putExtra("voteTitle", voteBean.voteTitle);
                i.putExtra("voteEx", voteBean.voteSubTitle);
                mContext.startActivity(i);
            }

        });

        btnShowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //투표하기
        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dlg = new Dialog(mContext);
                dlg.setContentView(R.layout.view_custom_dialog);
                dlg.show();
                final EditText edtCode = dlg.findViewById(R.id.edtCode);
                Button btnOk = dlg.findViewById(R.id.btnOK);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String code = edtCode.getText().toString();
                        if(TextUtils.equals(voteBean.voteDate, code) ) {
                            Intent i =new Intent(mContext, VoteActivity.class);
                            mContext.startActivity(i);
                        }
                        else {
                            Toast.makeText(mContext, "코드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}
