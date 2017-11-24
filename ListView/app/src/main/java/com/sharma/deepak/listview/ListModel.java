package com.sharma.deepak.listview;

/**
 * Created by deepak on 19-07-2017.
 */

public class ListModel {
    private String text;
    private boolean flag;
    private boolean checkValue;

    public boolean isCheckValue() {
        return checkValue;
    }

    public void setCheckValue(boolean checkValue) {
        this.checkValue = checkValue;
    }

    public ListModel(String text, boolean flag) {
        this.text = text;
        this.flag = flag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
