<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		
		<c:forEach items="${comments.list }" var="comment">
				<div class="media-body">
					<h5 class="mt-0 mb-1"><small> ${comment.content}</small></h5>
					<br>
					<br>
					<h5 class="mt-0 mb-1"><small> ${comment.userName }  &nbsp;  <fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd"/> </small></h5>
				</div>
				</li>
				<hr>
		</c:forEach>    
