<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>HOME</h1>
<hr>
<hr>
<sec:authentication property="principal" var="user"/>
<sec:authorize access="isAuthenticated()">
Welcome, <c:out value="${user.username}"/>
<hr>
<div align="right"><a href="${pageContext.request.contextPath }/logout">LOGOUT</a></a></div><hr>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
<div align="right"><a href="${pageContext.request.contextPath }/sign-up">SIGN-UP</a></a></div><hr>
</sec:authorize>
<a href="${pageContext.request.contextPath}/show-report">Show Report</a><br><br>
<a href="${pageContext.request.contextPath}/show-users">Show User</a><br><br>
<hr>
<%-- <a href="${pageContext.request.contextPath}/sign-up">Sign Up</a><hr><br><br> --%>
<%-- <c:out value="${user.getUsername()}"/><br> --%>
<%-- <c:out value="${user.getPassword()}"></c:out> --%>
</body>
</html>