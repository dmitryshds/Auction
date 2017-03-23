<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 05.02.2017
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Title</title>

        <c:url var="home" value="/" scope="request"/>

        <spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>

        <script src="${jqueryJs}"></script>

    </head>
    <body>
            <div class="row content">

                <div class="col-lg-12 col-md-12 col-sm-12">

                    <div class="breadcrumbs">

                        <p><a href="index">Home</a> <i class="icons icon-right-dir"></i> Your account details</p>

                    </div>

                </div>
            <!-- Main Content -->
                <section class="main-content col-lg-9 col-md-9 col-sm-9 alline-center" >

                    <div class="row">

                        <div class="col-lg-12 col-md-12 col-sm-12 register-account">

                            <div class="carousel-heading no-margin">

                                <h4>Your account details: </h4>

                            </div>

                                <div class="page-content">

                                    <div class="row">

                                        <div class="col-lg-12 col-md-12 col-sm-12">

                                             <p><strong>Your information</strong></p>

                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col-lg-4 col-md-4 col-sm-4">

                                            <img src="<c:url value="image${user.picture}"/>" style="width: 150px; height:150px;  ">

                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-8" >



                                        </div>

                                    </div>


                                     <div class="row">

                                        <div class="col-lg-4 col-md-4 col-sm-4">

                                             <p>Username(Login)* </br>

                                            <span style="color: red; display: none " id="err-login"> Incorrect value min 2 max 10 characters</span>

                                            <span style="color: green; display: none " id="corr-login"> Correct value</span>


                                             </p>

                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-8">

                                            <b>${user.login}</b>

                                        </div>

                                    </div>

                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                                <p>E-mail*</p>

                                            </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8">

                                                <b>${user.email}</b>
                                                <c:set var="validate" value="${user.validateEmail}"/>
                                                <c:choose>
                                                 <c:when test="${validate}">
                                                     <b style="color: #00a847">Confirmed</b>
                                                 </c:when>
                                                    <c:otherwise>
                                                        <b style="color: #ff0000;">Not Condirmed</b>
                                                        <form method="post" action="re-confirmation">
                                                            <input type="hidden" name="userId" value="${user.idUsers}">
                                                            <input  type="submit" value="Re-confirm" style="background-color: darkgrey;color: #1b6d85;" />
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                                        </form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>

                                        </div>

                                    <form:form method="post"  modelAttribute="user" action="changeuser">
                                           <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>First name</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                               <b>${user.firstName}</b>
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Last Name</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                          <b>${user.lastName}</b>
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>ZIP / Postal Code</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                          <b>${user.zip}</b>
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>City</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.city}</b>
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Country</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.country}</b>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>Street</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.street}</b>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Phone</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.homeNumber}</b>

                                                  </div>

                                          </div>

                                          <div class="row">

                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                            </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8">

                                            </div>

                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                            <div class="col-lg-12 col-md-12 col-sm-12">

                                                <input class="big" type="submit" value="Register" style="background-color: #DDD;color: #999;" id="register" disabled="disabled" />

                                                <input class="big" type="reset" value="Reset"  id="reset" />

                                            </div>

                            </div>

                        </div>
                             </form:form>
                    </div>

                </div>


                 </section>
            <!-- /Main Content -->
             </div>

        <script>

        </script>

    </body>

</html>