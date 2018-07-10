<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login/Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="col-md-4">
            <h1>Register!</h1>
            <form:form method="POST" action="/register" modelAttribute="user" class="form-group">
                <p><form:label path="name">Name: </form:label><form:input path="name" class="form-control"/></p>
                <p><form:label path="email">Email: </form:label><form:input path="email" class="form-control"/></p>
                <p><form:label path="password">Password: </form:label><form:password path="password" class="form-control"/></p>
                <p><form:label path="confirm">Confirm Password: </form:label><form:password path="confirm" class="form-control"/></p>
                <input type="submit" value="Register" class="form-control btn btn-primary"/>
            </form:form>
            <br>
            <p><form:errors path="user.*"/></p>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-4">
            <h1>Login</h1>
            <form method="POST" action="/login" class="form-group">
                <p>Email: <input type="text" id="email" name="username" class="form-control"/></p>
                <p>Password: <input type="password" id="password" name="password" class="form-control"/></p>
                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Login" class="form-control btn btn-primary"/>
            </form>
            <br>
            <c:if test="${logError != null}">
                <p>${logError}</p>
            </c:if>
            <c:if test="${logout != null}">
                <p><c:out value="${logout}"></c:out></p>
            </c:if>
        </div>
    </div>
</body>
</html>