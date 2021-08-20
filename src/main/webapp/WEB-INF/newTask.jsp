<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="padding-left: 100px; padding-top: 100px;">
		<h1>Create a new task</h1>
		<form:form action="/tasks" method="post" modelAttribute="task">
		
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
		    
		    <input type="submit" value="Create"/>
		    <a href="/tasks">Back to HomePage</a>
		</form:form>
	</div>

</body>
</html>