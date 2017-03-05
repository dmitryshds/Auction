<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 04.03.2017
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Title</title>

        <c:url var="home" value="/" scope="request"/>

        <spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>
        <spring:url value="/resources/js/jquery-ui.js" var="jqueryUI"/>

        <script src="${jqueryJs}">
        </script>
        <script src="${jqueryUI}">

        </script>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<c:set var="owner" value="${item.owner}"/>

    </head>
    <body>
        <!-- Container -->
        		<div class="container">



        			<!-- Content -->
        			<div class="row content">


                        <div class="col-lg-12 col-md-12 col-sm-12">
                        	<div class="breadcrumbs">
                            	<p><a href="${contextPath}/index">Home</a> <i class="icons icon-right-dir"></i> <span><c:forEach var="category" items="${item.categoryList}">${category.type}</c:forEach> </span> <i class="icons icon-right-dir"></i> Item</p>
                            </div>
                        </div>


        				<!-- Main Content -->
        				<section class="main-content col-lg-12 col-md-12 col-sm-12">



                            <!-- Product -->
                            <div class="product-single">

                                <div class="row">

                                    <!-- Product Images Carousel -->
                                    <div class="col-lg-4 col-md-4 col-sm-4 product-single-image">

        								<div id="product-slider">
        									<ul class="slides">
        										<li>
        											<img class="cloud-zoom" src="img/products/single1.jpg" data-large="img/products/sample1.jpg"  alt=""/>
        											<a class="fullscreen-button" href="img/products/single1.jpg">
        												<div class="product-fullscreen">
        													<i class="icons icon-resize-full-1"></i>
        												</div>
        											</a>
        										</li>
        									</ul>
        								</div>
        								<div id="product-carousel">
        									<ul class="slides">
        										<li>
        											<a class="fancybox" rel="product-images" href="img/products/single1.jpg"></a>
        											<img src="img/products/single1.jpg" data-large="img/products/single1.jpg" alt=""/>
        										</li>
        										<li>
        											<a class="fancybox" rel="product-images" href="img/products/single2.jpg"></a>
        											<img src="img/products/single2.jpg" data-large="img/products/single2.jpg" alt="" />
        										</li>
        										<li>
        											<a class="fancybox" rel="product-images" href="img/products/single3.jpg"></a>
        											<img src="img/products/single3.jpg" data-large="img/products/single3.jpg" alt="" />
        										</li>
        										<li>
        											<a class="fancybox" rel="product-images" href="img/products/single4.jpg"></a>
        											<img src="img/products/single4.jpg" data-large="img/products/single4.jpg" alt="" />
        										</li>
        										<%--<li>--%>
        											<%--<a class="fancybox" rel="product-images" href="img/products/single5.jpg"></a>--%>
        											<%--<img src="img/products/single5.jpg" data-large="img/products/single5.jpg" alt="" />--%>
        										<%--</li>--%>
        									</ul>
        									<div class="product-arrows">
        										<div class="left-arrow">
        											<i class="icons icon-left-dir"></i>
        										</div>
        										<div class="right-arrow">
        											<i class="icons icon-right-dir"></i>
        										</div>
        									</div>
        								</div>
                                    </div>
                                    <!-- /Product Images Carousel -->

                                    <div class="col-lg-8 col-md-8 col-sm-8 product-single-info full-size">

                                        <h2>Lorem ipsum dolor sit amet</h2>
                                        <div class="rating-box">
        									<div class="rating readonly-rating" data-score="4"></div>
        									2 Review(s)
        								</div>
                                        <table>
                                        	<tr>
                                            	<td>Manufacturer</td>
                                                <td><a href="#">Manufacturer 1</a></td>
                                            </tr>
                                            <tr>
                                            	<td>Availability</td>
                                                <td><span class="green">in stock</span> 20 items</td>
                                            </tr>
                                            <tr>
                                            	<td>Product code</td>
                                                <td>PBS173</td>
                                            </tr>
                                        </table>

                                        <strong>Product Dimensions</strong>
                                        <table>
                                        	<tr>
                                            	<td>Product Width</td>
                                                <td>10.00000M</td>
                                            </tr>
                                            <tr>
                                            	<td>Product Length</td>
                                                <td>10.00000M</td>
                                            </tr>
                                        </table>

                                    	<span class="price"><del>$381.00</del> $281.00</span>

                                        <table class="product-actions-single">
                                        	<tr>

                                            </tr>
                                        </table>


                   				        </div>

                                    </div>

                                </div>
                             </section>
                            </div>


        		</div>




        		<!-- JavaScript -->

        		<script src="js/jquery-1.11.0.min.js"></script>
        		<script type="text/javascript" src="js/jquery-ui.min.js"></script>


        		<!-- Scroll Bar !!!-->
        		<script src="js/perfect-scrollbar.min.js"></script>



        		<!-- FlexSlider!!! -->
        		<script defer src="js/flexslider.min.js"></script>



        		<!-- Cloud Zoom!!! -->
        		 <script src="js/zoomsl-3.0.min.js"></script>

        		<!-- SelectJS !!!-->
                <script src="js/chosen.jquery.min.js" type="text/javascript"></script>

                <!-- Main JS -->
                 <script defer src="js/bootstrap.min.js"></script>
                <script src="js/main-script.js"></script>





        
    </body>
</html>
