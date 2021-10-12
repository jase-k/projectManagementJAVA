<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container w-50">
<div class="d-flex justify-content-between">
	<h1>Create a Project</h1>
	<div>
		<a href="/logout" class="btn btn-danger">Logout</a>
		<a href="/dashboard" class="btn btn-primary">Dashboard</a>
	</div>
</div>
<h2>Project Information:</h2>
<table class="table">
	<tbody>
		<tr>
			<th>Project Name: </th>
			<td><c:out value="${project.name }"></c:out></td>
		</tr>
		<tr>
			<th>Project Lead: </th>
			<td><c:out value="${project.teamLead.fName }"></c:out></td>
		</tr>
		<tr>
			<th>Project Description:</th>
			<td><c:out value="${project.description }" ></c:out> </td>
		</tr>
		<tr>
			<th>Project Due Date:</th>
			<td><c:out value="${project.dueDate}" ></c:out> </td>
		</tr>
	</tbody>
</table>
<h2>Project Team Members: </h2>
<ul>
	<c:forEach var="team_member" items="${project.team }">
		<li><c:out value="${team_member.fName} ${team_member.lName}" /></li>	
	</c:forEach>
</ul>
</div>
</body> 
</html>