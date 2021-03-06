<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>	
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getAttribute("content1") != null ? (String)request.getAttribute("content1") : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>
<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		function query(){
		alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
	</script>
</head>
<body>
	<form action="" id="form">
		<input type="hidden" value="${article.id}" name="id">
		<div class="form-group row ">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" value="${article.title}" name="title" placeholder="请输入标题">
		</div>




		<div class="form-group row ">
			<textarea name="content1" cols="100" rows="8"
				style="width: 860px; height: 250px; visibility: hidden;" ><%=htmlspecialchars(htmlData)%></textarea>
			<br />
		</div>
		<div class="form-group row ">
			<label for="title">文章标题图片</label> <input type="file"
				class="form-control" id="file" name="file">
		</div>
		
		<div class="form-group row ">
		  	<label for="channel">文章栏目</label>
			<select class="custom-select custom-select-sm mb-3" id="channel"  name="channelId">
				<option value="0">请选择</option>  
				<c:forEach items="${channels}" var="channel">
					<option value="${channel.id}" ${channel.id==article.channelId?"selected":""} >   ${channel.name}</option> 
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group row ">
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			</select>
		</div>
		<div class="form-group row ">	
			<label for="category">文章标签</label> 
				<input name="tags" size="50" value="${article.tags}"/>
		</div>		
		
		
		<div class="form-group row" >
			<button type="button" class="btn btn-success" onclick="publish()">修改</button>
		
		</div>
	</form>












</body>




<script type="text/javascript">
//发布文章
function publish(){
	
	
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
			url:"/article/update",
			success:function(obj){
				if(obj)
			    {
					alert("修改成功!")
					// location="/article/listMyArticle";
					$("#center").load("/user/myarticlelist")
				}else{
					alert("修改失败")
				}
				
			}
		})
	}
	
/* 	
	$.post("/article/publish",$("form").serialize()+"&content="+editor1.html(),function(obj){
		if(obj)
		alert("发布成功");
		else
		alert("发布失败")
	}) 
	
}
		*/

/**
 *  预加载函数
 */
$(function(){
	//根据频道获取分类
	changeChannel();
	
	//为栏目添加绑定事件   触发联动
	 $("#channel").change(function(){
		 changeChannel();
	}) // end of change
})//end $(function



	//自动加载文章的栏目
 	/* $.ajax({
		type:"get",
		url:"/article/getAllChn",
		success:function(list){
			$("#channel").empty();
			for(var i in list){
				if(${article.channelId}==list[i].id){
					$("#channel").append("<option selected value='"+list[i].id+"'>"+list[i].name+"</option>")
					
					// 频道的回显
					 $("#category").empty();
						//根据ID 获取栏目下的分类
					 $.get("/article/getCatsByChn",{channelId:${article.channelId}},function(catlist){
						
						 for(var cati in catlist){
						  	 if(catlist[cati].id==${article.categoryId}){
								 $("#category").append("<option selected value='"+catlist[cati].id+"'>"+catlist[cati].name+"</option>")
						 	 }else{
						 		$("#category").append("<option value='"+catlist[cati].id+"'>"+catlist[cati].name+"</option>")
						 	 }
							 //处理回显
							
						 }
						 
					 })
					
					
				}else{
					$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
				}
				
			}
		}
		
	}) */
 	
	
	
	
//})

	/**
	*  函数用于根据频道内容获取分类列表内容
	*
	*/
	function changeChannel(){
			
			 //先清空原有的栏目下的分类
			 $("#category").empty();
			var cid =$("#channel").val();//获取当前的下拉框的id
			//根据ID 获取栏目下的分类
		 	$.get("/article/listCatByChnl",{chnlId:cid},function(resultData){
			if(resultData.result==1){ //后端处理正确
				var list = resultData.data; //得到分类列表的数据
				//遍历分类列表数据
				 for(var i in list){
					 //该分类就是文章的分类
					if(list[i].id==${article.categoryId}){
						// 该项处于选中状态
				  		$("#category").append("<option value='"+list[i].id 
				  				      +"' selected >"+list[i].name+"</option>")
					}else{
						//
						$("#category").append("<option value='"+list[i].id+"'>"
								        +list[i].name+"</option>")
					}//end if
				 }//end for
			}//end if 
		 })// end $.get(
}//end function




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