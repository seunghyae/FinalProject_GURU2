package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 투표한 항목 정보
 */
public class VotedBean implements Serializable {

    public long voteID;  //고유 번호
    public String userId;
    public String uuid;
    public List<ChoiceBean> choiceList;

}
