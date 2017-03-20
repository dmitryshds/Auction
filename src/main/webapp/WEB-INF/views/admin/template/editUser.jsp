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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!--/span-->
<div class="span9"
     id="content">
    <div class="row-fluid">

    </div>
    <div class="row-fluid">
        <!-- block -->

    </div>


    <div class="row-fluid row-width">
        <!-- block -->
        <div class="block">
            <div class="navbar navbar-inner block-header">
                <div class="muted pull-left">Edit user :</div>
            </div>
            <div class="block-content collapse in">
                <div class="span12">
                    <table cellpadding="0"
                           cellspacing="0"
                           border="0"
                           class="table table-striped table-bordered"
                           id="user-table">
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
                            <form method="post"
                                  action="/admin/editUser">
                                <tr>
                                    <td><input type="hidden"
                                               name="idUsers"
                                               value="${user.idUsers}"/></td>
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
                                    <td><select id="titles"
                                                name="titles"
                                                style="color: crimson">
                                        <option>ACTIVE</option>
                                        <option>INACTIVE</option>
                                        <option>LOCKED</option>
                                        <option>DELETED</option>
                                    </select>
                                        <input class="big"
                                               type="submit"
                                               value="Save changes"
                                               style="background: #45b0e3"/>
                                    </td>
                                    <%--<td>  </td>--%>
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>

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

<div class="row-fluid row-width">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">Users items :</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <table cellpadding="0"
                       cellspacing="0"
                       border="0"
                       class="table table-striped table-bordered"
                       id="item-table">
                    <thead>
                        <tr style="font-size: small;">
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
                            <th>State</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item"
                                   items="${user.itemList}">
                            <tr class="odd gradeX"
                                style="font-size: small;">
                                <td>${item.idItems}</td>
                                <td>${item.category.type}</td>
                                <td>${item.name}</td>
                                <td>${item.description}</td>
                                <td>${item.initialPrice}</td>
                                <td>${item.buynowPrice}</td>
                                <td><fmt:formatDate type="both"
                                                    value="${item.dateStart}"/></td>
                                <td><fmt:formatDate type="both"
                                                    value="${item.dateFinish}"/></td>
                                <c:forEach var="pic"
                                           items="${item.pictures}">
                                    <td><img src="/image${pic}"
                                             style="width: 50px; height: 50px;"/></td>
                                </c:forEach>
                                <c:set var="len"
                                       value="${fn:length(item.pictures)}"/>
                                <c:if test="${len < 4}">
                                    <c:forEach begin="${4-len}"
                                               end="3"
                                               step="1">
                                        <td></td>
                                    </c:forEach>
                                </c:if>
                                <td>${item.state}</td>
                                <td>
                                    <form method="post"
                                          action="/admin/editItem">
                                        <select name="itemTitles"
                                                style="color: crimson">
                                            <option>ACTIVE</option>
                                            <option>INACTIVE</option>
                                            <option>LOCKED</option>
                                            <option>DELETED</option>
                                        </select>
                                        <input class="big"
                                               type="submit"
                                               value="Save changes"
                                               style="background: #45b0e3"/>
                                        <input type="hidden"
                                               name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
                                        <input type="hidden"
                                               name="idItems"
                                               value="${item.idItems}"/>
                                    </form>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



