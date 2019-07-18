package com.example.cho6.finalproject_guru2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cho6.finalproject_guru2.Acitivity.LoginActivity;
import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class MyInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_info, container, false);

        final TextView mTxtMemId=view.findViewById(R.id.txtMemID);
        final TextView mTxtMemName=view.findViewById(R.id.txtMemName);
        final TextView mTxtMemMajor=view.findViewById(R.id.txtMemMajor);
        final TextView mTxtMemNo=view.findViewById(R.id.txtMemNo);

        Button btnLogout=view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        //FireBase 인증객체
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
        final FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();

        String email;
        email = mFirebaseUser.getEmail();
        mTxtMemId.setText("구글ID: "+email);

        mFirebaseDB.getReference().child("member").child(Utils.getUserIdFromUUID(email)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MemberBean bean = snapshot.getValue(MemberBean.class);
                    String UUIDEmail = Utils.getUserIdFromUUID(mFirebaseUser.getEmail());
                    if(TextUtils.equals(bean.memId, UUIDEmail)) {
                        mTxtMemName.setText("이름: "+bean.memName);
                        mTxtMemMajor.setText("학과: "+bean.memMajor);
                        mTxtMemNo.setText("학번: "+bean.memNum);
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
    //로그아웃을 처리한다.
    private void logout(){
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        try{
            mFirebaseAuth.signOut();
            Toast.makeText(getActivity(),"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getActivity(), com.example.cho6.finalproject_guru2.Acitivity.LoginActivity.class);
            startActivity(i);
            //finish();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}