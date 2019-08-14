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
<style>
	.row{
	margin: 30px;
	}
</style>
<script>

function changeSize(){
	var key = $("#key").val();
	if(key!=""){
		search(-2,1);//搜索第一页
	}
	
}
function search(dir,first){
	var currentPage = $("#currentPage").attr("value");
	var pageSize = $("#select").val();
	var totalPage = $("#totalPage").attr("value");
	dir = dir==null?0:dir;
	//alert($("#select").val());
	//alert(1);
	currentPage = currentPage==""?0:currentPage;
	pageSize = pageSize==""?6:pageSize;
	if(dir==-2){//首页
		currentPage="0";
	}else if(dir==2){//末页
		currentPage=(Number(totalPage)-1).toString();
	}else{//前一页或后一页
		currentPage = (Number(currentPage)+dir).toString();
	}
	//alert(first);
	if(first){//点击搜索按钮时只展示第一页
		currentPage = "0";
	}
	//alert(currentPage);
	$.ajax({
		url:"QueryMyCollectionServlet",
		data:{"currentPage":currentPage, "pageSize":pageSize},
		type:"post",
		dataType:"json",
		success:function(result){
			//更新两个变量的值
			$("#currentPage").attr("value", result.page.currentPage);
			$("#select").attr("value", result.page.pageSize);
			$("#totalPage").attr("value", result.page.totalPage);
			//alert(1);
			if(result.info=="yes"){//展示查询结果
				var htm = "<div class='row'><div class='col-md-8'>标题</div>" +
				"<div class='col-md-2'>所属标签</div></div>";
				var collections = result.collections;
				$.each(collections, function(i, collection){
					htm += "<div class='row'><div id='post' class='col-md-8'><a href='QueryPostByID?postID=" +
							collection.postID + "'>" + collection.title + "</a></div><div class='col-md-2'>"+ collection.tag +
							"</div><div class='col-md-2'><a href='RemoveCollectionByIDServlet?collectionID="+ collection.collectionID +
							"'><button class='btn btn-default' type='submit'>取消收藏</button></a></div></div>"
					
				});
				$("#result").html(htm);
			}else{
				$("#result").html("<div>没有找到相关内容</div>");
			}
			//展示分页按钮
			var pageHelp = "";
			var page = result.page;
			//alert(page.pageSize);
			if(page.totalPage<=1){//只有一页
				pageHelp += "<div class='col-md-2'></div>"+"<div class='col-md-'></div>" +
			    "<div class='col-md-2'>只有一页</div>" + "<div class='col-md-4'></div>";
			}else if(page.currentPage==0){//首页
				pageHelp += "<div class='col-md-2 col-md-offset-4'>当前第"+(parseInt(page.currentPage)+1)+"页</div>"+
				"<div class='col-md-2'><button onclick='search(1);' class='btn btn-default' type='submit'>下一页</button></div>" +
				"<div class='col-md-2'><button onclick='search(2);' class='btn btn-default' type='submit'>尾页</button></div>";
			}else if(page.currentPage==page.totalPage-1){//末页
				pageHelp += "<div class='col-md-2'><button onclick='search(-2);' class='btn btn-default' type='submit'>首页</button></div>" +
				"<div class='col-md-2'><button onclick='search(-1);' class='btn btn-default' type='submit'>上一页</button></div>" +
				"<div class='col-md-2'>当前第"+(parseInt(page.currentPage)+1)+"页</div>"+
				"<div class='col-md-4'></div>";
			}else{//中间页
				pageHelp += "<div class='col-md-2'><button onclick='search(-2);' class='btn btn-default' type='submit'>首页</button></div>"+
				"<div class='col-md-2'><button onclick='search(-1);' class='btn btn-default' type='submit'>上一页</button></div>"+
				"<div class='col-md-2'>当前第"+(parseInt(page.currentPage)+1)+"页</div>" +
				"<div class='col-md-2'><button onclick='search(1);' class='btn btn-default' type='submit'>下一页</button></div>" +
				"<div class='col-md-2'><button onclick='search(2);' class='btn btn-default' type='submit'>尾页</button></div>";
			
			}
			$("#pageHelper").html(pageHelp);
		}
		
	})
}
</script>
<title>收藏管理</title>
</head>
<body onload="search(0,1);">
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
	 <input type="hidden" id="currentPage" value="" />
	 <input type="hidden" id="pageSize" value="" />
	 <input type="hidden" id="totalPage" value="" />
	 <input type="hidden" id="preKey" value="" />
	 <div class="row" id="result"></div>
	 <div class="row">
	     <div class="col-md-10" id="pageHelper"></div>	
		 <div class="col-md-2">
			<select id="select" onchange="changeSize();">
				<option value="#" selected="selected">每页显示帖子数目</option>
				<option value="6" >6</option>
				<option value="8" >8</option>
				<option value="10" >10</option>
			</select>
		 </div>
	 </div>
</div>
</body>
</html>