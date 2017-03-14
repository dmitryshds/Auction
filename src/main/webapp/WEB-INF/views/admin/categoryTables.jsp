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

<script>
    function hello(e) {

        console.log("E = "+e);
       var next = e.parentNode;
       next =  next.style.display = 'none';
        console.log("NEXT = "+next);
    }
</script>

			<div class="row-fluid row-width" >
                       <!-- block -->
                       <div class="block">
                           <div class="navbar navbar-inner block-header">
                               <div class="muted pull-left">Categories dataTable</div>
                           </div>
                           <div class="block-content collapse in">
                               <div class="span12">

 									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="category-table">
									<thead>
										<tr>
											<th>ID</th>
											<th>Category</th>
                                            <td>Action: </td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${category}" var="cat">
										<tr class="odd gradeX">
											<td>${cat.idCategory}</td>
											<td>${cat.type}</td>
											<%--<td><a href="javascript:hello(this)" class="edit" >Edit</a></td>--%>
                                            <td >
                                               <form action="${contextPath}/admin/edit/category" method="post">

                                                   <input type="text" name="editCategory" value="${cat.type}"  />
                                                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                   <input type="hidden" name="idCategory" value="${cat.idCategory}" />
                                                   <input class="big" type="submit" value="Edit category" style="background: #45b0e3" />

                                               </form>

                                            </td>
										</tr>
										</c:forEach>
                                        <tr>
                                            <form action="${contextPath}/admin/newCategory" method="post">
                                                 <td></td>
                                                <td></td>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                <td>
                                                    <input type="text" name="newCategory" />
                                                    <input class="big" type="submit" value="ADD NEW CATEGORY" style="background: greenyellow" />
                                                </td>
                                            </form>

                                        </tr>
									</tbody>
								</table>
                               </div>
                           </div>
                       </div>
                       <!-- /block -->
                   </div>
<%--<spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>--%>
     <%--<script src="${jqueryJs}">--%>
        <%--$('.edit').click(function () {--%>
           <%--console.log("CLICK");--%>
        <%--});--%>
        <%--$("#category-table").on("click", "td", function() {--%>
            <%--alert("CLICK");--%>
          <%--});--%>
        <%--function hello() {--%>
            <%--alert('hi');--%>
        <%--}--%>
     <%--</script>--%>