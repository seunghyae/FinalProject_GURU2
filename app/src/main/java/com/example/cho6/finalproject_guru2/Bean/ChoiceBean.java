package com.example.cho6.finalproject_guru2.Bean;

import java.io.Serializable;

public class ChoiceBean implements Serializable {

    public boolean isSelect; //선택여부
    public String itemTitle;

    public ChoiceBean() {

    }

    public ChoiceBean(boolean isSelect, String itemTitle) {
        this.isSelect = isSelect;
        this.itemTitle = itemTitle;
    }

}
