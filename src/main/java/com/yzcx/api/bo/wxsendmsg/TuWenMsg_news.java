package com.yzcx.api.bo.wxsendmsg;

import java.util.ArrayList;
import java.util.List;

public class TuWenMsg_news {
    private List<TuWenMsg_news_articles> articles=new ArrayList<>();

    public List<TuWenMsg_news_articles> getArticles() {
        return articles;
    }

    public void setArticles(List<TuWenMsg_news_articles> articles) {
        this.articles = articles;
    }
}
