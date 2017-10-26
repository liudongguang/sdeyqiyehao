<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-5">
        <h3 class="text-center">[${param.rolename}]角色权限分配</h3>
        <form class="form-horizontal" id="subform" method="post" action="permission_shiro/saveRoleAndPermission">
            <input type="hidden" value="${param.uid}" name="roleID"/>
            <div id="permissionsContainerID" style="max-height: 400px;overflow: auto;min-height: 300px;padding: 10px;">
            </div>
        </form>
        <div class="float-right">
        <button class="btn btn-primary-outline waves-effect waves-light" id="subBT" type="submit"
                form="subform">
            保存
        </button>
        <button id="backBTID" class="btn btn-primary-outline waves-effect waves-light" type="button">返回</button>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript"
        src="assets/js/jsp/permissions/role/distribution_permission.js"></script>

