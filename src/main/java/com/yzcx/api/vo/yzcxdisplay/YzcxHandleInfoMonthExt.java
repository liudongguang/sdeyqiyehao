package com.yzcx.api.vo.yzcxdisplay;

public class YzcxHandleInfoMonthExt {
    private String monthStr;
    private Double sumNum;

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public Double getSumNum() {
        return sumNum;
    }

    public void setSumNum(Double sumNum) {
        this.sumNum = sumNum;
    }

    @Override
    public String toString() {
        return "YzcxHandleInfoMonthExt{" +
                "monthStr='" + monthStr + '\'' +
                ", sumNum=" + sumNum +
                '}';
    }
}
