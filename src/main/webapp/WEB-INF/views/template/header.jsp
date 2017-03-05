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
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Title</title>
    </head>
    <body>
       				 <!-- Header -->
        			<header class="row">

        				<div class="col-lg-12 col-md-12 col-sm-12">

        					<!-- Top Header -->
        					<div id="top-header">

        						<div class="row">

        							<nav id="top-navigation" class="col-lg-7 col-md-7 col-sm-7">

        								<ul class="pull-left">

											<sec:authorize access="isAuthenticated()">

        									 	<li><a href="account" methods="GET"><spring:message code="account"/></a></li>

        									</sec:authorize>

												<li><a href="orders_list.html">List Order</a></li>

        										<li><a href="order_info.html">Checkout</a></li>

											<sec:authorize access="isAuthenticated()">

												<li><a href="additem" methods="GET"><spring:message code="Add item"/></a></li>

											</sec:authorize>

        										<li><a href="text_page.html">About Us</a></li>

        										<li><a href="contact.html">Contact</a></li>

        								</ul>
        							</nav>

        							<nav class="col-lg-5 col-md-5 col-sm-5">

        								<ul class="pull-right">

											<sec:authorize access="!isAuthenticated()">

        										<li class="purple">

													<a href="login">

														<i class="icons icon-user-3"></i><spring:message code="login"/></a>

        									    </li>

        										<li><a href="register">


													<i class="icons icon-lock"></i> Create an Account</a></li>

											</sec:authorize>

											<sec:authorize access="isAuthenticated()">

												<li class="purple">

													    	<i class="icons icon-user-3" style="color: #122b40"> Hello <sec:authentication property="principal.username" /></i>

												<li><a href="logout">

															<i class="icons icon-lock"></i>Log out</a></li>

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

        									<a href="index"><img src="<c:url value="/resources/img/logo.png" />" alt="Logo"></a>

        							</div>

        							<nav id="middle-navigation" class="col-lg-8 col-md-8 col-sm-8">

        								<ul class="pull-right">

        									<li class="blue">

        										<a href="compare_products.html"><i class="icons icon-docs"></i>0 Items</a>

                                            </li>

        									<li class="red">

        										<a href="wishlist.html"><i class="icons icon-heart-empty"></i>2 Items</a>

                                            </li>

        									<li class="orange"><a href="order_info.html"><i class="icons icon-basket-2"></i>17 Items</a>

                                            	<ul id="cart-dropdown" class="box-dropdown parent-arrow">


        										</ul>

                                            </li>

        									<li>

												<a class="flag" href="#"><span class="english-flag"></span>English</a>

                                            	<ul class="box-dropdown parent-arrow">

        											<li>

                                                    	<div class="box-wrapper no-padding parent-border">

                                                            <table class="language-table">

                                                            	<tr>

                                                                	<td class="flag"><span class="english-flag"></span></td>

                                                                    <td class="country"><a href="?language=en">English</a></td>

                                                                </tr>

                                                                <tr>

                                                                	<td class="flag"><span class="spanish-flag"></span></td>

                                                                    <td class="country"><a href="?language=ru">Russian</a></td>

                                                                </tr>

                                                            </table>

                                                        </div>

        											</li>

        										</ul>

                                            </li>

        									<li>

												<a href="#"><i class="icons icon-dollar"></i>US Dollar</a>

                                            	<ul class="box-dropdown parent-arrow">

        											<li>

                                                    	<div class="box-wrapper no-padding parent-border">

                                                            <table class="currency-table">

                                                            	<tr>

                                                                	<td><a href="#">₴ UAH</a></td>

                                                                </tr>

        														<tr>

                                                                	<td><a href="#">$ US Dollar</a></td>

                                                                </tr>

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

        								<a>

        									<i class="icons icon-shop-1"></i>

        									<span class="nav-caption">Home</span>

        									<span class="nav-description">Variety of Layouts</span>

        								</a>

        								<ul class="normal-dropdown normalAnimation">

        								</ul>

        							</li>

        							<li class="red">

        								<a href="category_v1.html">

        									<i class="icons icon-camera-7"></i>

        									<span class="nav-caption">Cameras</span>

        									<span class="nav-description">Photo & Video</span>

        								</a>

        							</li>

        							<li class="blue">

        								<a href="category_v2.html">

        									<i class="icons icon-desktop-3"></i>

        									<span class="nav-caption">Computers</span>

        									<span class="nav-description">Laptops & Tablets</span>

        								</a>

        							</li>

        							<li class="orange">

        								<a href="category_v1.html">

        									<i class="icons icon-mobile-6"></i>

        									<span class="nav-caption">Cell phones</span>

        									<span class="nav-description">Phones & Accessories</span>

        								</a>
        							</li>

        							<li class="green">

        								<a href="blog.html">

        									<i class="icons icon-pencil-7"></i>

        									<span class="nav-caption">Blog</span>

        									<span class="nav-description">News & Reviews</span>

        								</a>

        							</li>

        							<li class="purple">

        								<a href="contact.html">

        									<i class="icons icon-location-7"></i>

        									<span class="nav-caption">Contact</span>

        									<span class="nav-description">Store Locations</span>

        								</a>

        							</li>

        							<li class="nav-search">

                                        	<i class="icons icon-search-1"></i>

        							</li>

        						</ul>

        						<div id="search-bar">

        							<div class="col-lg-12 col-md-12 col-sm-12">

                                    	<table id="search-bar-table">

                                            <tr>

                                            	<td class="search-column-1">

                                                    <p><span class="grey">Popular Searches:</span>

														<a href="#">accessories</a>,
														<a href="#">audio</a>,
														<a href="#">camera</a>,
														<a href="#">phone</a>,
														<a href="#">storage</a>,
														<a href="#">more</a></p>

                                                    <input type="text" placeholder="Enter your keyword">

                                                </td>

                                                <td class="search-column-2">

                                                	<p class="align-right"><a href="#">Advanced Search</a></p>

                                                	<select class="chosen-select-search">

                                                        <option>All Categories</option>
                                                        <option>All Categories</option>
                                                        <option>All Categories</option>
                                                        <option>All Categories</option>
                                                        <option>All Categories</option>

                                                    </select>

                                        		</td>

                                            </tr>

                                        </table>

        							</div>

        							<div id="search-button">

        								<input type="submit" value="">

        								<i class="icons icon-search-1"></i>

        							</div>

        						</div>

        					</nav>
        					<!-- /Main Navigation -->

        				</div>

        			</header>
        			<!-- /Header -->
    </body>
</html>
