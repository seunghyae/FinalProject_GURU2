package com.example.cho6.finalproject_guru2.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Firebase.UserAdapter;
import com.example.cho6.finalproject_guru2.Firebase.VoteAdapter;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminVoteFragment extends Fragment {
    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    private List<VoteBean> mEndVoteList = new ArrayList<>();
    private VoteAdapter mVoteAdapter;

    public AdminVoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mFirebaseDB.getReference().child("votes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    VoteBean bean = snapshot.getValue(VoteBean.class);
                    if(bean.endVote == true) {
                        mEndVoteList.add(0, bean);
                    }
                    //바뀐 데이터로 refresh 한다
                    if (mVoteAdapter != null) {
                        mVoteAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.fragment_admin_vote, container, false);
    }
}


