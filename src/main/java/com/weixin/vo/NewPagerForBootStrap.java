package com.weixin.vo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class NewPagerForBootStrap extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7451256094024946259L;
	private int pageNum;// 当前页
	private int pages;// 总页数
	private int pageSize;// 一页中的条数
	private int total;// 总条数
	private String url;

	public int doStartTag() throws JspException {
		if (pages > 0) {
			JspWriter out = pageContext.getOut();
			int wenhaoIndex = url.indexOf("?");
			StringBuilder outStr = new StringBuilder("<div><ul class=\"pagination\">");
			outStr.append("<li ");
			if (pageNum == 1) {
				outStr.append("class=\"disabled\"");
			}
			outStr.append("><a href=\"");
			if (pageNum != 1) {
				outStr.append(url);
				if (-1 != wenhaoIndex) {
					outStr.append("&");
				} else {
					outStr.append("?");
				}
				outStr.append("pageNum=").append(pageNum - 1);
			} else {
				outStr.append("javascript:;");
			}
			outStr.append("\">");
			outStr.append("&laquo;</a></li>");
			for (int i = 1; i <= pages; i++) {
				if (i == pageNum) {
					outStr.append("<li class=\"active\"><a href=").append(url);
					if (-1 != wenhaoIndex) {
						outStr.append("&");
					} else {
						outStr.append("?");
					}
					outStr.append("pageNum=").append(i);
					outStr.append(">").append(i).append("</a></li>");
				} else {
					if (pageNum - i >= 4 && i != 1) // 只显示当前页前三个页码
					{
						outStr.append("<li class=\"disabled\"><a href=\"javascript:;\">...</a></li>");
						i = pageNum - 4;// 将页码跳到没有省略的页码
					} else {
						if (i >= pageNum + 3 && i != pages) // 只显示当前页的后两个页码
						{
							outStr.append("<li class=\"disabled\"><a href=\"javascript:;\">...</a></li>");
							i = pages; // 将页码跳到最后一页
						}
						outStr.append("<li><a href=").append(url);
						if (-1 != wenhaoIndex) {
							outStr.append("&");
						} else {
							outStr.append("?");
						}
						outStr.append("pageNum=").append(i);
						outStr.append(">").append(i).append("</a></li>");
					}
				}
			}
			outStr.append("<li ");
			if (pageNum == pages) {
				outStr.append("class='disabled'");
			}
			outStr.append("><a href=\"");
			if (pageNum != pages) {
				outStr.append(url);
				if (-1 != wenhaoIndex) {
					outStr.append("&");
				} else {
					outStr.append("?");
				}
				outStr.append("pageNum=").append(pageNum + 1);
			} else {
				outStr.append("javascript:;");
			}
			outStr.append("\">");
			outStr.append("&raquo;</a></li>");
			///
			outStr.append("<li class='disabled'><a href=\"javascript:;\"><i>");
			outStr.append("共").append(total).append("条记录");
			outStr.append("</i></a></li>");
			///
			outStr.append("</ul></div>");
			try {
				out.write(outStr.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.doStartTag();

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}