<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <base href="${pageContext.request.contextPath }/"/>
    <title>导入人员</title>
    <link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-10">
            <form method="post" action="sendwxmsg/handlerBirthExcel" enctype="multipart/form-data" id="subfileform">
                <div class="form-group">
                    <label for="exampleInputPassword1">Excel文件</label>
                    <input type="file" class="form-control" name="rydata" id="exampleInputPassword1" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
                </div>
                <button type="submit" class="btn btn-primary">上传</button>
            </form>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript" src="assets/js/jquery3.2.1.js"></script>
<script language="javascript" type="text/javascript" src="assets/menu/js/menu.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/pajax/jquery.pjax.js"></script>
<script language="javascript" type="text/javascript" src="assets/nprogress-0.2.0/nprogress.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jquery.form.min.js"></script>
<script language="javascript" type="text/javascript" src="assets/layer/layer.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/commonMain2.js"></script>
<script language="javascript" type="text/javascript" src="assets/birthday/importexcel.js"></script>
</body>
</html>
