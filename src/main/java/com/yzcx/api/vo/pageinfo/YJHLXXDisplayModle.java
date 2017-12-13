package com.yzcx.api.vo.pageinfo;

import com.yzcx.api.vo.YJHLInfo;

import java.util.List;

public class YJHLXXDisplayModle {
    private List<YJHLInfo> list;
    private Integer pageNum;
    private Integer pages;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<YJHLInfo> getList() {
        return list;
    }

    public void setList(List<YJHLInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "YJHLXXDisplayModle{" +
                "list=" + list +
                ", pageNum=" + pageNum +
                ", pages=" + pages +
                '}';
    }
}
