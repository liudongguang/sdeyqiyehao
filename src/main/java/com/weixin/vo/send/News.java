package com.weixin.vo.send;

public class News extends BaseMsg {

	private OneNews news = new OneNews();

	public News() {
		this.setMsgtype("news");
	}

	public OneNews getNews() {
		return news;
	}

	public void setNews(OneNews news) {
		this.news = news;
	}
}
