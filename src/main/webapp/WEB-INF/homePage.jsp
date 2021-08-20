<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	crossorigin="anonymous">

</head>
<body>
	<div style="display: flex; justify-content: space-between">
		<h1>Welcome <c:out value="${user.userName}"></c:out></h1>
		<a href="/logout" style="padding-right: 100px;">Logout</a>
	</div>
	
	<div style="padding-left: 100px; padding-right: 100px;">
	<table class="table" style= "outline: solid black 1px;">
		<thead>
			<tr>
				<th>Task</th>
				<th>Creator</th>
				<th>Assignee</th>
				<th>Priority</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td><a href="tasks/${task.id}"><c:out value="${task.content}"></c:out></a></td>
					<td><c:out value="${task.creator.userName}"></c:out></td>
					<td><c:out value="${task.assignee.userName}"></c:out></td>
					<td><c:out value="${task.priority}"></c:out></td>
				</tr>
	        </c:forEach>
		</tbody>
	</table>
	</div>
	<div style="text-align: right; padding-right: 100px;">
		<a href="/tasks/new">Create Task</a>
	</div>
</body>
</html>