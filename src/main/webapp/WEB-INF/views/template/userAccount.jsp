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



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Title</title>

<c:url var="home" value="/" scope="request"/>

<spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>
<spring:url value="/resources/js/bootstrap-filestyle.min.js" var="fileStyle"/>

        <script src="${jqueryJs}"></script>
<script src="${fileStyle}"></script>


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
                                        <form method="post" action="changeAvatar" enctype="multipart/form-data">
                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                                <img src="<c:url value="image${user.picture}"/>" style="width: 150px; height:150px;" id="img-avatar">

                                        </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8" style=" height: 150px; ">



                                                <input class="filestyle" type="file" name="file" id="upload-avatar" accept=".png, .jpg, .gif, .jpeg" data-icon="false"/><br/>

                                                <input type="hidden" name="userId" value="${user.idUsers}"/>

                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                                <div>
                                                    <input class="big" type="submit" value="Upload Avatar" style="background-color: #DDD;color: #999;" disabled="disabled" id="save-avat"/>

                                                    <input class="big" type="reset" value="Reset" id="remove-image" hidden="hidden"/>
                                                </div>
                                                <p id="error-len" style="color: #ff0000;" hidden="hidden"></p>
                                        </div>

                                        </form>

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
                                            <span style="color: red; float: right; margin-right: 70px; display: none;" id="err-email"> Incorrect e-mail</span>


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
                                                     <input type="text" name="email" id="email" value="${user.email}" class="input-align-right" hidden="hidden">
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

                                    <form:form method="post" action="changeUser">
                                           <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>First name</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.firstName}</b>
                                                      <input type="text" name="firstName" value="${user.firstName}" class="input-align-right" hidden="hidden">
                                                  </div>
                                               <div class="col-lg-12 col-md-12 col-sm-12">



                                               </div>

                                           </div>

                                        <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Last Name</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.lastName}</b>
                                                      <input type="text" name="lastName" value="${user.lastName}" class="input-align-right" hidden="hidden">
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>ZIP / Postal Code</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.zip}</b>
                                                      <input type="text" name="zip" value="${user.zip}" class="input-align-right" hidden="hidden">
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>City</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.city}</b>
                                                      <input type="text" name="city" value="${user.city}" class="input-align-right" hidden="hidden">
                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Country</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.country}</b>
                                                      <input type="text" name="country" value="${user.country}" class="input-align-right" hidden="hidden">

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>Street</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.street}</b>
                                                      <input type="text" name="street" value="${user.street}" class="input-align-right" hidden="hidden">

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Phone</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <b>${user.homeNumber}</b>
                                                      <input type="text" name="homeNumber" value="${user.homeNumber}" class="input-align-right" hidden="hidden">

                                                  </div>

                                          </div>

                                          <div class="row">

                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                            </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8">

                                            </div>

                                            <input type="hidden" name="new-email" value="" id="new-email"/>
                                            <input type="hidden" name="userId" value="${user.idUsers}"/>

                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                            <div class="col-lg-12 col-md-12 col-sm-12">

                                                <input class="big" type="button" value="Edit" id="edit" style="background-color: green;color: #999;"/>
                                                <input class="big" type="submit" value="Save changes" id="save-changes" style="background-color: #DDD;color: #999;" disabled="disabled"/>

                                                <input class="big" type="reset" value="Reset"  id="reset" />

                                            </div>

                            </div>

                        </div>
                             </form:form>
                    </div>

                </div>

                    </div>
                 </section>
            <!-- /Main Content -->
             </div>
        <script>

        </script>

<script>

    $(":file").filestyle({input: false});


    $('#reset').click(function () {
        $('.input-align-right').hide();
        $('#save-changes').attr("style", "background-color: #DDD;color: #999;").attr("disabled", "disabled");
    });

    $('#edit').click(function () {

        $('.input-align-right').show();
        $('#save-changes').removeAttr("style").removeAttr("disabled");


    });

    $('#email').change(function () {
        $('#err-email').hide();
        var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        var mail = $(this).val();
        if (!pattern.test(mail)) {
            $('#err-email').show();
            $('#save-changes').attr("style", "background-color: #DDD;color: #999;").attr("disabled", "disabled");

        } else {
            var email = $('#email').val();
            $('#new-email').val(email);
            $('#save-changes').removeAttr("style").removeAttr("disabled");
        }
    });

    var maxFileSize = 2 * 1024 * 1024;

    $('#upload-avatar').change(function () {

        var file = $(this)[0].files;

        if (errorMsg = validateFile(file)) {

            $('#error-len').html(errorMsg).show();

            return;
        }
        $('#error-len').html(errorMsg).hide();

        var img = readURL(this);

        readURL(img);
    });

    function readURL(input) {
        if (input) {

            var reader = new FileReader();

            reader.onload = function (e) {

                $('#img-avatar').removeAttr('src')

                        .attr('src', e.target.result);
                $('#save-avat').removeAttr('disabled', 'disabled').removeAttr("style");

                $('#remove-image').show();
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    function validateFile(file) {
        if (file[0].size > maxFileSize) {

            return 'Image size more then 2Mb';

        }
    }
    ;
    $(document).on("click", "#remove-image", function () {

        $('#img-avatar').removeAttr('src');

        $('#save-avat').attr('disabled', 'disabled').attr("style", "background-color: #DDD;color: #999;");

        $('#remove-image').hide();
    })


</script>