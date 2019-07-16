package com.example.cho6.finalproject_guru2.Acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cho6.finalproject_guru2.Bean.MemberBean;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();


    TextView mtxtId = findViewById(R.id.txtId);
    EditText mEdtName = findViewById(R.id.edtName);
    EditText mEdtMajor = findViewById(R.id.edtMajor);
    EditText mEdtNum = findViewById(R.id.edtNum);

    Button mBtnJoin = findViewById(R.id.btnJoin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mtxtId.setText(mFirebaseAuth.getCurrentUser().getEmail());

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinProcess();
            }
        });
    } //end onCreate()

    //회원가입 처리
    private void joinProcess() {

        //Firebase 데이터베이스에 메모를 등록한다.
        DatabaseReference dbRef = mFirebaseDatabase.getReference();
        String id = dbRef.push().getKey(); // key 를 메모의 고유 ID 로 사용한다.

        //데이터베이스에 저장한다.
        BoardBean boardBean = new BoardBean();
        boardBean.id = id;
        boardBean.userId = mFirebaseAuth.getCurrentUser().getEmail();
        boardBean.title = "메모 title";
        boardBean.contents = mEdtMemo.getText().toString();
        boardBean.imgUrl = imgUrl;
        boardBean.imgName = imgName;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        boardBean.date = sdf.format(new Date());

        //고유번호를 생성한다
        String guid = getUserIdFromUUID(boardBean.userId);
        dbRef.child("memo").child( guid ).child( boardBean.id ).setValue(boardBean);
        Toast.makeText(this, "게시물이 등록 되었습니다.", Toast.LENGTH_LONG).show();
        finish();

        //회원가입 완료
        Toast.makeText(this, "회원가입이 완료 되었습니다.", Toast.LENGTH_LONG).show();
        finish();
    }









}
