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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Title</title>

        <c:url var="home" value="/" scope="request"/>

        <spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>
        <spring:url value="/resources/js/jquery-ui.js" var="jqueryUI"/>
        <spring:url value="/resources/js/zoom/bootstrap.min.js" var="zoom1"/>
        <spring:url value="/resources/js/zoom/chosen.jquery.min.js" var="zoom2"/>
        <spring:url value="/resources/js/zoom/flexslider.min.js" var="zoom3"/>
        <spring:url value="/resources/js/zoom/jquery-1.11.0.min.js" var="zoom4"/>
        <spring:url value="/resources/js/zoom/jquery-ui.min.js" var="zoom5"/>
        <spring:url value="/resources/js/zoom/main-script.js" var="zoom6"/>
        <spring:url value="/resources/js/zoom/perfect-scrollbar.min.js" var="zoom7"/>
        <spring:url value="/resources/js/zoom/zoomsl-3.0.min.js" var="zoom8"/>


        <script src="${jqueryUI}">

        </script>


        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<c:set var="owner" value="${item.owner}"/>
        <c:set var="picture" value="${item.pictures}"/>
        <c:set var="bids" value="${item.bidSet}"/>

        <!-- Container -->
        		<div class="container">



        			<!-- Content -->
        			<div class="row content">


                        <div class="col-lg-12 col-md-12 col-sm-12">
                        	<div class="breadcrumbs">
                            	<p><a href="${contextPath}/index">Home</a> <i class="icons icon-right-dir"></i> <span>${item.category.type} </span> <i class="icons icon-right-dir"></i> Item</p>
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
                                                    <c:if test="${fn:length(picture) gt 0}">
                                                        <img class="cloud-zoom" src="/image${picture[0]}"
                                                             data-large="/image${picture[0]}" alt=""/>
                                                        <a class="fullscreen-button" href="/image${picture[0]}">

                                                            <div class="product-fullscreen">
        													<i class="icons icon-resize-full-1"></i>
        												</div>
        											</a>
                                                    </c:if>
        										</li>
        									</ul>
        								</div>
        								<div id="product-carousel">
        									<ul class="slides">
                                                <c:if test="${fn:length(picture) gt 1}">

                                                    <c:forEach var="i" begin="1" end="${fn:length(picture)-1}">
                                                        <li>
                                                            <a class="fancybox" rel="product-images"
                                                               href="/image${picture[i]}"></a>
                                                            <img src="/image${picture[i]}"
                                                                 data-large="/image${picture[i]}" alt=""/>
                                                        </li>
                                                    </c:forEach>

                                                </c:if>
                                            </ul>
        								</div>
                                    </div>
                                    <!-- /Product Images Carousel -->

                                    <div class="col-lg-8 col-md-8 col-sm-8 product-single-info full-size">

                                        <h2>${item.name}</h2>

                                        <table>
                                        	<tr>
                                                <td>Owner:</td>
                                                <td><a href="#"> ${item.owner.login}</a></td>
                                            </tr>
                                            <tr>
                                                <td>Initial price</td>
                                                <td><span class="green"></span> ${item.initialPrice}</td>
                                            </tr>
                                            <c:if test="${not empty item.buynowPrice}">
                                                <tr>
                                                    <td>By now price</td>
                                                    <td><a href="#"> ${item.buynowPrice} </a></td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td>Description</td>
                                                <td>${item.description}</td>
                                            </tr>
                                        </table>

                                        <strong></strong>
                                        <table>
                                        	<tr>
                                                <td>Added</td>
                                                <td><fmt:formatDate type="both" value="${item.dateStart}"/></td>
                                            </tr>

                                            <tr>
                                                <td>Left :</td>
                                                <td id="demo"></td>
                                            </tr>
                                        </table>
                                        <c:if test="${fn:length(bids) gt 0}">
                                            <c:set var="len" value="${fn:length(bids)}"/>
                                            <c:forEach items="${bids}" var="b" begin="${len-1}" end="${len}">
                                                <span class="price" id="latest-price">${b.bid}</span>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${fn:length(bids) == 0}">
                                            <span class="price" id="latest-price">${item.initialPrice}</span>
                                        </c:if>

                                        <sec:authorize access="isAuthenticated()">
                                            <form action="/newbid" class="product-actions-single" method="post">
                                                <table>
                                                    <tr>

                                                        <td><input type="text" id="bid" name="bid"></td>
                                                        <td><input class="big" type="submit" value="Add bid" style="background-color: #DDD;color: #999;" id="addBid" disabled="disabled"/></td>
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                        <input type="hidden" name="itemId" value="${item.idItems}"/>
                                                        <input type="hidden" name="userName" id="userName" value=" <sec:authentication property="principal.username"/>">
                                                    </tr>
                                                    <tr id="error">
                                                    </tr>
                                                </table>
                                            </form>
                                        </sec:authorize>

                                        <table >
                                            <h3 class="table">Latest Bids :</h3>
                                            <tr>
                                                <td>Date: </td>
                                                <td>Bidder: </td>
                                                <td>Bid: </td>
                                            </tr>
                                            <c:forEach items="${bids}" var="b">
                                                <tr>
                                                    <td><fmt:formatDate type="both" value="${b.bidDate}"/></td>
                                                    <td>${b.userBidder.login}</td>
                                                    <td>${b.bid}</td>
                                                </tr>
                                            </c:forEach>



                                        </table>


                   				        </div>

                                    </div>

                                </div>
                             </section>
                            </div>


        		</div>




        		<!-- JavaScript -->

        <script type="text/javascript" src="${zoom4}">

        </script>
        <script src="${zoom5}"></script>


        		<!-- Scroll Bar !!!-->
        <script src="${zoom7}"></script>



        		<!-- FlexSlider!!! -->
        <script defer src="${zoom3}"></script>



        		<!-- Cloud Zoom!!! -->
        <script src="${zoom8}"></script>

        		<!-- SelectJS !!!-->
        <script src="${zoom2}"></script>

                <!-- Main JS -->
        <script defer src="${zoom1}"></script>
        <script src="${zoom6}"></script>

        <script type="text/javascript">
//             CountDown Timer
            var countDownDate = ${item.dateFinish.time};
            var x = setInterval(function () {
            var now = new Date().getTime();
            var distance = countDownDate - now;
            var days = Math.floor(distance / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);
            document.getElementById("demo").innerHTML = days + "d " + hours + "h "
              + minutes + "m " + seconds + "s ";
            if (distance < 0) {
                clearInterval(x);
             document.getElementById("demo").innerHTML = "EXPIRED";
                nonActiveDutton();
            }
        }, 1000);


        </script>

        <script type="text/javascript">
            document.getElementById('bid').oninput   = function () {
             nonActiveButton();
              hideError();
             var text = this.value;
             if (checkBid(text))
             {
                 activeButton();
             }
             else {
                 if(text == '')
                 {
                     return;
                 }else {
                     showError();
                 }
             }
            };

         function nonActiveButton() {
             document.getElementById('addBid').setAttribute("style","background-color: #DDD;color: #999;");
             document.getElementById('addBid').setAttribute("disabled","disabled");

         };

         function activeButton() {
           document.getElementById('addBid').removeAttribute("style");
           document.getElementById('addBid').removeAttribute("disabled");

         }

         function showError() {
             document.getElementById('error').innerHTML ="<span style='color: #ff0000' id='err'>Only digit character, and your bid can't be less then price</span>";
         }
         function hideError() {
             var elem = document.getElementById("err");
             var parent = document.getElementById("error");
             if (elem) {
                  parent.removeChild(elem);
               }

         }
         function checkBid(e) {
           var latestBid =  document.getElementById('latest-price').textContent;
             console.log("latestBid = "+latestBid);
           var price = parseFloat(latestBid);
           var bid = parseFloat(e);
             if (price < bid){
                 return true;
             }else {
                 return false;
             }
         }






        </script>


        

