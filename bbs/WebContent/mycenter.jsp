<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet" type="text/css"  href="navigator.css">
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<title>个人中心</title>
<style>
    .row{
      padding: 30px;
    }
</style>
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
	<!-- <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
	 <div class="row"><div class="col-md-4"><h1>个人中心</h1></div></div>
	 <div class="row"><div class="col-md-4"><a href="managecollection.jsp"><button id="submit" class="btn btn-md btn-primary btn-block" type="submit">收藏管理</button></a></div></div>
	 <div class="row"><div class="col-md-4"><a href="managecomment.jsp"><button id="submit" class="btn btn-md btn-primary btn-block" type="submit">评论管理</button></a></div></div>
	 <div class="row"><div class="col-md-4"><a href="managepost.jsp"><button id="submit" class="btn btn-md btn-primary btn-block" type="submit">帖子管理</button></a></div></div>
	 <div class="row"><div class="col-md-4"><a href="resetpwd.jsp"><button id="submit" class="btn btn-md btn-primary btn-block" type="submit">修改密码</button></a></div></div>
	 <div class="row"><div class="col-md-4"><a href="resetsign.jsp"><button id="submit" class="btn btn-md btn-primary btn-block" type="submit">修改签名</button></a></div></div>
</div>
</body>
</html>