<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>
<script>
		 
	</script>
</head>
<body>
	<form action="" id="form">
		<div class="form-group row ">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>
		
		<div class="form-group row ">
		  	<label for="channel">文章栏目</label> 
			<select class="custom-select custom-select-sm mb-3" id="channel"  name="channelId">
			  <option value="0">请选择</option>
			  <c:forEach items="${channels}" var="channel">
			  		<option value="${channel.id}">${channel.name}</option>
			  </c:forEach>
			</select>
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			</select>
			
			<div class="form-group row ">
				<label for="title">文章标题图片</label> <input type="file"
				 class="form-control"  name="file">
			</div>
							
			
			<div id="imgs" class="container" >	
					<div class="form-group row ">
						<label for="title">文章图片</label> <input type="file"
							class="form-control"  name="imgs">
					    <label for="title">图片描述</label> <input type="text"
							class="form-control"  name="imgsdesc">
					</div>
			</div>
			<input type="button" onclick="addimg()" value="追加图片"/>	
			
		</div>
		
		<div class="form-group row" >
		<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		
		</div>
	</form>






</body>


<script type="text/javascript">

/**
 * 动态追加图片
 */
function addimg(){
	$("#imgs").append('<div id="imgs" class="container" style="border:2 solid red" >	<div class="form-group row "> <label for="title">文章图片</label> <input type="file" class="form-control"  name="imgs"> <label for="title">图片描述</label> <input type="text" class="form-control"  name="imgsdesc"></div></div>')
}


//发布文章
function publish(){
	//alert(editor1.html())
	
		//序列化表单数据带文件
		 var formData = new FormData($( "#form" )[0]);
		//改变formData的值
		//editor1.html() 是富文本的内容
		 formData.set("content",editor1.html());
		
		$.ajax({
			type:"post",
			data:formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url:"/article/addimg",
			success:function(obj){
				if(obj){
					alert("发布成功!")
					$('#center').load("/user/myarticlelist");
				}else{
					alert("发布失败")
				}
				
			}
			
		})
	
/* 	
	$.post("/article/publish",$("form").serialize()+"&content="+editor1.html(),function(obj){
		if(obj)
		alert("发布成功");
		else
		alert("发布失败")
	}) */
	
}


$(function(){

	//自动加载文章的栏目
	/* $.ajax({
		type:"get",
		url:"/article/getAllChn",
		success:function(list){
			$("#channel").empty();
			for(var i in list){
				$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
			}
		}
		
	}) */
	//为栏目添加绑定事件
	$("#channel").change(function(){
		 //先清空原有的栏目下的分类
		 $("#category").empty();
		var cid =$(this).val();//获取当前的下拉框的id
		//根据ID 获取栏目下的分类
	 	$.get("/article/listCatByChnl",{chnlId:cid},function(resultData){
		if(resultData.result==1){
			var list = resultData.data;
			 for(var i in list){
				  	$("#category").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")

			}	
		}
	 })
	})
})

</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
</html>