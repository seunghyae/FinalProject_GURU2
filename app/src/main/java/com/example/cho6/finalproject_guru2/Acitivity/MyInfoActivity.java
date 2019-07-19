package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.R;
import com.example.cho6.finalproject_guru2.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyInfoActivity extends AppCompatActivity {

    //FireBase 인증객체
    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase mFirebaseDB = FirebaseDatabase.getInstance();
    FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_info);
        final TextView mTxtMemId=findViewById(R.id.txtMemID);
        final TextView mTxtMemName=findViewById(R.id.txtMemName);
        final TextView mTxtMemMajor=findViewById(R.id.txtMemMajor);
        final TextView mTxtMemNo=findViewById(R.id.txtMemNo);

        Button btnLogout=findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


        String email;
        email = mFirebaseUser.getEmail();
        mTxtMemId.setText("구글ID: "+email);

        mFirebaseDB.getReference().child("members").child(Utils.getUserIdFromUUID(email)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                MemberBean bean = dataSnapshot.getValue(MemberBean.class);
                String UUIDEmail = Utils.getUserIdFromUUID(mFirebaseUser.getEmail());
                if(TextUtils.equals(bean.memId, UUIDEmail)) {
                    mTxtMemName.setText("이름 : " + bean.memName);
                    mTxtMemMajor.setText("학과 : " + bean.memMajor);
                    mTxtMemNo.setText("학번 : " + bean.memNum);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //로그아웃을 처리한다.
    private void logout(){
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        try{
            mFirebaseAuth.signOut();
            Toast.makeText(this,"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
            Intent i=new Intent(this, com.example.cho6.finalproject_guru2.Acitivity.LoginActivity.class);
            startActivity(i);
            //finish();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}