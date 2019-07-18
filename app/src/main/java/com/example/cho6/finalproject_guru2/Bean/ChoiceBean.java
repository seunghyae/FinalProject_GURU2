package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;

public class ChoiceBean implements Serializable {

    public boolean isOverlap; //중복허용
    public String itemTitle;

    public ChoiceBean() {

    }

    public ChoiceBean(boolean isOverlap, String itemTitle) {
        this.isOverlap = isOverlap;
        this.itemTitle = itemTitle;
    }

}
