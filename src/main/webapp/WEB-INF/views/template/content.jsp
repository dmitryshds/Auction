<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 27.01.2017
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<c:url var="home" value="/" scope="request" />
		<spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs" />

		<meta name="_csrf" content="${_csrf.token}"/>
		<meta name="_csrf_header" content="${_csrf.headerName}"/>

		<script src="${jqueryJs}"></script>

    </head>

    <body>
    			    <!-- Content -->
        			<div class="row content" >

        					<!-- Main Content -->
        					<section class="main-content col-lg-9 col-md-9 col-sm-9 col-lg-push-3 col-md-push-3 col-sm-push-3" >

								<div id="product">

								</div>

								<div class="row">

									<div class="col-lg-12 col-md-12 col-sm-12">

									</div>

									<%--DISPLAY PAGINATOR--%>
									<div id="count">

									</div>
								</div>


        					</section>
        					<!-- /Main Content -->

        					<!-- Sidebar -->
        					<aside class="sidebar col-lg-3 col-md-3 col-sm-3  col-lg-pull-9 col-md-pull-9 col-sm-pull-9">

        						<!-- Categories -->
        						<div class="row sidebar-box purple">

        						<div class="col-lg-12 col-md-12 col-sm-12">

        								<div class="sidebar-box-heading">

        										<i class="icons icon-folder-open-empty"></i>

												<h4>Categories</h4>

        								</div>

        								<div class="sidebar-box-content">

        									<ul>
        									 	<c:forEach items="${categories}" var="category">

                                                 	<li><a href="#" id="${category.idCategory}" class="category">${category.type} <i class="icons icon-right-dir"></i></a></li>

                                             	</c:forEach>
        									</ul>
        								</div>



        						</div>


        						</div>
        						<!-- /Categories -->
        					</aside>
        					<!-- /Sidebar -->

        			</div>
        			<!-- /Content -->

		<script>
            var id;
            var count = 0;
            var start;
            var quantity;
            var page = 1;

            $('.category').click(function () {
                id = parseInt(this.id);
                start = 0;
                quantity = 10;
                renderViaAjax(this.id, start, quantity);

            });


            function renderViaAjax(id, start, quantity) {
                if (start === undefined) {
                    start = 0;
                }
                if (quantity === undefined) {
                    quantity = 10;
                }
                console.log("SEND renderViaAjax  id = " + id + " count = " + count + " start = " + start + " quantity = " + quantity);

                $.ajax({
                    type: "POST",
                    url: "${home}cat/" + id + "/" + start + "/" + quantity,
                    timeout: 100000,
                    headers: {
                        'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
                    },
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        count = parseInt(data.count);
                        start = parseInt(data.start);
                        quantity = parseInt(data.quantity);
                        id = parseInt(id);
                        display(data.itemList);
                        console.log("RECIVE renderViaAjax  id = " + id + " count = " + count + " start = " + start + " quantity = " + quantity);

                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done: function (e) {
                        console.log("DONE");

                    }
                });

            }

            function display(data) {

                var arr = [];

                $.each(data, function (i, v) {
                    arr[i] = "<div class='col-lg-4 col-md-4 col-sm-4 product'" + " id='i" + v.idItems + "'" + ">" +
                        "<div class='product-image'>" +
                        "<img src='image"+
                          v.pictures +
                         "' alt='Item' style='width:100%; height:180px;'>" +
                        "</div>" +
                        "<div class='product-info'>" +
                        "<h5><a href='showItem/"+ v.idItems  +"'>" + v.name +
                        "</a></h5>" +
                        "<span class='price'>" + v.initialPrice +
                        "</span></div></div>";
                });

                var inner = "";
                for (var j in arr) {
                    inner += arr[j];
                }

                $('#product').html(inner);
                displayPaginator();

            }

            function displayPaginator() {
                var end = start + quantity;
                var st = start + 1;

                if (end > count) {
                    var diff = end - count;
                    end = end - diff;
                }
                if (count > 0) {
                    content = "<div class='col-lg-6 col-md-6 col-sm-6'>" +
                        "<div class='category-results' >" +
                        "<p>Results " + st + "-" + end + " of " + count + "</p>" +
                        "<p>Show" +
                        "<select class='chosen-select'>" +
                        "<option>-</option>" +
                        "<option>10</option>" +
                        "<option >20</option>" +
                        "<option>30</option>" +
                        "</select>per page</p></div>";
                } else {
                    content = "<div class='col-lg-6 col-md-6 col-sm-6'>" +
                        "<div class='category-results' >" +
                        "<p>Results 0-" + count + " of " + count + "</p>" +
                        "<p>Not items in category" +
                        "</p></div></div>"
                }
                var pages = Math.ceil(count / quantity);


                var tail = "<div class='pagination-results' >";

                if (pages < 6) {
                    for (var i = 1; i <= pages; i++) {
                        tail += "<a href='#' class='pagination-page'>" + i + "</a>";
                    }
                }
                else {
            //					   Need test if pages more then 6
                    var j = page;  //start chosen

                    var f = pages; //end

                    if (j < 5 && j != f) {

                        for (var k = 1; k <= 5; k++) {
                            tail += "<a href='#' class='pagination-page'>" + k + "</a>";
                        }
                        tail += "<a href='#'>" + "..." + "</a>";
                        for (var h = f - 1; h <= f; h++) {
                            tail += "<a href='#' class='pagination-page'>" + h + "</a>";
                        }

                    }
                    if (j != f) {

                        tail += "<a href='#' class='pagination-page'>" + 1 + "</a>";
                        tail += "<a href='#'>" + "..." + "</a>";
                        for (var d = page - 2; d <= page + 1; d++) {
                            tail += "<a href='#' class='pagination-page'>" + d + "</a>";
                        }
                        tail += "<a href='#'>" + "..." + "</a>";
                        tail += "<a href='#' class='pagination-page'>" + pages + "</a>";
                    }
                    if (j == f) {
                        tail += "<a href='#' class='pagination-page'>" + 1 + "</a>";
                        tail += "<a href='#'>" + "..." + "</a>";
                        for (var s = page - 2; s <= page; s++) {
                            tail += "<a href='#' class='pagination-page'>" + s + "</a>";
                        }

                    }

                }

                tail += "</div></div>";
                content = content + tail;
                $('#count').html(content);
            }

            $(document).on('click', 'a.pagination-page', function () {


                $('a.pagination-page').removeClass('active');

                $(this).addClass('active');
                page = parseInt(this.text);
                start = (quantity * page - quantity);
                console.log(start);
                renderViaAjax(id, start, quantity);
            });

            $(document).on('change', '.chosen-select', function () {
                var selected = $(this).find(":selected").val();
                start = (quantity * page - quantity);
                quantity = parseInt(selected);

                if (start < count) {
                    $(this).find(":selected").attr("selected", "selected");
                    renderViaAjax(id, start, quantity);

                }
            });

		</script>
    </body>
</html>
