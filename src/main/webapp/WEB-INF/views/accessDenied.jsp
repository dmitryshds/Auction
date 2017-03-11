<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>AccessDenied page</title>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
</head>
<body>
	<div class="generic-container">
		<div style="margin-bottom:15px;">
			<span>Dear <strong><sec:authentication property="principal.username" /></strong>, You are not authorized to access this page.</span> <span class="floatRight"><a href="${contextPath}/logout">Logout</a></span>
		</div>
	</div>
</body>
</html>