<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ideas</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <a href="/login?logout" class="pull-right">Logout</a>
        <h1>Welcome, ${user.name}</h1>
        <div>
            <h3 class="col-md-9">Ideas</h3>
            <a href="/ideas/low" class="col-md-1">Low Likes</a>
            <a href="/ideas/high" class="col-md-2">High Likes</a>
        </div>
        <table class="table table-striped table-ruled">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Created By</th>
                    <th>Likes</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ideas}" var="idea">
                <tr>
                    <td><a href="/ideas/${idea.id}">${idea.content}</a></td>
                    <td>${idea.thinker.name}</td>
                    <td>${idea.ideaLikers.size()}</td>
                    <c:set var = "liked" value= "${false}"/>
                    <c:forEach items="${idea.getIdeaLikers()}" var="liker">
                        <c:if test="${liker == user}">
                            <c:set var = "liked" value= "${true}"/>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${liked == false}">
                            <td><a href="/ideas/${idea.id}/like">Like</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/ideas/${idea.id}/unlike">Unlike</a></td>
                        </c:otherwise> 
                    </c:choose> 
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/ideas/new"><button class="btn">Create an Idea</button></a>
    </div>
</body>
</html>