package com.example.cho6.finalproject_guru2.Acitivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cho6.finalproject_guru2.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegVoteActivity extends AppCompatActivity {
    int mYear,mMonth,mDay,mHour,mMinute;
    TextView mTxtDate;
    TextView mTxtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vote);
        //텍스트뷰 두개 연결
        mTxtDate=(TextView)findViewById(R.id.txtDate);
        mTxtTime=(TextView)findViewById(R.id.txtTime);

        //현재 날짜와 ㅣ간을 가져오기 위한 Calandar 인스턴스 선언
        Calendar cal=new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        mHour=cal.get(Calendar.HOUR_OF_DAY);
        mMinute=cal.get(Calendar.MINUTE);

        UpdateNow();
    }
    public void mOnClick(View v) {
        switch (v.getId()) {
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
}