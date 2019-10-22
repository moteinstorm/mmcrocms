<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript">
$(function(){
	
	
	
	$(".form-control-sm").change(function(){
		
		$("#content-wrapper").load("/admin/manArticle?status="+$(this).val())
	})
	//下拉框回显
	$(".form-control-sm").val('${status}')
})


//查看文章详情
function toDetail(id){
	$("#content-wrapper").load("/admin/getArticle?id="+id)
	
}

</script>


</head>
<body>
<div class="container-fluid">

		<div>
		     文章状态
			  <select class="form-control-sm" >
			    <option value="-1">全部</option>
			    <option value="0">待审核</option>
			    <option value="1">已审核</option>
			    <option value="2">审核未通过</option>
			  </select>
		
		</div>

		<table class="table table-sm  table-hover table-bordered ">
			<thead class="thead-light">
				<tr align="center">
					<td>序号</td>
					<td>作者</td>
					<td>标题</td>
					<td>当前状态</td>
					<td>发布时间</td>
					<td>栏目</td>
					<td>分类</td>
					<td>操作</td>
				</tr>
			</thead>
			<c:forEach items="${pageInfo.list}" var="article" varStatus="index">
				<tr align="center">
					<td>${index.index+1 }</td>
					<td>${article.user.username}</td>
					<td>${article.title}</td>
					<td>${article.status==0?"待审核":article.status==1?"已审核":"未通过" }</td>
					<td><fmt:formatDate value="${article.created}" pattern="yyyy年MM月dd日  HH:mm:ss"/> </td>
					<td>${article.channel.name}</td>
					<td>${article.cat.name}</td>
					<td><button type="button" class="btn btn-info" onclick="toDetail(${article.id})">详情</button> </td>
				</tr>

			</c:forEach>

		</table>
		<div>
			${page}
		</div>
	</div>
</body>
</html>