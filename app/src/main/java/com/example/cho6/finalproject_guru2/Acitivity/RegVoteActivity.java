package com.example.cho6.finalproject_guru2.Acitivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cho6.finalproject_guru2.Bean.ChoiceBean;
import com.example.cho6.finalproject_guru2.Bean.VoteBean;
import com.example.cho6.finalproject_guru2.Database.FileDB;
import com.example.cho6.finalproject_guru2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class RegVoteActivity extends AppCompatActivity {

    //객체 선언
    int mYear,mMonth,mDay,mHour,mMinute;
    TextView mTxtStartDate, mTxtStartTime, mTxtEndDate, mTxtEndTime;
    EditText mEdtTitle, mEdtDetail, mItem1, mItem2;
    Button mBtnStartDate, mBtnStartTime, mBtnEndDate, mBtnEndTime, mBtnReg;
    Switch mSwitchPublic;
    CheckBox mCheckOverlap;
    Context mContext;
    EditText mEdtCode;
    ImageView mImgLock;


    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vote2);

        //텍스트뷰 두개 연결
        mBtnStartDate = findViewById(R.id.btnStartDate);
        mBtnStartTime = findViewById(R.id.btnStartTime);
        mBtnEndDate = findViewById(R.id.btnEndDate);
        mBtnEndTime = findViewById(R.id.btnEndTime);

        mTxtStartDate = findViewById(R.id.txtStartDate);
        mTxtStartTime = findViewById(R.id.txtStartTime);
        mTxtEndDate = findViewById(R.id.txtEndDate);
        mTxtEndTime = findViewById(R.id.txtEndTime);

        mSwitchPublic = findViewById(R.id.switchPublic);
        mCheckOverlap = findViewById(R.id.checkBox);

        //객체 선언
        mEdtTitle = findViewById(R.id.edtTitle);
        mEdtDetail = findViewById(R.id.edtDetail);
        mItem1 = findViewById(R.id.item1);
        mItem2 = findViewById(R.id.item2);
        mEdtCode=findViewById(R.id.edtCode);

        mBtnReg = findViewById(R.id.btnReg);

        mBtnStartDate.setOnClickListener(mClicks);
        mBtnStartTime.setOnClickListener(mClicks);
        mBtnEndDate.setOnClickListener(mClicks);
        mBtnEndTime.setOnClickListener(mClicks);


        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegVoteActivity.this, "onClick...", Toast.LENGTH_LONG).show();
                addVote();
            }
        });

        mSwitchPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    mEdtCode.setVisibility(View.VISIBLE);
                }
                else{
                    mEdtCode.setVisibility(View.INVISIBLE);
                }
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

       // UpdateNow();
    } //end onCreate()

    private View.OnClickListener mClicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
                case R.id.btnStartDate:
                    //리스너 등록
                    new DatePickerDialog(RegVoteActivity.this, mStartDateSetListner, mYear, mMonth, mDay).show();
                    break;
                case R.id.btnStartTime:
                    //리스너 등록
                    new TimePickerDialog(RegVoteActivity.this, mStartTimeSetListner, mHour, mMinute, false).show();
                    break;
                case R.id.btnEndDate:
                    new DatePickerDialog(RegVoteActivity.this, mEndDateSetListner, mYear, mMonth, mDay).show();
                    break;
                case R.id.btnEndTime:
                    new TimePickerDialog(RegVoteActivity.this, mEndTimeSetListner, mHour, mMinute, false).show();
                    break;
            }
        }

    };

    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mStartDateSetListner=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear=year;
                    mMonth=monthOfYear;
                    mDay=dayOfMonth;
                    //텍스트 뷰의 값 업테이트
                    UpdateStart();
                }
            };
    //시간 대화상자 리스너 부분
    TimePickerDialog.OnTimeSetListener mStartTimeSetListner=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                    //사용자가 입력한 값 가져옴
                    mHour=hourofDay;
                    mMinute=minute;

                    UpdateStart();
                }
            };

    //날짜 대화상자 리스너 부분
    DatePickerDialog.OnDateSetListener mEndDateSetListner=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear=year;
                    mMonth=monthOfYear;
                    mDay=dayOfMonth;
                    //텍스트 뷰의 값 업테이트
                    UpdateEnd();
                }
            };
    //시간 대화상자 리스너 부분
    TimePickerDialog.OnTimeSetListener mEndTimeSetListner=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                    //사용자가 입력한 값 가져옴
                    mHour=hourofDay;
                    mMinute=minute;

                    UpdateEnd();
                }
            };

    void UpdateStart() {
        mTxtStartDate.setText(String.format("%d-%02d-%02d", mYear, mMonth + 1, mDay));
        mTxtStartTime.setText(String.format("%d:%d:00", mHour, mMinute));
    }

    void UpdateEnd(){
        mTxtEndDate.setText(String.format("%d-%02d-%02d",mYear,mMonth+1,mDay));
        mTxtEndTime.setText(String.format("%d:%d:00",mHour,mMinute));    }

    public void addVote(){
        Toast.makeText(this, "addVote()실행", Toast.LENGTH_LONG).show();

        VoteBean voteBean = new VoteBean();
        //데이터베이스에 저장한다.
        voteBean.voteTitle = mEdtTitle.getText().toString();
        voteBean.voteSubTitle = mEdtDetail.getText().toString();
        voteBean.voteStartDate = mTxtStartDate.getText().toString();
        voteBean.voteStartTime = mTxtStartTime.getText().toString();
        voteBean.voteEndDate = mTxtEndDate.getText().toString();
        voteBean.voteEndTime = mTxtEndTime.getText().toString();
        voteBean.voteID = System.currentTimeMillis();

        if(mSwitchPublic.isChecked()){
            voteBean.Lock = true;
        }else {
            voteBean.Lock = false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //시작시간 TODO 바꿀 것!
            voteBean.startVoteMilli = sdf.parse(voteBean.voteStartDate + " " + voteBean.voteStartTime).getTime();
            //voteBean.startVoteMilli = System.currentTimeMillis();
            //종료시간
            voteBean.endVoteMilli = sdf.parse(voteBean.voteEndDate + " " + voteBean.voteEndTime).getTime();

            if(voteBean.endVoteMilli < voteBean.startVoteMilli) {
                Toast.makeText(this,"투표 종료 시점이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        if(mEdtCode!=null){
            voteBean.voteCode=mEdtCode.getText().toString();
        }


        //Firebase 데이터베이스에 투표를 등록한다.
        DatabaseReference dbRef = mFirebaseDatabase.getReference();
        dbRef.child("votes").child(String.valueOf(voteBean.voteID)).setValue(voteBean);
        dbRef.child("votes").child(String.valueOf(voteBean.voteID)).child("choiceList").setValue(choiceBean);
        dbRef.child("members").child(FileDB.getLoginMember(getBaseContext())).child("voteList").child(String.valueOf(voteBean.voteID)).setValue(voteBean);
        dbRef.child("members").child(FileDB.getLoginMember(getBaseContext())).child("voteList").child(String.valueOf(voteBean.voteID)).child("choiceList").setValue(choiceBean);
        finish();
    }

    public static String getUserIdFromUUID(String userEmail) {
        long val = UUID.nameUUIDFromBytes(userEmail.getBytes()).getMostSignificantBits();
        return String.valueOf(val);
    }
}