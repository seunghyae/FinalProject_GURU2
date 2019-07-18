package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;
import java.util.List;

public class VoteBean implements Serializable {
    public long voteID;  //고유 번호
    public String votePicPath;
    public String voteTitle;
    public String voteSubTitle;
    public String voteStartDate; // 투표 시작 날짜
    public String voteStartTime; // 투표 시작 시간
    public String voteEndDate; // 투표 종료 날짜
    public String voteEndTime; // 투표 종료 시간
    public boolean Lock = false;
    public boolean overlap = false;
    public List<ChoiceBean> ChoiceList;
    public boolean startVote = false; // 투표 시작 버튼 클릭 여부
    public boolean endVote = false; // 투표 종료 버튼 클릭 여부
    public String voteCode;

    public long startVoteMilli;
    public long endVoteMilli;
}
