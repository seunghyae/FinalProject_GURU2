package com.example.cho6.finalproject_guru2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

//UserMain에서 투표현황 클릭했을 때 나오는 listview에 뿌려주는 adapter
public class UserVoteResultAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private VoteBean mVoteBean;
    private List<VotedBean> mVotedList;

    public UserVoteResultAdapter(Context context, VoteBean voteBean) {
        mContext = context;
        mVoteBean = voteBean;
        mVotedList = voteBean.votedList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mVotedList.size();
    }

    @Override
    public Object getItem(int i) {
        return mVotedList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_result_choice, null);

        TextView txtNum = view.findViewById(R.id.txtNum);
        RadioButton rdoChoice = view.findViewById(R.id.rdoChoice);
        CheckBox chkChoice = view.findViewById(R.id.chkChoice);
        final EditText edtTile = view.findViewById(R.id.edtTitle);


        return view;
    }

}
