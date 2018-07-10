<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Idea</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
    <div class="container">
        <a href="/ideas">Back</a>
        <h1>Edit ${idea.content}</h1>
        <p><form:errors path="empty.*"/></p>
        <div class="col-md-4">
            <form:form method="POST" action="/ideas/${idea.id}/edit" class="form-group" modelAttribute="empty">
                <form:label path="content">Content: 
                    <form:input path="content" class="form-control" value="${idea.content}"/>
                </form:label>
                <input type="submit" value="Edit" class="form-control btn btn-info"/>
            </form:form> 
            <br>  
            <a href="/ideas/${idea.id}/delete"><button class="btn">Delete</button></a>
        </div> 
    </div>
</body>
</html>