<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 27.01.2017
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>
<spring:url value="/resources/js/jquery-ui.js" var="jqueryUI"/>
<c:url var="home" value="/" scope="request" />
<script src="${jqueryJs}"></script>
<script src="${jqueryUI}"> </script>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

       				 <!-- Header -->
        			<header class="row">

        				<div class="col-lg-12 col-md-12 col-sm-12">

        					<!-- Top Header -->
        					<div id="top-header">

        						<div class="row">

        							<nav id="top-navigation" class="col-lg-7 col-md-7 col-sm-7">

        								<ul class="pull-left">

											<sec:authorize access="isAuthenticated()">

        									 	<li><a href="${contextPath}/account" methods="GET"><spring:message code="account"/></a></li>



												<li><a href="#"><spring:message code="List Order"/></a></li>

        										<li><a href="${contextPath}/bids"><spring:message code="Your bids"/></a></li>


												<li><a href="${contextPath}/additem" methods="GET"><spring:message code="Add item"/></a></li>

											</sec:authorize>

        										<li><a href="#"><spring:message code="About Us"/></a></li>

        										<li><a href="#"><spring:message code="Contact"/></a></li>

											<sec:authorize access="hasRole('ADMIN')">

												<li><a href="${contextPath}/admin"><spring:message code="Admin page"/></a></li>

											</sec:authorize>

        								</ul>
        							</nav>

        							<nav class="col-lg-5 col-md-5 col-sm-5">

        								<ul class="pull-right">

											<sec:authorize access="!isAuthenticated()">

        										<li class="purple">

													<a href="${contextPath}/login">

														<i class="icons icon-user-3"></i><spring:message code="login"/></a>

        									    </li>

        										<li><a href="${contextPath}/register">


													<i class="icons icon-lock"></i><spring:message code="Create an Account"/> </a></li>

											</sec:authorize>

											<sec:authorize access="isAuthenticated()">

												<li class="purple">

													    	<i class="icons icon-user-3" style="color: #122b40"><spring:message code="Hello"/> <sec:authentication property="principal.username" /></i>

												<li><a href="${contextPath}/logout">

															<i class="icons icon-lock"></i><spring:message code="Log out"/></a></li>

											</sec:authorize>

        								</ul>

        							</nav>

        						</div>

        					</div>
        					<!-- /Top Header -->

        					<!-- Main Header -->
        					<div id="main-header">

        						<div class="row">

        							<div id="logo" class="col-lg-4 col-md-4 col-sm-4">

        									<a href="${contextPath}/index"><img src="<c:url value="/resources/img/logo.png" />" alt="Logo"></a>

        							</div>

        							<nav id="middle-navigation" class="col-lg-8 col-md-8 col-sm-8">

        								<ul class="pull-right ">

        									<li class="green">

												<a class="flag" href="#"><span class="english-flag"></span><spring:message code="English"/></a>

                                            	<ul class="box-dropdown parent-arrow">

        											<li>

                                                    	<div class="box-wrapper no-padding parent-border">

                                                            <table class="language-table">

                                                            	<tr>

                                                                	<td class="flag"><span class="english-flag"></span></td>

                                                                    <td class="country"><a href="?language=en"><spring:message code="English"/></a></td>

                                                                </tr>

                                                                <tr>

                                                                	<td class="flag"><span class="spanish-flag"></span></td>

                                                                    <td class="country"><a href="?language=ru"><spring:message code="Russian"/></a></td>

                                                                </tr>

                                                            </table>

                                                        </div>

        											</li>

        										</ul>

                                            </li>

        									<li>

												<%--<a href="#"><i class="icons icon-dollar"></i>US Dollar</a>--%>

                                            	<ul class="box-dropdown parent-arrow">

        											<li>

                                                    	<div class="box-wrapper no-padding parent-border">

                                                            <table class="currency-table">

                                                            	<%--<tr>--%>

                                                                	<%--<td><a href="#">₴ UAH</a></td>--%>

                                                                <%--</tr>--%>

        														<%--<tr>--%>

                                                                	<%--<td><a href="#">$ US Dollar</a></td>--%>

                                                                <%--</tr>--%>

                                                            </table>

                                                        </div>

        											</li>

        										</ul>

                                            </li>

        								</ul>

        							</nav>

        						</div>

        					</div>
        					<!-- /Main Header -->


        					<!-- Main Navigation -->
        					<nav id="main-navigation" class="style1">

        						<ul>

        							<li class="home-green current-item">

        								<a href="${contextPath}/index">

        									<i class="icons icon-shop-1"></i>

        									<span class="nav-caption"><spring:message code="Home"/></span>

        									<span class="nav-description"></span>

        								</a>

        								<ul class="normal-dropdown normalAnimation">

        								</ul>

        							</li>

        							<%--<li class="red">--%>
        							<li class="red">
        								<a href="${contextPath}/account">

        									<i class="icons icon-camera-7"></i>

        									<span class="nav-caption"><spring:message code="account"/></span>

        									<span class="nav-description"></span>

        								</a>

        							</li>

        							<li class="blue">

        								<a href="${contextPath}/additem">

        									<i class="icons icon-desktop-3"></i>

        									<span class="nav-caption"><spring:message code="Add item"/></span>

        									<span class="nav-description"></span>

        								</a>

        							</li>

        							<li class="orange">

        								<a href="${contextPath}/bids">

        									<i class="icons icon-mobile-6"></i>

        									<span class="nav-caption"><spring:message code="Your bids"/></span>

        									<span class="nav-description"></span>

        								</a>
        							</li>

        							<li class="green">

        								<a href="#">

        									<i class="icons icon-pencil-7"></i>

        									<span class="nav-caption"><spring:message code="About us"/></span>

        									<span class="nav-description"></span>

        								</a>

        							</li>

        							<li class="purple">

        								<a href="#">

        									<i class="icons icon-location-7"></i>

        									<span class="nav-caption"><spring:message code="Contact"/></span>

        									<span class="nav-description"></span>

        								</a>

        							</li>

        							<li class="nav-search">

                                        	<i class="icons icon-search-1"></i>

        							</li>

        						</ul>

        						<div id="search-bar" >

        							<div class="col-lg-12 col-md-12 col-sm-12">

                                    	<table id="search-bar-table">

                                            <tr>

                                            	<td class="search-column-1">

                                                    <p><span class="grey">Search:</span>

														<%--<a href="#">accessories</a>,--%>
														<%--<a href="#">audio</a>,--%>
														<%--<a href="#">camera</a>,--%>
														<%--<a href="#">phone</a>,--%>
														<%--<a href="#">storage</a>,--%>
														<%--<a href="#">more</a></p>--%>

                                                    <input type="text" placeholder="Enter your keyword" id="search">

                                                </td>

											</tr>

                                        </table>

        							</div>

        							<div id="search-button">

        								<input type="submit" value="" id="search-submit">

        								<i class="icons icon-search-1"></i>

        							</div>

        						</div>

        					</nav>
        					<!-- /Main Navigation -->

        				</div>

        			</header>
        			<!-- /Header -->

        <script>


			$('#search-button').click(function () {
				var text = $('#search').val();
				console.log(""+text);
				start = 0;
				quantity = 10;
				searchViaAjax(text,start,quantity);

			})
			function searchViaAjax(text, start, quantity) {
	                if (start === undefined) {
	                    start = 0;
	                }
	                if (quantity === undefined) {
	                    quantity = 10;
	                }
	                console.log("searchViaAjax  text = " + text + " count = " + count + " start = " + start + " quantity = " + quantity);

	                $.ajax({
	                    type: "POST",
	                    url: "${home}search/"  + start + "/" + quantity + "/" + text ,
	                    timeout: 100000,
	                    headers: {
	                        'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
	                    },
	                    success: function (data) {
	                        console.log("SUCCESS: ", data);
	                        count = parseInt(data.count);
	                        start = parseInt(data.start);
	                        quantity = parseInt(data.quantity);
//	                        id = parseInt(id);
	                        display(data.itemList);
	                        console.log("RECIVE renderViaAjax  id = " + ""+ " count = " + count + " start = " + start + " quantity = " + quantity);

	                    },
	                    error: function (e) {
	                        console.log("ERROR: ", e);
//	                        display(e);
	                    },
	                    done: function (e) {
	                        console.log("DONE");

	                    }
	                });

	            }

		</script>