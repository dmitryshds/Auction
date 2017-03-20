<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 20.03.2017
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>

<div class="navbar navbar-fixed-top">
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
                        <i>Hello <sec:authentication property="principal.username"/>
                            <a href="${contextPath}/logout" role="button" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="icon-user"></i> Log out<i class="caret"></i> </i>

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
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li class="active">
                    <a href="/index"><i class="icon-chevron-right"></i> Main Auction</a>
                </li>

                <li>
                    <a href="/admin"><i class="icon-chevron-right"></i> User Tables</a>
                </li>
                <li>
                    <a href="/admin/category"><i class="icon-chevron-right"></i> Category Tables</a>
                </li>
                <li>
                    <a href="/admin/avat"><i class="icon-chevron-right"></i> User avatar</a>
                </li>
                <li>
                    <a href="/admin/messages"><i class="icon-chevron-right"></i>Send Message</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-success pull-right"></span> Orders</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-success pull-right"></span> Invoices</a>
                </li>

                <li>
                    <a href="#"><span class="badge badge-info pull-right"></span> Users</a>
                </li>

                <li>
                    <a href="#"><span class="badge badge-info pull-right"></span> Reports</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-important pull-right"></span> Errors</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-warning pull-right"></span> Logs</a>
                </li>
            </ul>
        </div>

