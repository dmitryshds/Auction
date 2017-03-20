<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 10.03.2017
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


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
                               <div class="muted pull-left">Users dataTable</div>
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
											<th>Edit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${users}" var="user">
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
											<td><a href="${contextPath}/admin/edit/user/${user.idUsers}">Edit</a></td>

										</tr>
										</c:forEach>
									</tbody>
								</table>
                               </div>
                           </div>
                       </div>
                       <!-- /block -->
                   </div>
</div>

</div>
</div>
