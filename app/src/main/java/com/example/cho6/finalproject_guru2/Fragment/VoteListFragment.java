package com.example.cho6.finalproject_guru2.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.adapter.UserVoteAdapter;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoteListFragment extends Fragment {

    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private List<VoteBean> mUserVoteList = new ArrayList<>();
    public UserVoteAdapter mUserVoteAdapter;
    public ListView mListView;

    public VoteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote_list, container, false);

        mListView = view.findViewById(R.id.lstChoice);

        //최초 데이터 세팅
        mUserVoteAdapter = new UserVoteAdapter(getActivity(), mUserVoteList);
        mListView.setAdapter(mUserVoteAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        mFirebaseDB.getReference().child("votes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String memId = Utils.getUserIdFromUUID(mFirebaseAuth.getCurrentUser().getEmail());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //VotedBean votedBean = snapshot.getValue(VotedBean.class);
                    VoteBean bean = snapshot.getValue(VoteBean.class);

                    try{
                        if(bean.votedList.size()>0) {
                            //자신이 투표한 리스트만 보이게 함
                            for(int i=0; i<bean.votedList.size();i++) {
                                if (TextUtils.equals(memId, bean.votedList.get(i).uuid))
                                    mUserVoteList.add(0, bean);
                            }
                        }
                        throw new Exception(); //강제 에러 출력
                    }catch (Exception e){
                        //에러시 수행
                        e.printStackTrace();
                    }



                    //바뀐 데이터로 refresh 한다
                    if (mUserVoteAdapter != null) {
                        mUserVoteAdapter.notifyDataSetChanged();
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
