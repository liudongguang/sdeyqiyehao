package com.yzcx.api.vo.highchat;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by LiuDongguang on 2017/5/24.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Title {
    private String text=" ";
    private String  align;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
