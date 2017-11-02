<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<head>
    <base href="${pageContext.request.contextPath }/" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>微信企业号管理</title>

    <!-- CSS -->

    <link rel="stylesheet" href="assets/bootstrap4.0.0-beta/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/login/loginfonts.css">
    <link rel="stylesheet"
          href="assets/login/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/login/form-elements.css">
    <link rel="stylesheet" href="assets/login/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/login/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="assets/login/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="assets/login/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="assets/login/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="assets/login/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>
<!-- Top content -->
<div class="top-content">
    <div class="inner-bg">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="text w-50">
                    <h1>
                        <strong>微信企业号管理</strong>
                    </h1>
                    <div class="description">
                        <p>用户登陆</p>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="form-box w-25">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>系统登陆</h3>
                            <p>输入你的用户名与密码登陆:</p>
                            <p style="color: red">${requestScope.message }</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="shirotest/login" method="post"
                              class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label> <input required
                                    type="text" name="username" placeholder="用户名..." value=""
                                    class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label> <input required
                                    type="password" name="password" placeholder="密码..."  value=""
                                    class="form-password form-control" id="form-password">
                            </div>
                            <button type="submit" class="btn">登陆</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Javascript -->
<script language="javascript" type="text/javascript" src="assets/js/jquery3.2.1.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/popper.js"></script>
<script language="javascript" type="text/javascript" src="assets/bootstrap4.0.0-beta/js/bootstrap.js"></script>
<script src="assets/login/js/jquery.backstretch.min.js"></script>
<script type="text/javascript" src="assets/login/js/scripts.js"></script>
<!--[if lt IE 10]>
<script type="text/javascript" src="assets/login/js/placeholder.js"></script>
<![endif]-->
</body>
</html>