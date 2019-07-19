package com.example.cho6.finalproject_guru2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

//UserMain에서 투표현황 클릭했을 때 나오는 listview에 뿌려주는 adapter
public class UserVoteResultAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private VoteBean mVoteBean;
    private List<ChoiceBean> mChoiceList;

    public UserVoteResultAdapter(Context context, VoteBean voteBean) {
        mContext = context;
        mVoteBean = voteBean;
        mChoiceList = voteBean.choiceList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mChoiceList.size();
    }

    @Override
    public Object getItem(int i) {
        return mChoiceList.get(i);
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
        TextView txtChoiceTitle = view.findViewById(R.id.txtChoiceTitle);
        TextView txtVoteCount = view.findViewById(R.id.txtVoteCount);

        ChoiceBean choiceBean = mChoiceList.get(position);

        txtNum.setText( String.valueOf(position+1) );
        txtChoiceTitle.setText( choiceBean.itemTitle );
        txtVoteCount.setText( String.valueOf(choiceBean.totalSelCount) + " 표" );

        return view;
    }

}
