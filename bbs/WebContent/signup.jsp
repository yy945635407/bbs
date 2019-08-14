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
      padding-top: 50px;
    }
</style>
<script type="text/javascript">
function check(){
	var status = $("#info").html();
	if(stauts!=""){
		$("#info").show();
	}
}
function enableSubmit(bool){
	if(bool) $("#submit").removeAttr("disabled");
	else $("#submit").attr("disabled","disabled");
	}
function testname(){
	var uname=$("#name").val();
	$.post("RegisterAjaxServlet",
			"uname=" + uname,
			function(result){
				if(result=="用户名已存在"){
					enableSubmit(false);
					$("#v_name").show();
					$("#v_name").html("用户名已存在");
				}else{
					enableSubmit(true);
					$("#v_name").hide();
				}
			},
			"text");	
	
}
function testpwd(){
	var pwd1=$("#pwd1").val();
	var pwd2=$("#pwd2").val();
	if(pwd1!=pwd2) {
		enableSubmit(false);
		$("#v_pwd").show();
		$("#v_pwd").html("两次密码输入不一致");
		$("#pwd2").focus();
	}else {
		enableSubmit(true);
		$("#v_pwd").hide();
		}
}
</script>
<title>注册</title>
</head>
<body onload="check();">
<div class="container">
	<form action="SignUpServlet" method="post" id="form">
		<h1 class="form-signup-heading">用户注册</h1>
		<label for="inputText" >用户名</label>
	    <input type="text" id="name" class="form-control" placeholder="用户名" name="uname" onblur="testname();" required autofocus><span id="v_name"></span>
	    <br/>
	    <label for="inputPassword" >密码</label>
	    <input type="password" id="pwd1" class="form-control" placeholder="密码" name="upwd1" required>
	    <br/>
	    <label for="repeatPassword" >确认密码</label>
	    <input type="password" id="pwd2" class="form-control" placeholder="确认密码" name="upwd2" onblur="testpwd();" required><span id="v_pwd"></span>
	    <br/>
	    <button id="submit" class="btn btn-md btn-primary btn-block" type="submit">注册</button>
    
	</form>
</div>
</body>
</html>


