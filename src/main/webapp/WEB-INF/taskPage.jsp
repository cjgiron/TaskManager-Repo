<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${task.content}</title>
</head>
<body>
	<div style="padding-left: 100px; padding-top: 100px;">
		<h1>Task: <c:out value="${task.content}"></c:out></h1>
		
		<p>Creator: <c:out value="${task.creator.userName}"></c:out></p>
		
		<p>Assignee: <c:out value="${task.assignee.userName}"></c:out></p>
		
		<p>Priority: <c:out value="${task.priority}"></c:out></p>
		
		<a href="/tasks/${task.id}/edit">Edit</a>
	</div>
</body>
</html>