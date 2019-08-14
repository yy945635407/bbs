<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script>
function setOldTag(){
	var tag = $("#select").val();
	$("#tag-old").attr("value",tag);
	$("#currentTag").html("当前标签：" + tag);
	$("#tag").attr("value", tag);
}
function setNewTag(){
	var tag = $("#tag-new").val();
	//alert(tag);
	$("#currentTag").html("当前标签：" + tag);
	$("#tag").attr("value", tag);
}
</script>
<style>
	.row{
	 margin: 30px;
	 border: 3px solid rgb(red, red, red);
	}
    #comment{
	  padding: 60px;
      
    }
</style>
<title>添加收藏</title>
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
	 <form class="collection-info" method="post" action="AddCollectionServlet">
	 	<h1>添加收藏</h1>
	 	<div class="row">
	 	<input type="hidden" name="postID" value="${requestScope.postID}"/>
	 	<input type="hidden" id="tag-old" name="tag-old" value="" />
		 	<c:if test="${requestScope.tags.size()!=0}">
		 	<div class="col-md-3"><h3>选择已存在的标签</h3></div>
		 	<div class="col-md-4">
                  <select onclick="setOldTag();" id="select">
                  <option disabled="disabled">选择已存在的标签</option>
                    <c:forEach var="tag" items="${requestScope.tags}">
			          	 	<option class="tag-item">${tag}</option>
			        </c:forEach>
                  </select>
             </div>
	         </c:if>
	         <c:if test="${requestScope.tags.size()==0}">
	         	<div class="col-md-3"><h3>您还没有标签</h3></div>
	         </c:if>
	    </div>
	    <br/><h2>或者</h2><br/>
	    <div class="row">
	      <div class="col-md-2"><h3>自定义标签</h3></div>
	      <div class="col-md-3"><input type="text" class="form-control" id="tag-new" placeholder="自定义标签" onblur="setNewTag();"/></div>
	   
	 	</div>
	 	<div class="row"><div class="col-md-12"><h2 id="currentTag">当前标签：</h2></div></div>
	 	<input type="hidden" class="form-control" name="tag" id="tag" />
	 	<button class="btn btn-md btn-primary btn-block" type="submit">确认</button>
	 </form>

</body>
</html>