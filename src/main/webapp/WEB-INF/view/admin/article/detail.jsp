<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="/resource/css/bootstrap.min.css">
	<script type="text/javascript">
	/**
	*  审核文章 
	*  paramter： status 1 审核通过  2 审核不通过
	* return 
	*/
	function pass(status){
		//提交审核请求
		$.post("/admin/checkArticle",{status:status,articleId:'${article.id}'},function(obj){
			if(obj.result==1){
				alert("处理成功")
				$("#content-wrapper").load("/admin/manArticle")
			}else{
				alert(obj.errorMsg);
			}
		})//end post
		
	}//end function
	
	/**
	*  设置文章是否热门 
	*  paramter： status 0 非热门   1 热门
	* return 
	*/
	function hot(status){
		//设置热门请求
		$.post("/admin/sethot",{status:status,articleId:'${article.id}'},function(obj){
			if(obj){
				alert("操作成功!")
				$("#content-wrapper").load("/admin/manArticle")
			}
		})//end post
		
	}//end function
	
	function goBack(){
		$("#content-wrapper").load("/admin/manArticle")
	}
	
	</script>
</head>
<body>  
<div class="container">

	<button type="button" onclick="pass(1)" class="btn btn-info">通过</button>
	<button type="button" onclick="pass(2)" class="btn btn-warning">不通过</button>
	
	<button type="button" onclick="hot(1)" class="btn btn-info">设置热门</button>
	<button type="button" onclick="hot(0)" class="btn btn-warning">设置不热门</button>
	
	<button type="button" onclick="goBack()" class="btn btn-green">返回</button>
	


		<dl>
			<dt>${article.title }</dt>
			<hr>
			
			<dd>${article.content }</dd>
		</dl>
	
</div>


</body>
</html>