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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> Admin <i class="caret"></i>

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
                          <a href="index.html"><i class="icon-chevron-right"></i> Dashboard</a>
                        </li>

                        <li>
                            <a href="form.html"><i class="icon-chevron-right"></i> Forms</a>
                        </li>
                        <li>
                            <a href="tables.html"><i class="icon-chevron-right"></i> Tables</a>
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


                    <div class="row-fluid row-width" >
                       <!-- block -->
                       <div class="block">
                           <div class="navbar navbar-inner block-header">
                               <div class="muted pull-left">Edit user : </div>
                           </div>
                           <div class="block-content collapse in">
                               <div class="span12">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Login</th>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th>E-mail</th>
                                        <th>Country</th>
                                        <th>Zip</th>
                                        <th>City</th>
                                        <th>Street</th>
                                        <th>Home number</th>
                                        <th>Validate e-mail</th>
                                        <th>State</th>

                                    </tr>
                                </thead>
                                <tbody>

                                    <tr class="odd gradeX">
                                        <td>${user.idUsers}</td>
                                        <td>${user.login}</td>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.country}</td>
                                        <td>${user.zip}</td>
                                        <td>${user.city}</td>
                                        <td>${user.street}</td>
                                        <td>${user.homeNumber}</td>
                                        <td>${user.validateEmail}</td>
                                        <td>${user.state}</td>
                                        <%--<td></td>--%>

                                    </tr>
                                     <form   method="post" action="/admin/editUser" >
                                     <tr>
                                        <td><input  type="hidden" name="idUsers" value="${user.idUsers}"/></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td> <select id="titles" name="titles" style="color: crimson">
                                              <option>ACTIVE</option>
                                              <option>INACTIVE</option>
                                              <option>LOCKED</option>
                                              <option>DELETED</option>
                                              </select>
                                            <input class="big" type="submit" value="Save changes" style="background: #45b0e3" />
                                        </td>
                                         <%--<td>  </td>--%>
                                       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                     </tr>
                                     </form>


                                </tbody>
                            </table>

                           </div>
                               </div>
                           </div>
                           <!-- /block -->
                       </div>

                     </div>
                    </div>

            <div class="row-fluid row-width" >
              <!-- block -->
              <div class="block">
                  <div class="navbar navbar-inner block-header">
                      <div class="muted pull-left">Users items : </div>
                  </div>
                  <div class="block-content collapse in">
                      <div class="span12">
                   <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                       <thead>
                           <tr>
                               <th>ID</th>
                               <th>Category</th>
                               <th>Name</th>
                               <th>Description</th>
                               <th>Initial Price</th>
                               <th>By now price</th>
                               <th>Date start</th>
                               <th>Date finish</th>
                               <th>Pictures</th>
                               <th>Pictures</th>
                               <th>Pictures</th>
                               <th>Pictures</th>


                           </tr>
                       </thead>
                       <tbody>
                           <c:forEach var="item" items="${user.itemList}">
                           <tr class="odd gradeX">
                               <td>${item.idItems}</td>
                               <td>${item.category.type}</td>
                               <td>${item.name}</td>
                               <td>${item.description}</td>
                               <td>${item.initialPrice}</td>
                               <td>${item.buynowPrice}</td>
                               <td>${item.dateStart}</td>
                               <td>${item.dateFinish}</td>
                               <c:forEach var="pic" items="${item.pictures}">
                               <td>${pic}</td>
                               </c:forEach>
                               <%--&lt;%&ndash;<td></td>&ndash;%&gt;--%>

                           </tr>
                           </c:forEach>
                            <%--<form   method="post" action="/admin/editUser" >--%>
                            <%--<tr>--%>
                               <%--<td><input  type="hidden" name="idUsers" value="${user.idUsers}"/></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td></td>--%>
                               <%--<td> <select id="titles2" name="titles" style="color: crimson">--%>
                                     <%--<option>ACTIVE</option>--%>
                                     <%--<option>INACTIVE</option>--%>
                                     <%--<option>LOCKED</option>--%>
                                     <%--<option>DELETED</option>--%>
                                     <%--</select>--%>
                                   <%--<input class="big" type="submit" value="Save changes" style="background: #45b0e3" />--%>
                               <%--</td>--%>
                                <%--&lt;%&ndash;<td>  </td>&ndash;%&gt;--%>
                              <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>

                            <%--</tr>--%>
                            <%--</form>--%>


                       </tbody>
                   </table>



                </div>
            </div>
            <hr>

            <footer>
                <p>&copy; Auction 2017</p>
            </footer>
        </div>
        <%--<script src="<c:url value="/resources/js/adm/jquery-1.9.1.min.js"/>"></script>--%>

        <%--<script src="<c:url value="/resources/js/adm/jquery.dataTables.min.js"/>"></script>--%>

        <%--<script src="<c:url value="/resources/js/adm/DT_bootstrap.js"/>"></script>--%>

    </body>

</html>