<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>图片文章</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="/resource/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<style type="text/css">
</style>
</head>
<body>
	<%-- <jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include> --%>

	<div class="container">
	<div id="carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
						<c:forEach items="${article.imgList}" var="imgobj" varStatus="imgindex">
							<li data-target="#carousel" data-slide-to="${imgindex.index}" ${imgindex.index==0?"class=\"active\"":""}></li>					
						</c:forEach>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${article.imgList}" var="imgobj" varStatus="imgindex">
							<div class="carousel-item ${imgindex.index==0?"active":""}">
								<img class="d-block w-100" src="/pic/${imgobj.picUrl}" alt="First slide">
							</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carousel" role="button"
							data-slide="prev"> <span class="carousel-control-prev-icon"
							aria-hidden="true"></span> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carousel" role="button"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
	</div>	

	<br/>

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	
	<script type="text/javascript" src="/resource/js/cms.js"></script>
</body>
</html>