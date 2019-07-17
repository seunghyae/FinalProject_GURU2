package com.example.cho6.finalproject_guru2.Acitivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class RegVoteActivity extends AppCompatActivity {

    //객체 선언
    int mYear,mMonth,mDay,mHour,mMinute;
    TextView mTxtDate;
    TextView mTxtTime;
    EditText mEdtTitle, mEdtDetail, mItem1, mItem2;
    Button mBtnChangeTime, mBtnChangeDate;
    Switch mSwitchPublic;
    CheckBox mCheckOverlap;

    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vote);

        //텍스트뷰 두개 연결
        mTxtDate=(TextView)findViewById(R.id.txtDate);
        mTxtTime=(TextView)findViewById(R.id.txtTime);
        mSwitchPublic = findViewById(R.id.switchPublic);
        mCheckOverlap = findViewById(R.id.checkBox);

        //객체 선언
        mEdtTitle = findViewById(R.id.edtTitle);
        mEdtDetail = findViewById(R.id.edtDetail);
        mItem1 = findViewById(R.id.item1);
        mItem2 = findViewById(R.id.item2);

        Button mBtnReg = findViewById(R.id.btnReg);
        mBtnChangeDate = findViewById(R.id.btnChangeDate);
        mBtnChangeTime = findViewById(R.id.btnChangeTime);

        mBtnChangeDate.setOnClickListener(mClicks);
        mBtnChangeTime.setOnClickListener(mClicks);
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegVoteActivity.this, "onClick...", Toast.LENGTH_LONG).show();
                addVote();
            }
        });

       /* mSwitchPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true)
                Toast.makeText(getApplicationContext(), "비공개 투표로 설정 되었습니다", Toast.LENGTH_LONG).show();
            }
        });

        mCheckOverlap.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "중복응답이 허용되었습니다.", Toast.LENGTH_LONG).show();
            }
        });*/

        //현재 날짜와 시간을 가져오기 위한 Calandar 인스턴스 선언
        Calendar cal=new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        mHour=cal.get(Calendar.HOUR_OF_DAY);
        mMinute=cal.get(Calendar.MINUTE);

        UpdateNow();
    } //end onCreate()

    private View.OnClickListener mClicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
                case R.id.btnChangeDate:
                    //리스너 등록
                    new DatePickerDialog(RegVoteActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    break;

                case R.id.btnChangeTime:
                    //리스너 등록
                    new TimePickerDialog(RegVoteActivity.this, mTimeSetListner, mHour, mMinute, false).show();
                    break;

            }
        }

    };


    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mDateSetListner=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear=year;
                    mMonth=monthOfYear;
                    mDay=dayOfMonth;
                    //텍스트 뷰의 값 업테이트
                    UpdateNow();
                }
            };
    //시간 대화상자 리스너 부분
    TimePickerDialog.OnTimeSetListener mTimeSetListner=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                    //사용자가 입력한 값 가져옴
                    mHour=hourofDay;
                    mMinute=minute;

                    UpdateNow();
                }
            };

    void UpdateNow(){
        mTxtDate.setText(String.format("%d/%d/%d",mYear,mMonth+1,mDay));
        mTxtTime.setText(String.format("%d:%d",mHour,mMinute));
    }

    public void addVote(){
        Toast.makeText(this, "addVote()실행", Toast.LENGTH_LONG).show();

        VoteBean voteBean = new VoteBean();
        //데이터베이스에 저장한다.
        voteBean.voteTitle = mEdtTitle.getText().toString();
        voteBean.voteSubTitle = mEdtDetail.getText().toString();
        voteBean.voteDate = mTxtDate.getText().toString();
        voteBean.voteTime = mTxtTime.getText().toString();
        voteBean.voteID = System.currentTimeMillis();

        ChoiceBean choiceBean = new ChoiceBean();
        choiceBean.item1 = mItem1.getText().toString();
        choiceBean.item2 = mItem2.getText().toString();

        if(mSwitchPublic.isChecked()){
            voteBean.Lock = true;
        }

        if(mCheckOverlap.isChecked()){
            voteBean.overlap = true;
        }

        voteBean.startVote = false;


        //Firebase 데이터베이스에 투표를 등록한다.
        DatabaseReference dbRef = mFirebaseDatabase.getReference();
        dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);
        dbRef.child("votes").child(String.valueOf(voteBean.voteID)).child("choiceList").setValue(choiceBean);
        finish();
    }

    public static String getUserIdFromUUID(String userEmail) {
        long val = UUID.nameUUIDFromBytes(userEmail.getBytes()).getMostSignificantBits();
        return String.valueOf(val);
    }
}