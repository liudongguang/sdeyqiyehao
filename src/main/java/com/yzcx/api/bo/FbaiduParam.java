package com.yzcx.api.bo;


import com.yzcx.api.util.SysConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by liudo on 2017/7/1.
 */
public class FbaiduParam {
    private String searVal;
    private int searchType = 1;
    private String yybaidentity;

    public String getYybaidentity() {
        return yybaidentity;
    }

    public void setYybaidentity(String yybaidentity) {
        this.yybaidentity = yybaidentity;
    }

    public String getSearVal() {
        return searVal;
    }

    public void setSearVal(String searVal) {
            this.searVal = searVal.toUpperCase();
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    @Override
    public String toString() {
        return "FbaiduParam{" +
                "searVal='" + searVal + '\'' +
                ", searchType=" + searchType +
                ", yybaidentity='" + yybaidentity + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(SysConstant.pattern_code.matcher("1a").matches());
        System.out.println(SysConstant.getPattern_str.matcher("abS").matches());
    }
}
