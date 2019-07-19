package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoiceBean implements Serializable {

    public boolean isSelect; //선택여부
    public String itemTitle;
    public int totalSelCount; //선택된 갯수
    private List<String> selectUserIdList;

    public ChoiceBean() {

    }

    public ChoiceBean(boolean isSelect, String itemTitle) {
        this.isSelect = isSelect;
        this.itemTitle = itemTitle;
    }

    public void plusTotalCount() {
        totalSelCount += 1;
    }

    public List<String> getSelectUserIdList() {
        if(selectUserIdList == null) {
            selectUserIdList = new ArrayList<>();
        }
        return selectUserIdList;
    }

    public void setSelectUserIdList(List<String> selectUserIdList) {
        this.selectUserIdList = selectUserIdList;
    }
}
