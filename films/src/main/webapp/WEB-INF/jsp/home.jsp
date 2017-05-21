<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>
    <sec:authorize access="isAuthenticated()" var="isAuth"/>
    <c:if test="${isAuth}">
        <h1>Hi, ${userName}</h1>
        <form  action="/logout" method="post">
            <sec:csrfInput/>
            <button type="submit">Log out</button>
        </form>
    </c:if>

    <c:if test="${!isAuth}">
        <a href="/login/google">Login with Google</a>
        <a href="/login/github">Login with GitHub</a>
    </c:if>

</body>

</html>