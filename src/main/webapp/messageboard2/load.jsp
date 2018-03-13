<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<input type="hidden" value="${page.pageNum}" id="pageNumID"/>
<input type="hidden" value="${page.pages}" id="pagesID"/>
<c:choose>
    <c:when test="${fn:length(page.list) gt 0}">
        <c:forEach items="${page.list}" var="lymsg">
            <li target="_blank" class="item item-content blog_li">
                <div class="blog_divs">
                    <div class="item-media">
                        <a href="">
                            <img class="blog_head_img" width="48" src="${lymsg.wxheadimg}">
                        </a>
                    </div>
                    <div class="item-main">
                        <a href="">
                            <div class="item-title-row">
                                <h2 class="item-title jy_tit">${lymsg.wxqyusername}</h2>
                            </div>
                            <div class="item-title-row">
                                <div class="item-subtitle"><fmt:formatDate
                                        value="${lymsg.createtime}"
                                        pattern=" yyyy-MM-dd HH:mm:ss"/></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="blog_divs_pad">
                    <p class="blog_main_p">
                            ${lymsg.content}
                    </p>
                </div>
                <div class="blog_divs_say">
                    <c:if test="${fn:length(lymsg.pingLunByLY) gt 0}">
                        <span class="all_says">全部评论</span>
                        <c:forEach items="${lymsg.pingLunByLY}" var="pinglun">
                            <div class="says_txt">
                                <span>${pinglun.wxqyusername}：</span>
                                <span>${pinglun.hfnr}</span>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </li>
        </c:forEach>
    </c:when>
</c:choose>
