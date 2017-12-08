package com.yzcx.api.bo;
public class PageParam {
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageParam{" +
				"pageNum=" + pageNum +
				", pageSize=" + pageSize +
				'}';
	}
}
