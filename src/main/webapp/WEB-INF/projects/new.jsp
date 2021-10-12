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
<form:form action="/projects" method="post" modelAttribute="project" class="form-control">
	<p>
		<form:input type="hidden" path="teamLead" value="${user.id}" />
		<form:input type="hidden" path="team" value="${user.id}" />
	</p>
    <p>
        <form:label path="name">Project Name:</form:label>
        <form:errors path="name"/>
        <form:input path="name"/>
    </p>
    <p>
        <form:label path="description">Description:</form:label>
        <form:errors path="description"/>
        <form:textarea path="description"/>
    </p>
    <p>
        <form:label path="dueDate">Due Date</form:label>
        <form:errors path="dueDate"/>
        <form:input type="date" path="dueDate"/>
    </p>
    
    <input type="submit" value="Submit" class="btn btn-primary"/>
    <a href="/dashboard" class="btn btn-danger">Cancel</a>
</form:form>    

</div>
</body>
</html>