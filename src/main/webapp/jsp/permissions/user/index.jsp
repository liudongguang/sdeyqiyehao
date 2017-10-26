<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="clearfix"></div>
<div class="form-group">
    <button class="btn btn-primary-outline" data-pjax href="pajaxapimain/permissions/user/add.jsp">新增</button>
</div>
<div class="table-responsive">
    <table id="dataTable" class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>用户名</th>
            <th>创建时间</th>
            <th>包含角色</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="obj">
            <tr id="${obj.uid}">
                <td>${obj.username}</td>
                <td><fmt:formatDate value="${obj.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <c:forEach items="${obj.roles}" var="permission">
                        ${permission.rolename}
                    </c:forEach>
                </td>
                <td><a class="btn btn-success-outline btn-sm" data-pjax  href="permission_shiro/distributionRole?uid=${obj.uid}&rolename=${obj.username}">角色分配</a>
                    <a class="btn btn-danger-outline btn-sm" data-pjax delmark href="permission_shiro/deleteUser?uid=${obj.uid}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!--
分页开始
-->
<div id="pagesDIV" style="margin: 0 auto"></div>
<input id="pageNum" type="hidden" value="${page.pageNum}"/>
<input id="pageSize" type="hidden" value="${page.pageSize}"/>
<input id="pages" type="hidden" value="${page.pages}"/>
<input id="total" type="hidden" value="${page.total}"/>
<input id="loadDataURL" type="hidden" value="permission_shiro/getUserPageInfo"/>
<input id="searFormID" type="hidden" value="subForm"/>
<input id="containerID" type="hidden" value="mainContainer"/>

<!--
分页结束
-->
<script language="javascript" type="text/javascript">
    jPageInit();
</script>


