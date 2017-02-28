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

                        <p><a href="index">Home</a> <i class="icons icon-right-dir"></i> Create an account</p>

                    </div>

                </div>
            <!-- Main Content -->
                <section class="main-content col-lg-9 col-md-9 col-sm-9 alline-center" >

                    <div class="row">

                        <div class="col-lg-12 col-md-12 col-sm-12 register-account">

                            <div class="carousel-heading no-margin">

                                <h4>Register: STEP 1</h4>

                            </div>

                            <form:form method="post"  modelAttribute="user" action="newuser">

                                <div class="page-content">

                                    <div class="row">

                                        <div class="col-lg-12 col-md-12 col-sm-12">

                                             <p><strong>Your information</strong></p>

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

                                            <input type="text"  id="login" name="login"/>

                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col-lg-4 col-md-4 col-sm-4">

                                            <p>Password*

                                                <span style="color: red; display: none " id="err-password"> Incorrect value min 6 max 12 characters</span>

                                                <span style="color: green; display: none " id="corr-password"> Correct value</span>

                                            </p>

                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-8">

                                             <input type="password" name="password" id="password" class="form-control"/>

                                        </div>

                                    </div>

                                    <div class="row">

                                        <div class="col-lg-4 col-md-4 col-sm-4">

                                            <p>Confirm Password*

                                                <span style="color: red; display: none " id="err-confPassword"> Passwords do not match</span>

                                                <span style="color: green; display: none " id="corr-confPassword"> Correct value</span>

                                            </p>

                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-8 form-group">

                                            <input type="password"  id="confPassword" class="form-control"/>

                                        </div>

                                    </div>

                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                                <p>E-mail*

                                                    <span style="color: red; display: none " id="err-email"> Incorrect e-mail</span>

                                                    <span style="color: green; display: none " id="corr-email"> Correct e-mail</span>

                                                </p>

                                            </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8">

                                            <input type="text" name="email" id="email"/>

                                            </div>

                                        </div>

                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-4">

                                                <p>I agree to the terms of service*</p>

                                            </div>

                                            <div class="col-lg-8 col-md-8 col-sm-8">

                                                <input type="checkbox"  id="i-agree-to-terms" name="checkbox"/><label for="i-agree-to-terms"></label>

                                            </div>

                                        </div>

                                    <div class="row">

                                        <div class="col-lg-12 col-md-12 col-sm-12">

                                                  <p><strong>Account details</strong></p>

                                              </div>

                                       </div>

                                      <div class="row">

                                          <div class="col-lg-4 col-md-4 col-sm-4">

                                              <p>Title</p>

                                          </div>

                                          <div class="col-lg-8 col-md-8 col-sm-8">

                                              <select class="chosen-select" id="titles" name="titles">

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

                                                      <input type="text" name="firstName"  id="firstName"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Last Name</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text" name="lastName" id="lastName"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>ZIP / Postal Code</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text"  name="zip" id="zip"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>City</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text" name="city" id="city"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Country</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text" name="country"  id="country"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                          <p>Street</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text" name="street" id="street"/>

                                                  </div>

                                          </div>

                                          <div class="row">

                                                  <div class="col-lg-4 col-md-4 col-sm-4">

                                                      <p>Phone</p>

                                                  </div>

                                                  <div class="col-lg-8 col-md-8 col-sm-8">

                                                      <input type="text" name="homeNumber" id="homeNumber"/>

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

     var loginValid;
     var passwordValid;
     var emailValid;
     var termsValid;

$('#login').blur(function () {
    $('#err-login').hide();
    $('#corr-login').hide();
    console.log("blur login");
    var text = $(this).val();
    if(text.length < 2)
    {
      $('#err-login').show();

    } else{

$.ajax({
    type: "POST",
    url: "${home}userlogin/"+text,
    timeout: 100000,
    headers: {
        'X-CSRF-TOKEN': $("meta[name='_csrf']").attr("content")
    },
    success: function (data) {

        if(data) {
            $('#corr-login').show();
            loginValid = true;
            validate();
        } else{
            $('#err-login').html('login is busy').show();
        }
    },
    error: function (e) {
        console.log("ERROR: ", e);

    },
    done: function (e) {
        console.log("DONE");

    }
});


    }
});

$('#password').blur(function () {
    $('#err-password').hide();
    $('#corr-password').hide();
    var text = $(this).val();
    if(text.length < 6)
    {
      $('#err-password').show();

    } else{
        $('#corr-password').show();
        validate();
    }
});

$('#confPassword').blur(function () {
    $('#err-confPassword').hide();
    $('#corr-confPassword').hide();
    var pass = $('#password').val();
    var confPass = $(this).val();
    if(pass != confPass)
    {
      $('#err-confPassword').show();

    } else{
        $('#corr-confPassword').show();
        passwordValid = true;
        validate();
    }
});

$('#email').blur(function () {
    $('#err-email').hide();
    $('#corr-email').hide();
    var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    var mail = $(this).val();
    if(!pattern.test(mail))
    {
      $('#err-email').show();

    } else{
        $('#corr-email').show();
        emailValid = true;
        validate();
    }
});

$('#i-agree-to-terms').click(function () {


    var check = $('#i-agree-to-terms').is(':checked');


    if(check)
    {

        termsValid = true;
        validate();

    }
    else{
        $('#register').attr("style","background-color: #DDD;color: #999;").attr("disabled","disabled");

    }
});

$('#reset').click(function () {

    $('#err-login').hide();
    $('#corr-login').hide();

    $('#err-password').hide();
    $('#corr-password').hide();

    $('#err-confPassword').hide();
    $('#corr-confPassword').hide();

    $('#err-email').hide();
    $('#corr-email').hide();
});

 function validate() {
     if(loginValid && passwordValid && emailValid && termsValid)
     {
         $('#register').removeAttr("style").removeAttr("disabled");
     }
 }

        </script>

    </body>

</html>