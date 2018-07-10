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
    <title>${event.name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
    <div class="container">
        <a href="/ideas">Back</a>
        <h1>${idea.content}</h1>
        <h4>Created By: ${idea.thinker.name}</h4>
        <h2>Users who liked this idea</h2>
        <table class="table table-ruled table-striped">
            <tr>
                <th>Name</th>
            </tr>
            <c:forEach items="${idea.ideaLikers}" var="liker">
                <tr>
                    <td>${liker.name}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:if test="${idea.getThinker() == user}">
            <a href="/ideas/${idea.id}/edit"><button class="btn">Edit</button></a>
        </c:if>
    </div>
</body>
</html>