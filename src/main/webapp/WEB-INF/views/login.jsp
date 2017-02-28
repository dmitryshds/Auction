<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 08.02.2017
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Index</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/fontello.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/form.css" />"/>

        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,700,900,700italic,500italic'
              rel='stylesheet' type='text/css'>
    </head>

    <body>

        <div class="container">

            <div>

                <div class="wrapper">

                    <c:url var="loginUrl" value="/login" />

                    <c:set var="login" value="${empty loginName ? '' : loginName}"/>

                         <form class="form-signin" name="myForm" id="myForm" method="post" action="${loginUrl}">

                             <h2 class="form-signin-heading">Please login</h2>

                                    <input type="text" class="form-control" value="${login}" name="login" placeholder="Login" required="" autofocus=""/>

                                    <input type="password" class="form-control" value="" name="password" placeholder="Password" required=""/>

                                    <input type="checkbox"  id="login-remember-2" name="remember-me"/>

                                          <label for="login-remember-2" class="checkbox">Remember me</label>

                                     <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

                                            <div style="margin-top: 40px;">

                                                 <a href="register" >Create an Account</a>

                                            </div>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                         </form>

                                 <c:if test="${not empty error}">

                                    	<div class="error" >${error}</div>

                                 </c:if>

                </div>

            </div>

        </div>

    </body>

</html>