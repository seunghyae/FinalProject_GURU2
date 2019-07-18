package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VoteBean implements Serializable {
    public long voteID;  //고유 번호
    public String votePicPath;
    public String voteTitle;
    public String voteSubTitle;
    public String voteStartDate;
    public String voteStartTime;
    public String voteEndDate;
    public String voteEndTime;
    public boolean Lock = false;
    public boolean overlap = false;
    public boolean startVote = false;
    public boolean endVote = false;
    public String voteCode;

    public long startVoteMilli;
    public long endVoteMilli;

    public List<ChoiceBean> choiceList;
}
