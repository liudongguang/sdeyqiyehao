<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="clearfix"></div>
<div class="form-group">
    <button class="btn btn-outline-primary" pajax-data href="jsp/permissions/role/add.jsp">新增</button>
</div>
<div class="table-responsive">
    <table id="dataTable" class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>角色名</th>
            <th>角色描述</th>
            <th>包含权限</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="obj">
            <tr id="${obj.roleid}">
                <td>${obj.rolename}</td>
                <td>${obj.roledescription}</td>
                <td>
                    <c:forEach items="${obj.permissions}" var="permission">
                        ${permission.permissionname}
                    </c:forEach>
                </td>
                <td><a class="btn btn-success-outline btn-sm" pajax-data  href="permission_shiro/distributionPermission?uid=${obj.roleid}&rolename=${obj.rolename}">权限分配</a>
                    <a class="btn btn-danger-outline btn-sm" pajax-data del href="permission_shiro/deleteRole?uid=${obj.roleid}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="pagesDIV" class="d-flex justify-content-end align-items-center">
</div>
<input id="pageNum" type="hidden" value="${page.pageNum}"/>
<input id="pageSize" type="hidden" value="${page.pageSize}"/>
<input id="pages" type="hidden" value="${page.pages}"/>
<input id="total" type="hidden" value="${page.total}"/>
<input id="loadDataURL" type="hidden" value="permission_shiro/getRolePageInfo"/>
<input id="searFormID" type="hidden" value="subForm"/>
<input id="containerID" type="hidden" value="pajaxMainContainer"/>
<script language="javascript" type="text/javascript">
    jQuery.ajax({
        url: "assets/js/page/jPageExt.js",
        dataType: "script",
        cache: true
    })
    initPajaxRequestForClick("#pajaxMainContainer");
</script>



