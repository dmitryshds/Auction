<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 05.02.2017
  Time: 17:29
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

    </head>
    <body>
        <div class="row content">

            <div class="col-lg-12 col-md-12 col-sm-12">

                <div class="breadcrumbs">

                    <p><a href="index">Home</a> <i class="icons icon-right-dir"></i> Add new Item</p>

                </div>

            </div>
            <!-- Main Content -->
            <section class="main-content col-lg-9 col-md-9 col-sm-9 alline-center">

                <div class="row">

                    <div class="col-lg-12 col-md-12 col-sm-12 register-account">

                        <div class="carousel-heading no-margin">

                            <h4>Add new Item</h4>

                        </div>


                        <form method="post" action="newitem" enctype="multipart/form-data">

                            <div class="page-content">

                                <div class="row">

                                    <div class="col-lg-12 col-md-12 col-sm-12">

                                        <p><strong>Your information</strong></p>

                                    </div>

                                    <div class="col-lg-4 col-md-4 col-sm-4">


                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">
                                        <c:if test="${not empty error}">

                                            <div class="error">${error}</div>

                                        </c:if>

                                        <c:if test="${not empty message}">

                                            <div class="message">${message}</div>

                                        </c:if>

                                    </div>
                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>Select category* </p>

                                        <span style="color: red; display: none " id="err-categoryType"> Incorrect, field Category must be selected</span>

                                        <span style="color: green; display: none " id="corr-categoryType"> Correct </span>


                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <select class="chosen-select" id="categoryType" name="categoryType">
                                                 <option></option>
                                            <c:forEach items="${category}" var="category">

                                                <option>${category.type}</option>

                                            </c:forEach>

                                        </select>


                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>Name* </p>

                                        <span style="color: red; display: none " id="err-name"> Incorrect, field can't be empty</span>

                                        <span style="color: green; display: none " id="corr-name"> Correct </span>


                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <input type="text" name="name" id="name" class="form-control"/>

                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>Description </p>

                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8 form-group">

                                        <input type="text" id="description" name="description" class="form-control"/>

                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>Initial price* </p>

                                        <span style="color: red; display: none " id="err-initialPrice"> Incorrect, only numbers or field can't be empty</span>

                                        <span style="color: green; display: none "
                                              id="corr-initialPrice"> Correct </span>


                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <input type="text" name="initialPrice" id="initialPrice"/>

                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>By now price</p>

                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <input type="text" name="buynowPrice" id="buynowPrice"/>

                                    </div>

                                </div>


                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>Pictures</p>

                                        <p id="error-len" style="display: none; color: #ff0000;"></p>

                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <input type="file" name="files" id="upload-image-1"
                                               accept=".png, .jpg, .gif, .jpeg"/><br/>
                                        <input type="file" name="files" id="upload-image-2"
                                               accept=".png, .jpg, .gif, .jpeg"/><br/>
                                        <input type="file" name="files" id="upload-image-3"
                                               accept=".png, .jpg, .gif, .jpeg"/><br/>
                                        <input type="file" name="files" id="upload-image-4"
                                               accept=".png, .jpg, .gif, .jpeg"/><br/>

                                        <div id="image-container">
                                            <img id="disp-image-1" src="#" class="im-preview"/>
                                            <img id="disp-image-2" src="#" class="im-preview"/>
                                            <img id="disp-image-3" src="#" class="im-preview"/>
                                            <img id="disp-image-4" src="#" class="im-preview"/>

                                        </div>
                                    </div>

                                </div>

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                        <p>End date*</p>

                                        <span style="color: red; display: none " id="err-dateFinish"> Incorrect, invalid data</span>

                                        <span style="color: green; display: none " id="corr-dateFinish"> Correct </span>


                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">

                                        <input type="text" name="dateFinish" id="datepicker"/>

                                    </div>

                                </div>

                            </div>

                            <div class="row">

                                <div class="row">

                                    <div class="col-lg-4 col-md-4 col-sm-4">

                                    </div>

                                    <div class="col-lg-8 col-md-8 col-sm-8">


                                    </div>

                                    <input type="hidden" name="userName" id="userName"
                                           value=" <sec:authentication property="principal.username"/>">

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <div class="col-lg-12 col-md-12 col-sm-12">

                                        <input class="big" type="submit" value="Add Item" style="background-color: #DDD;color: #999;" id="register" disabled="disabled"/>

                                        <input class="big" type="reset" value="Reset" id="reset"/>

                                    </div>

                                </div>

                            </div>
                        </form>
                    </div>

                </div>


            </section>
            <!-- /Main Content -->
        </div>

        <script>
//            DATE PICKER
            $(function () {
                $("#datepicker").datepicker();
            });

            //          UPLOAD FILE

            var maxFileSize = 2 * 1024 * 1024;
            var errorMsg = 'Image size more then 2Mb';

            $(document).on("change", "#upload-image-1", function () {
                var file = $(this)[0].files;
                if (file[0].size > maxFileSize) {

                    $('#error-len').html(errorMsg).show();
                    return;
                }
                $('#error-len').html(errorMsg).hide();
                if (this) {

               var reader = new FileReader();

               reader.onload = function (e) {

                   $('#disp-image-1').attr('src', e.target.result).show();
               };
               reader.readAsDataURL(this.files[0]);
             }});



            $(document).on("change", "#upload-image-2", function () {
                var file = $(this)[0].files;
                if (file[0].size > maxFileSize) {

                    $('#error-len').html(errorMsg).show();
                    return;
                }
                $('#error-len').html(errorMsg).hide();
                if (this) {

               var reader = new FileReader();

               reader.onload = function (e) {

                   $('#disp-image-2').attr('src', e.target.result).show();
               };
               reader.readAsDataURL(this.files[0]);
             }});

            $(document).on("change", "#upload-image-3", function () {
                var file = $(this)[0].files;
                if (file[0].size > maxFileSize) {

                    $('#error-len').html(errorMsg).show();
                    return;
                }
                $('#error-len').html(errorMsg).hide();
                if (this) {

               var reader = new FileReader();

               reader.onload = function (e) {

                   $('#disp-image-3').attr('src', e.target.result).show();
               };
               reader.readAsDataURL(this.files[0]);
             }});

            $(document).on("change", "#upload-image-4", function () {
               var file = $(this)[0].files;
               if (file[0].size > maxFileSize) {

                   $('#error-len').html(errorMsg).show();
                   return;
               }
               $('#error-len').html(errorMsg).hide();
               if (this) {

              var reader = new FileReader();

              reader.onload = function (e) {

                  $('#disp-image-4').attr('src', e.target.result).show();
              };
              reader.readAsDataURL(this.files[0]);
            }});
            $(document).on("click", "#reset", function () {

                       $('#disp-image-1').hide();
                       $('#disp-image-2').hide();
                       $('#disp-image-3').hide();
                       $('#disp-image-4').hide();
                       messCatHide();
                       messMameHide();
                       messPriceHide();
                       messDateHide();
             });

      //           FIELDS VALIDATOR

        var categoryValid;
        var nameValid;
        var priceValid;
        var dateValid;

        function messCatHide() {
            $('#corr-categoryType').hide();
            $('#err-categoryType').hide();
        }

         $('#categoryType').blur(function () {
             messCatHide();
          var cat = $(this).val();
            if(cat){
                $('#corr-categoryType').show();
                categoryValid = true;
                validate();
            } else {
                $('#err-categoryType').show();
            }

         });

          function messMameHide() {
            $('#corr-name').hide();
            $('#err-name').hide();
        }


        $('#name').blur(function () {
            messMameHide();
         var cat = $(this).val();
           if(cat){
               $('#corr-name').show();
              nameValid = true;
               validate();
           } else {
               $('#err-name').show();
           }

        });

        function messPriceHide() {
            $('#corr-initialPrice').hide();
            $('#err-initialPrice').hide();
        }


        $('#initialPrice').blur(function () {
            messPriceHide();
            var price = $(this).val();
            var patt =/^[0-9\s]+$/i;
           if(patt.test(price)){
               $('#corr-initialPrice').show();
              priceValid = true;
               validate();
           } else {
               $('#err-initialPrice').show();
           }

        });

       function messDateHide() {
            $('#corr-dateFinish').hide();
            $('#err-dateFinish').hide();
        }


        $('#datepicker').change(function () {
            messDateHide();
           var val = $(this).val();
           var val1 = Date.parse(val);
            var currDate = new Date();

        if (  currDate < val1){
            $('#corr-dateFinish').show();
              dateValid = true;
               validate();
           }
           else {
            $('#err-dateFinish').show();
           }

        });

       function validate() {
         if(categoryValid && nameValid && priceValid && dateValid)
         {
             $('#register').removeAttr("style").removeAttr("disabled");
         }
         };

        </script>

    </body>

</html>