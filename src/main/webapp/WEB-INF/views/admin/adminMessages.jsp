<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 09.03.2017
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<html class="no-js">

    <head>
        <title>Admin Home Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/adm_styles.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/bootstrap.min.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/bootstrap-responsive.min.css"/>"/>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>


    </head>

    <body>

        <c:import url="template/header.jsp"/>

        <c:import url="template/userMessages.jsp"/>

        <c:import url="template/footer.jsp"/>

        <script src="<c:url value="/resources/js/adm/jquery-1.9.1.min.js"/>"></script>

        <script src="<c:url value="/resources/js/adm/jquery.dataTables.min.js"/>"></script>

        <script src="<c:url value="/resources/js/adm/DT_bootstrap.js"/>"></script>

    </body>

</html>