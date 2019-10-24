<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>文章详情</title>
	<!-- <link rel="stylesheet"
	href="/resource/css/bootstrap.min.css"> -->
	<link rel="stylesheet" href="//cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="//cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="//cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	
</head>
<body>
<div class="container">

<div id="myCarousel" class="carousel slide">
	<!-- 轮播（Carousel）指标 -->
	<ol class="carousel-indicators">
		<c:forEach items="${article.imgList}" var="imgobj" varStatus="imgindex">
			<li data-target="#myCarousel" data-slide-to="${imgindex.index}" ${imgindex.index==1? "class=\"active\"":""}></li>
		</c:forEach>
	</ol>   
	<!-- 轮播（Carousel）项目 -->
	<div class="carousel-inner">
		<c:forEach items="${article.imgList}" var="imgobj" varStatus="imgindex">
			<div class="item ${imgindex.index==0?"active":""}">
				<img src="/pic/${imgobj.picUrl}" alt="${imgobj.desc}">
			</div>
		</c:forEach>
	</div>
	<!-- 轮播（Carousel）导航 -->
	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div> 

</div>

</body>
</html>