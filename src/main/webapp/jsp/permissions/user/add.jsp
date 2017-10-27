<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="clearfix"></div>
<div class="col-md-5">
    <form class="form-horizontal" id="subform" method="post" action="permission_shiro/saveUser" checkurl="permission_shiro/checkUserName">
        <div class="form-group">
            <label class="control-label">用户名</label>
            <input name="username" type="text" required checkparam  class="form-control" placeholder="用户名名" maxlength="8"/>
        </div>
        <div class="form-group">
            <label class="control-label">密码</label>
            <input name="password" type="password" required checkparam  class="form-control" placeholder="密码" maxlength="20"/>
        </div>
        <div class="form-group text-center">
            <button class="btn btn-primary-outline waves-effect waves-light" id="subBT" type="submit" form="subform">保存</button>
            <button id="backBTID" class="btn btn-primary-outline waves-effect waves-light" type="button">返回</button>
        </div>
    </form>
</div>
<script language="javascript" type="text/javascript" src="assets/js/jsp/permissions/user/add.js"></script>

