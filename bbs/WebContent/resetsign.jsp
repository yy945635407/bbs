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
<title>修改签名</title>
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
	<!--<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
	<h1>当前签名</h1>
	<h2 class="current-sign">${sessionScope.user.userSign}</h2>
	<br/><br/><br/>
	<form action="ResetSignServlet" method="post" id="form">
		<h1 class="form-resetsign-heading">修改签名</h1>
		<label>新签名</label>
		<textarea name="nsign" placeholder="新签名" class="form-control" rows="5" cols="4" required autofocus/></textarea>
		<br/>
	    <button type="submit" id="submit" class="btn btn-md btn-primary btn-block">确认修改</button><br/><a href="mycenter.jsp">${requestScope.info}</a>
	    
	</form>
</div>
</body>
</html>