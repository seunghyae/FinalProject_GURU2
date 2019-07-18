package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.List;

public class MemberBean implements Serializable  {
    public String Id;
    public String memId;
    public String memName;
    public String memMajor;
    public String memNum;
    public boolean isAdmin = false;

    public List<VoteBean> voteList;
}
