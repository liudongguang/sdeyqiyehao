package com.yzcx.api.vo.pageinfo;

import com.yzcx.api.vo.SSXX_anpai;

import java.util.List;

public class SSXXDisplayModle {

    private List<SSXX_anpai> list;
    private Integer pageNum;
    private Integer pages;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

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

    public List<SSXX_anpai> getList() {
        return list;
    }

    public void setList(List<SSXX_anpai> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SSXXDisplayModle{" +
                "list=" + list +
                ", pageNum=" + pageNum +
                ", pages=" + pages +
                '}';
    }
}
