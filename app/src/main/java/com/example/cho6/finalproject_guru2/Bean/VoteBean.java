package com.example.cho6.finalproject_guru2.Bean;

import java.util.List;

public class VoteBean {
    public long voteID;  //고유 번호
    public String votePicPath;
    public String voteTitle;
    public String voteSubTitle;
    public String voteDate;
    public String voteTime;
    public boolean Lock = false;
    public boolean overlap = false;
    public List<ChoiceBean> ChoiceList;
    public boolean startVote = false;

    public long startVoteMilli;
    public long endVoteMilli;
}
