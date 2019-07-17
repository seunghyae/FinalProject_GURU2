package com.example.cho6.finalproject_guru2.Firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ChoiceAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChoiceBean> mChoiceList;

    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    public ChoiceAdapter(Context context, List <ChoiceBean> choiceList){
        mContext = context;
        mChoiceList = choiceList;
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
        view = inflater.inflate(R.layout.view_choice, null);

        RadioButton radioBtn = view.findViewById(R.id.radioBtn);

        final ChoiceBean choiceBean = mChoiceList.get(i);

        radioBtn.setText(choiceBean.item1);


        return view;
    }
}
