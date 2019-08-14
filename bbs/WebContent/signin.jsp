<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<style>
    body{
      padding: 100px 100px;
    }
</style>
<title>登陆</title>
<script>
function identity(){
	var identity = $("#select").val();
	if(identity=="1"){//跳转用户登陆
		$("#form").attr("action","SignInServlet");
	}else{//跳转管理员登陆
		//alert(1);
		$("#form").attr("action","ASignInServlet");
	}
}
</script>
</head>
<body>
<div class="container">
  <div class="info">${sessionScope.timeout}</div>
  <div class="info">${sessionScope.info}</div><br/> <!-- 提示错误信息 -->
  <form class="form-signin" id="form" action="SignInServlet" method="post">
    <h1 class="form-signin-heading">欢迎光临</h1>
    <h2 class="form-signin-identityinfo">请选择您的身份</h2>
    <select id="select" onchange="identity();">
    	<option value="1" selected="selected">用户</option>
    	<option value="2">管理员</option>
    </select>
    <br/>
    <br/>
    <label for="inputText" >用户名</label>
    <input type="text" id="inputText" class="form-control" placeholder="用户名" name="uname" required autofocus>
    <br/>
    <label for="inputPassword" >密码</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="密码" name="upwd" required>
    <br/>
    <a href="signup.jsp">还没注册？点击注册</a><br/>
    <a href="asignup.jsp">注册成为管理员？</a>
    <button class="btn btn-md btn-primary btn-block" type="submit">登陆</button>
  </form>
</div>
</body>
</html>
