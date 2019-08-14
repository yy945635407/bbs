<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet" type="text/css"  href="navigator.css">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
function enableSubmit(bool){
	if(bool) $("#submit").removeAttr("disabled");
	else $("#submit").attr("disabled","disabled");
	}
function testpwd(){
	var pwd1=$("#pwd1").val();
	var pwd2=$("#pwd2").val();
	if(pwd1!=pwd2) {
		enableSubmit(false);
		$("#v_pwd").show();
		$("#v_pwd").html("两次密码输入不一致");
	}else {
		enableSubmit(true);
		$("#v_pwd").hide();
		}
}
</script>
<title>修改密码</title>
</head>
<body>
<div class="header">
    <h2 class="logo">欢迎</h2>
    <input type="checkbox" id="chk">
    <label for="chk" class="show-menu-btn">
      <i class="fas fa-ellipsis-v"></i>
    </label>

    <ul class="menu">
      <a href="index.jsp">首页</a>
      <a href="mycenter.jsp">个人中心</a>
      <a href="SignOutServlet">注销</a>
      <label for="chk" class="hide-menu-btn">
        <i class="fas fa-times"></i>
      </label>
    </ul>
</div>
<div class="container">
	<!--  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	    <div class="container">
	      <div class="navbar-header">
	        <a href="index.jsp" class="navbar-brand"> 首页</a>
	      </div>
	      <div id="navbar" class="collapse navbar-collapse">
	        <ul class="nav navbar-nav">
	          <li><a href="mycenter.jsp">个人中心</a></li>
	          <li><a href="SignOutServlet">注销</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav> -->
	<form action="ResetPwdServlet" method="post" id="form">
		<h1 class="form-resetpwd-heading">修改密码</h1>
		<label>原密码</label>
		<input type="password" name="oripwd" placeholder="原密码" id="name" onblur="testoripwd();" class="form-control" required autofocus/><span id="oripwd">${requestScope.oripwderror}${requestScope.nopwd}</span><br/> 
	    <br/>
	    <label>新密码</label>
	    <input type="password" name="npwd" placeholder="新密码" id="pwd1"  onblur="testnullpwd();" class="form-control" required/><span id="t_pwd">${requestScope.nonpwd}</span><br/>
	    <br/>
	    <label>确认新密码</label>
	    <input type="password" name="npwd2" placeholder="确认密码" id="pwd2" onblur="testpwd();" class="form-control" required/><span id="v_pwd"></span><br/>
	    <br/>
	    <button id="submit" class="btn btn-md btn-primary btn-block" type="submit">确认修改</button><a href="signin.jsp">${requestScope.info}</a>
    
	</form>
</div>
</body>
</html>