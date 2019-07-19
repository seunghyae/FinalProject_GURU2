package com.example.cho6.finalproject_guru2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VotedBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

public class ResultChoiceAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChoiceBean> mChoiceList;


    public ResultChoiceAdapter(Context context, List<ChoiceBean>choiceList){
        mContext=context;
        mChoiceList=choiceList;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_choice_email, null);

        return view;
    }
}
