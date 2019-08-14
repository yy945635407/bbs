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
function recommend(){
	var currentRecommendInfo = $("#recommend").html();
	//alert(currentRecommendInfo);
	if($("#isAdmin").val() != "true"){
		if(currentRecommendInfo == "正在加载个性化数据..."){//利用ajax获取推荐
			$.ajax({
				url: "RecommendServlet",
				data:{},
				type: "post",
				dataType: "json",
				success: function(result){
					//alert(1);
					var htm = "";
					var posts = result.recommendPosts;
					//alert(result.info);
					$.each(posts, function(i, post){
						htm += "<div class='row'><div id='post' class='col-md-8'><a href='QueryPostByID?postID="+ post.postID
								+"'>" + post.title +"</a></div></div>";
					});
					//alert(2);
					$("#recommend").html(htm);
				}
			});	
		}
	}else{
		$("#recommend").hide();
	}
}
</script>
<style>
	.row{
	 margin: 30px;
	}
    #comment{
	  padding: 60px;
      border: 1px solid gray;
	 border-radius: 5px;
    }
    #content{
	  padding: 30px;
      border: 1px solid gray;
	  border-radius: 5px;
    }
    #post{
      display: flex;
      flex-direction: column;
	  padding: 60px;
      background-color: rgba(223, 162, 24, 0.15);
      border: 1px solid rgba(104, 24, 223, 0.15);
      border-radius: 5px;
    }
</style>
<title>${sessionScope.post.title}</title>
</head>
<body onload="recommend();">
<c:if test="${sessionScope.admin==null}">
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
</c:if>
<c:if test="${sessionScope.admin!=null}">
	<div class="header">
	    <h2 class="logo">欢迎</h2>
	    <input type="checkbox" id="chk">
	    <label for="chk" class="show-menu-btn">
	      <i class="fas fa-ellipsis-v"></i>
	    </label>
	
	    <ul class="menu">
	      <a href="index.jsp">首页</a>
	      <a href="SignOutServlet">注销</a>
	      <label for="chk" class="hide-menu-btn">
	        <i class="fas fa-times"></i>
	      </label>
	    </ul>
	</div>
</c:if>
<div class="container">

<!--
<c:if test="${sessionScope.admin==null}">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
	 </nav>
</c:if>
<c:if test="${sessionScope.admin!=null}">
	  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	    <div class="container">
	      <div class="navbar-header">
	        <a href="aindex.jsp" class="navbar-brand"> 首页</a>
	      </div>
	      <div id="navbar" class="collapse navbar-collapse">
	        <ul class="nav navbar-nav">
	          <li><a href="ASignOutServlet">注销</a></li>
	        </ul>
	      </div>
	    </div>
	 </nav>
</c:if>-->
	 <h1>${sessionScope.postBean.title}</h1>
	 <div class="row">
	 	<div class="col-md-6"><h2>作者:${sessionScope.postBean.author.userName}</h2></div>
	 	<c:if test="${sessionScope.admin==null&&sessionScope.postBean.liked==false}">
	 	<div class="col-md-1 col-offset-2"><a href="AddLikeServlet?postID=${sessionScope.postBean.postID}"><button class="btn btn-sm btn-primary btn-block" type="submit">点赞</button></a></div>
	 	</c:if>
	 	<c:if test="${sessionScope.admin==null&&sessionScope.postBean.liked==true}">
	 	<div class="col-md-1 col-offset-2"><a href="RemoveLikeServlet?postID=${sessionScope.postBean.postID}"><button class="btn btn-sm btn-primary btn-block" type="submit">取消点赞</button></a></div>
	 	</c:if>
	 	<c:if test="${sessionScope.admin==null}">
	 	<div class="col-md-3">点赞数:${sessionScope.postBean.likeNum}</div>
	 	</c:if>
	 	<c:if test="${sessionScope.admin==null&&sessionScope.postBean.collected==false}">
	 	<div class="col-md-1 col-offset-2"><a href="CollectPostServlet?postID=${sessionScope.postBean.postID}"><button class="btn btn-sm btn-primary btn-block" type="submit">收藏</button></a></div>
	 	</c:if>
	 	<c:if test="${sessionScope.admin==null&&sessionScope.postBean.collected==true}">
	 	<div class="col-md-1 col-offset-2"><a href="RemoveCollectionServlet?postID=${sessionScope.postBean.postID}"><button class="btn btn-sm btn-primary btn-block" type="submit">已收藏</button></a></div>
	 	</c:if>
	 	<!-- 管理员审核帖子 -->
	 	<c:if test="${sessionScope.admin!=null}">
	 	<div class="col-md-4 col-offset-2"><a href="ReviewPostServlet?postID=${sessionScope.postBean.postID}"><button class="btn btn-sm btn-primary btn-block" type="submit">审核</button></a></div>
	 	</c:if>
	 </div>
	  <div class="row">
	 	<div class="col"></div>
	 </div>
	 <div class="row"><div class="col-md-12" id="content">${sessionScope.postBean.content}</div></div>
	 <h1>评论</h1>
		<c:forEach var="commentBean" items="${sessionScope.postBean.commentBeans}">
			<div class="row">
				<div id="commenter" class="col-md-2">评论者：${commentBean.commenter.userName}</div>
				<div id="comment" class="col-md-8">${commentBean.content}</div>
				<div id="commentTime" class="col-md-2">评论时间：${commentBean.publishTime}</div>
			</div>
		</c:forEach>
	 
		
	<%
	//按钮显示设置
	%>
	<div class="row">
	<c:choose>
		<c:when test="${sessionScope.page.totalPage<=1}">
			<div class="col-md-2"></div>
			<div class="col-md-2"></div>
			<div class="col-md-2">只有一页</div>
			<div class="col-md-4"></div>
		</c:when>
		<c:when test="${sessionScope.page.currentPage == (sessionScope.page.totalPage-1)}">
			<div class="col-md-2"><a href="QueryPostByID?currentPage=0&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">首页</button></a></div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.currentPage-1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">上一页</button></a></div>
			<div class="col-md-2">当前第${sessionScope.page.currentPage+1}页</div>
			<div class="col-md-4"></div>
		</c:when>
		<c:when test="${sessionScope.page.currentPage == 0}">
			<div class="col-md-2 col-md-offset-4">当前第${sessionScope.page.currentPage+1}页</div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.currentPage+1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">下一页</button></a></div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.totalPage-1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">尾页</button></a></div>
		</c:when>
		<c:otherwise>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=0&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">首页</button></a></div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.currentPage-1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">上一页</button></a></div>
			<div class="col-md-2">当前第${sessionScope.page.currentPage+1}页</div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.currentPage+1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">下一页</button></a></div>
			<div class="col-md-2"><a href="QueryPostByID?currentPage=${sessionScope.page.totalPage-1}&pageSize=${sessionScope.page.pageSize}&postID=${sessionScope.postBean.postID}"><button class="btn btn-default" type="submit">尾页</button></a></div>
		</c:otherwise>
	</c:choose>
	
	<div class="col-md-2">
		<select id="select" onchange="window.location=this.value">
			<option value="#" selected="selected">每页显示评论数目<!-- (${sessionScope.page.pageSize})  --></option>
			<option id="select_6" value="QueryPostByID?currentPage=${sessionScope.page.currentPage}&pageSize=6&postID=${sessionScope.postBean.postID}">6</option>
			<option id="select_8" value="QueryPostByID?currentPage=${sessionScope.page.currentPage}&pageSize=8&postID=${sessionScope.postBean.postID}">8</option>
			<option id="select_10" value="QueryPostByID?currentPage=${sessionScope.page.currentPage}&pageSize=10&postID=${sessionScope.postBean.postID}">10</option>
		</select>
	</div>
	</div>
	<c:if test="${sessionScope.admin==null}">
		<h1>添加评论</h1>
		<form class="addcomment-form" action="PublishCommentServlet">
		<div class="row" id="addcomment">
			<input type="hidden" value="${sessionScope.postBean.postID}" name="postID"/>
		 	<div class="col-md-12">
		 	<textarea class="form-control" id="commentContent" name="content" rows="5" cols="4" required></textarea>
		 	</div>
		</div>
		<div class="row">
		 	<div class="col-md-4 col-md-offset-8"><button type="submit" class="btn btn-sm btn-primary btn-block" onclick="addcomment">提交</button></div>
		</div>
		</form>
	</c:if>
	<h1>猜你喜欢</h1>
	<input type="hidden" id="isAdmin" value="${sessionScope.isAdmin}">
	<div class="row"><div id="recommend">正在加载个性化数据...</div></div>
	<c:forEach var="recommended" items="${sessionScope.recommendPosts}">
		<div class="row"><div id="post" class="col-md-8"><a href="QueryPostByID?postID=${recommended.postID}">${recommended.title}</a></div></div>
	</c:forEach>
</div>
</body>
</html>