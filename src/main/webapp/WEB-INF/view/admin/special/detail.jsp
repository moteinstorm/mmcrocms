<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js">
<!--

//-->
</script>
<div class="container">
	<label>专题名称:</label>${special.title}
	<br/>
	<label>专题摘要:</label>${special.digest};
	<br/>
	<label>专题文章:</label>
	<br/>
	<table>
		<tr>
			<td>文章id</td>
			<td>文章标题</td>
			<td>发布时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${special.artilceList}" var="article">
			<tr>
				<td>${article.id}</td>
				<td>${article.title}</td>
				<td>${article.created}</td>
				<td><a href="javascript:remove(${special.id},${article.id})">移除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	
	<label>添加新的文章</label> <input name="articleId" id="articleId" />  
	   <input type="button" onclick="addArticle()" value="添加文章">
	
	<br/>
	
	<script type="text/javascript">
		
	   function addArticle(){
		   
		   $.post("/special/addArticle",{specId:${special.id},articleId:$("#articleId").val()},function(msg){
				if(msg.result==1){
					alert("处理成功")
					$("#content-wrapper").load("/special/detail?id="+${special.id});
				}else{
					alert(msg.errorMsg);
				}
			},"json")
	   }
	
		function remove(specialId,articleId){
			
			$.post("/special/removeArticle",{specId:specialId,articleId:articleId},function(msg){
				if(msg.result==1){
					alert("处理成功")
					$("#content-wrapper").load("/special/detail?id="+specialId);
				}else{
					alert(msg.errorMsg);
				}
			},"json")
			
		}
	</script>
			

</div>