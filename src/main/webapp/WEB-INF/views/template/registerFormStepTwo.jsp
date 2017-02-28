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

        <title>Register Step 3</title>

            <c:url var="home" value="/" scope="request"/>

            <spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>

            <spring:url value="/resources/js/uploadFile.js" var="uploadJs"/>

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

                                    <h4>Register: STEP 2</h4>

                            </div>

                        <form method="post"  action="stepTwo" enctype="multipart/form-data" >

                            <div class="page-content">

                                <div class="row">

                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <p><strong>Your information</strong></p>
                                    </div>

                                </div>

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

                                    <input type="file"  name="file"  id="upload-image"  accept=".png, .jpg, .gif, .jpeg"/><br/>

                                    <%--<div  id="rem-image">--%>

                                        <%--<a href="#">Delete image</a>--%>

                                    <%--</div>--%>

                                </div>

                                    <input type="hidden"   name="userId" value="${userId}"/>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


                                <div class="col-lg-12 col-md-12 col-sm-12">

                                    <input class="big" type="submit" value="Complete Registration"/>

                                    <input class="big" type="reset" value="Reset" id="rem-image"/>

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

            <%--src="${uploadJs}"--%>
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