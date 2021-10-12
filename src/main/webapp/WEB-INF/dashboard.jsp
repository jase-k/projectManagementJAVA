<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<div>
		<h1>Welcome ${user.fName }</h1>
	</div>
	<div>
		<a href="/logout" class="btn btn-danger">Logout</a>
		<a href="/project/new" class="btn btn-primary">+ New Project</a>
	</div>
</div>
<p>All Projects</p>
<table class="table">
	<thead>
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="project" items="${allProjects}" >
		<c:if test="${!project.team.contains(user)}">
			<tr>
				<td><a href="projects/${project.id}"><c:out value="${project.name }"></c:out></a></td>
				<td><c:out value="${project.teamLead.fName} ${project.teamLead.lName} "></c:out></td>
				<td><c:out value="${project.dueDate }"></c:out></td>
				<td><a href="/projects/${project.id }/join">Join Team</a></td>
			</tr>
		</c:if>
		</c:forEach>
	</tbody>
</table>

<p>Your Projects</p>
<table class="table">
	<thead>
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="project" items="${allProjects}" >
		<c:if test="${project.team.contains(user)}">
		<tr>
			<td><a href="projects/${project.id}"><c:out value="${project.name }"></c:out></a></td>
			<td><c:out value="${project.teamLead.fName} ${project.teamLead.lName} "></c:out></td>
			<td><c:out value="${project.dueDate }"></c:out></td>
			<td>
			<c:if test="${user != project.teamLead}">
				<a href="projects/${project.id}/leave">Leave Team</a>
			</c:if> 
			<c:if test="${user == project.teamLead}">
				<a href="projects/${project.id }/edit">Edit</a>
			</c:if>
			</td>
		</tr>
		</c:if>
		</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>