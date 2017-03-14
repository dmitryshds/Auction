<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 09.03.2017
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html class="no-js">

    <head>
        <title>Admin Home Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/adm_styles.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/bootstrap.min.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/admin/bootstrap-responsive.min.css"/>"/>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>


    </head>

    <body>
        <div class="navbar navbar-fixed-top" >
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">Admin Panel</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <i>Hello <sec:authentication property="principal.username" />
                                <a href="${contextPath}/logout" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> Log out<i class="caret"></i> </i>
                                <%--<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> Admin account<i class="caret"></i>--%>

                                </a>

                            </li>
                        </ul>
                        <ul class="nav">

                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="container-fluid" >
            <div class="row-fluid">
                <div class="span2" id="sidebar" >
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li class="active">
                          <a href="/index"><i class="icon-chevron-right"></i> Main Auction</a>
                        </li>

                        <li>
                            <a href="/admin"><i class="icon-chevron-right"></i> Forms</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon-chevron-right"></i> Categories</a>
                        </li>
                        <li>
                            <a href="#"><i class="icon-chevron-right"></i> User avatar</a>
                        </li>

                        <li>
                            <a href="#"><span class="badge badge-success pull-right">731</span> Orders</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-success pull-right">812</span> Invoices</a>
                        </li>

                        <li>
                            <a href="#"><span class="badge badge-info pull-right">1,234</span> Users</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">2,221</span> Messages</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">11</span> Reports</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-important pull-right">83</span> Errors</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-warning pull-right">4,231</span> Logs</a>
                        </li>
                    </ul>
                </div>

                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">

                    	</div>
                    <div class="row-fluid">
                        <!-- block -->

                    </div>

                    <c:import url="userTables.jsp"/>
                    <c:import url="categoryTables.jsp"/>
                    <c:import url="userAvatar.jsp"/>


                    </div>

                </div>
            </div>
            <hr>

            <footer>
                <p>&copy; Auction 2017</p>
            </footer>
        </div>
        <script src="<c:url value="/resources/js/adm/jquery-1.9.1.min.js"/>"></script>

        <script src="<c:url value="/resources/js/adm/jquery.dataTables.min.js"/>"></script>

        <script src="<c:url value="/resources/js/adm/DT_bootstrap.js"/>"></script>

    </body>

</html>