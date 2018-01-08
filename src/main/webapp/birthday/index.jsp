<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <base href="${pageContext.request.contextPath }/"/>
    <title>生日祝福</title>
    <link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css"/>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-size: 42px;
        }
    </style>
</head>
<body style="width: 100%; height: 100%; -webkit-background-size: cover;-moz-background-size: cover;-o-background-size: cover;background-size: cover;">
<div style="display:block;">

    <img src="assets/img/birthday.jpg" class="img-fluid" alt="Responsive image"/>

    <div style="position:absolute;color:#8d6551;top:150px;left:20px;padding: 15px;">
        <p>亲爱的${param.xingming}：</p>
        <p style="text-indent:2em">时光的树越长越葱茏
        </p>
        <p style="text-indent:2em">生命的花越开越艳丽！</p>
        <p style="text-indent:2em">一年一度，每一次生日都是崭新的开始
        </p>
        <p style="text-indent:2em">每一次祝福都是浓浓的情谊！</p>
        <p style="text-indent:2em">${param.month}月${param.day}日是您的生日，山东大学齐鲁第二医院祝您生日快乐！愿您幸福安康、顺意连年！</p>
        <p style="text-indent:2em">100元生日补贴已发放到您的善德餐卡中，浓浓的祝福请您收下。</p>
        <p style="text-align: right">山东大学齐鲁第二医院工会</p>
    </div>
</div>
<script language="javascript" type="text/javascript" src="assets/js/jquery3.2.1.js"></script>
</body>
</html>
