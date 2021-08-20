<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Task</title>
</head>
<body>
	<div style="padding-left: 100px; padding-top: 100px;">
	<h1>Edit <c:out value="${task.content}"></c:out></h1>
		<form:form action="/tasks/${task.id}" method="post" modelAttribute="task">
			<input type="hidden" name="_method" value="put">
			<form:input type="hidden" path="creator" value="${user.id}"/>
			<p>
		        <form:label path="content">Task:</form:label>
		        <form:errors path="content"/>
		        <form:input path="content"/>
		    </p>
			<p>
		        <form:label path="assignee">Assignee:</form:label>
		        <form:errors path="assignee"/>
		        <form:select path="assignee">
		        	<c:forEach items="${users}" var="user">
		        		<form:option value="${user}"><c:out value="${user.userName}"></c:out></form:option>
		        	</c:forEach>
		        </form:select>
		    </p>
		    
		    <p>
		        <form:label path="priority">Priority:</form:label>
		        <form:errors path="priority"/>
		        <form:select path="priority">
		        	<form:option value="High">High</form:option>
		        	<form:option value="Medium">Medium</form:option>
		        	<form:option value="Low">Low</form:option>
		        </form:select>
		    </p>
		    
		    <input type="submit" value="Update"/>
		    <a href="/tasks">Back to HomePage</a>
		</form:form>
	</div>
</body>
</html>