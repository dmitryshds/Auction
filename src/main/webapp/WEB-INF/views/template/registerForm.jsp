<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 05.02.2017
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <title>Title</title>
        <c:url var="home" value="/" scope="request"/>
        <spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>
        <script src="${jqueryJs}"></script>
    </head>
    <body>
        <div class="row content">

            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="breadcrumbs">
                    <p><a href="index">Home</a> <i class="icons icon-right-dir"></i> Create an account</p>
                </div>
            </div>
            <!-- Main Content -->
            <section class="main-content col-lg-9 col-md-9 col-sm-9 alline-center" >


                <div class="row">

                    <div class="col-lg-12 col-md-12 col-sm-12">


                        </div>


                    </div>




                <div class="row">

                    <div class="col-lg-12 col-md-12 col-sm-12 register-account">

                        <div class="carousel-heading no-margin">
                            <h4>Register</h4>
                        </div>
                        <%--<form:form method="post" enctype="multipart/form-data" modelAttribute="user" action="register">--%>
                        <form:form method="post"  modelAttribute="user" action="newuser">
                        <div class="page-content">
                            <div class="row">

                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <p><strong>Your information</strong></p>
                                </div>

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>E-mail*</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <form:input type="text" path="email" id="email"/>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Username(Login)*</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <form:input type="text" path="login" id="login"/>
                                </div>

                            </div>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Displayed name*</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Password*</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <form:input type="password" path="password" id="password" class="form-control"/>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Confirm Password*</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8 form-group">
                                    <input type="password"  id="confPassword" class="form-control"/>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>I agree to the terms of service</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="checkbox"  id="i-agree-to-terms"/><label for="i-agree-to-terms"></label>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <p><strong>Account details</strong></p>
                                </div>
                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Company Name</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Title</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <select class="chosen-select"  id="chosenSel">
                                        <option>Mr</option>
                                        <option>Mrs</option>
                                    </select>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>First name</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <form:input type="text" path="firstName" id="firstName"/>
                                </div>

                            </div>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Middle Name</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Last Name</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <form:input type="text" path="lastName" id="lastName"/>
                                </div>

                            </div>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Address</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Address 2</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>ZIP / Postal Code</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="text" />
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>City</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="text" id="city"/>
                                </div>

                            </div>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Country</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="text"  id="country"/>
                                </div>

                            </div>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>State / Province / Region</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <div class="row">

                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Phone</p>
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="text"  id="homeNumber"/>
                                </div>

                            </div>

                            <%--<div class="row">--%>

                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>Mobile Phone</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                            <%--</div>--%>

                            <div class="row">
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                    <p>Upload your avatar (2Mb max)</p>
                                    <p id = "error-len" style="display: none; color: #ff0000;"></p>
                                 </div>
                                <div class="col-lg-8 col-md-8 col-sm-8" >
                                    <img id="disp-image" src="#" style="width: 150px; height: 200px; display: none;"/>

                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                </div>
                                <div class="col-lg-8 col-md-8 col-sm-8">
                                    <input type="file"  name="files" id="upload-image"  accept=".png, .jpg, .gif, .jpeg"/><br/>
                                    <div  id="rem-image">
                                    <a href="#">Delete image</a>
                                    </div>
                                </div>




                                <%--<div class="col-lg-4 col-md-4 col-sm-4">--%>
                                    <%--<p>FAX</p>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-8 col-md-8 col-sm-8">--%>
                                    <%--<input type="text">--%>
                                <%--</div>--%>

                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <input class="big" type="submit" value="Register">
                                    <input class="big" type="reset" value="Cancel" >
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

           var maxFileSize = 2 * 1024 * 1024;

            $(document).on("change", "#upload-image", function() {

                 var file = $(this)[0].files;

                if ( errorMsg = validateFile(file) ) {

                    $('#error-len').html(errorMsg).show();
                      return;
                    }

                $('#error-len').html(errorMsg).hide();

                var img2 = readURL(this);
                readURL(img2);
            });

            function readURL(input) {
                    if (input) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#disp-image')
                                .attr('src', e.target.result).show();
                            $('#rem-image').show();
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                }
            var validateFile = function(file)
                {
                    if ( file[0].size > maxFileSize ) {
                      return 'Image size more then 2Mb';
                      }
                };

                $(document).on("click", "#rem-image", function () {

                    $('#disp-image').hide();

                    $('#upload-image').val('');

                    $('#rem-image').hide();
                })


        </script>


    </body>
</html>