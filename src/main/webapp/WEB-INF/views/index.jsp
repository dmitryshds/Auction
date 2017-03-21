<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 22.01.2017
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Index</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/fontello.css" />"/>
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css" />"/>
		<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,700,900,700italic,500italic' rel='stylesheet' type='text/css'>

    </head>
    <body>
		<div class="container" >
		   <div>
			   <c:import url="template/header.jsp"/>
		   </div>

			<div >
				<c:import url="template/content.jsp"/>
		   </div>

			<div >
				<c:import url="template/footer.jsp"/>
		   </div>


		</div>


    </body>
</html>
