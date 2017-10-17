package com.weixin.vo.send;

import java.util.ArrayList;
import java.util.List;

public class OneNews {
	private List<InNews> articles = new ArrayList<>();;
	public List<InNews> getArticles() {
		return articles;
	}

	public void setArticles(List<InNews> articles) {
		this.articles = articles;
	}

}
