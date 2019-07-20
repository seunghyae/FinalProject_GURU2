package com.example.cho6.finalproject_guru2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.EmailBean;
import com.example.cho6.finalproject_guru2.R;

import java.util.List;

public class ShowPeopleAdapter extends BaseAdapter {

    private Context mContext;
    public LayoutInflater inflater;
    public EmailBean mEmailBean;
    public List<EmailBean> mEmailList;
    public TextView mTxtEmail;

    public ShowPeopleAdapter(Context context, EmailBean emailBean) {
        mContext = context;
        mEmailBean = emailBean;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mEmailList.size();
    }

    @Override
    public Object getItem(int i) {
        return mEmailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_choice_email, null);

        //final EmailBean emailBean = mEmailList.get(i);

        mTxtEmail = view.findViewById(R.id.txtEmail);
        mTxtEmail.setText(mEmailBean.email);

        return view;
    }
}
