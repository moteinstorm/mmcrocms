<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">

function myopen(id){
	// alert(id)
	window.open("/article/show?id="+id,"_blank");
	
}

function toUpdate(id) {
	$('#center').load("/article/update?id="+id);
}

function del(id) {
	var result = confirm("您确定要删除文章么？");
	if(!result)
		return;
	
	$.post("/user/delArticle",{id:id},function(data){
		if(data){
			alert('删除成功')	
			$('#center').load("/user/myarticlelist");
			
		}else{
			alert('删除失败')
		}
	},"json"
	)
}

</script>
</head>
<body>

	<c:forEach items="${pageArticles.list}" var="article">
		<dl>
			<dt><a href="javascript:myopen(${article.id })">${article.title }</a></dt>
			<dd>作者:${article.user.username} 发布时间:
			  <fmt:formatDate value="${article.created}"/>
				频道:${article.channel.name}  分类:${article.cat.name}
			    <a href="javascript:toUpdate(${article.id })">修改</a>
			    <a href="javascript:del(${article.id })">删除</a>
			</dd>
		</dl>
		<hr>
	</c:forEach>
	${pageStr}


</body>
<script type="text/javascript">
	$(function(){
	    $('.page-link').click(function (e) {
	    	
	    	
	    	  //获取点击的的url
	        var url = $(this).attr('data');
	        // alert(url);
	    
	       //在中间区域显示地址的内容
	       $('#center').load(url);
	    });
		
	})
	
</script>
</html>