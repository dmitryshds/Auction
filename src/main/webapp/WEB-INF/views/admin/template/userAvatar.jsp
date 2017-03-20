<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 14.03.2017
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url var="home" value="/" scope="request"/>

<spring:url value="/resources/js/jquery-3.1.1.js" var="jqueryJs"/>

<spring:url value="/resources/js/uploadFile.js" var="uploadJs"/>

<script src="${jqueryJs}"></script>

<div class="span9" id="content">
<div class="row-fluid">

    </div>
<div class="row-fluid">
    <!-- block -->

</div>
<div class="row-fluid row-width">
    <!-- block -->
    <div class="block">
        <div class="navbar navbar-inner block-header">
            <div class="muted pull-left">User avatar</div>
        </div>
        <div class="block-content collapse in">
            <div class="span12">
                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered"
                       id="category-table">

                    <tbody>
                        <tr>
                            <td>
                                <form method="post" action="${contextPath}/admin/userAvatar" enctype="multipart/form-data">

                                    <div>

                                        <p>Upload standart user avatar (2Mb max)</p>

                                        <p id="error-len" style="display: none; color: #ff0000;"></p>

                                        <c:if test="${not empty error}">

                                    	<div class="error" >${error}</div>

                                        </c:if>
                                        <c:if test="${not empty success}">

                                    	<div class="success" >${success}</div>

                                        </c:if>

                                    </div>

                                    <div>

                                        <img id="disp-image" src="#"
                                             style="width: 150px; height: 200px; display: none;"/>

                                    </div>


                                    <div>

                                        <input type="file" name="file" id="upload-image"
                                               accept=".png, .jpg, .gif, .jpeg"/><br/>


                                        <input type="hidden" name="userId" value="${userId}"/>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    </div>
                                    <div>

                                        <input class="big" type="submit" value="Upload"/>

                                        <input class="big" type="reset" value="Reset" id="rem-image"/>

                                    </div>


                                </form>
                            </td>
                            <td>
                                <img src="/show/adm/avat.jpg">
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- /block -->
</div>
</div>
</div>
</div>

<script>

    var maxFileSize = 2 * 1024 * 1024;

    $(document).on("change", "#upload-image", function () {

        var file = $(this)[0].files;

        if (errorMsg = validateFile(file)) {

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
    function validateFile(file) {
        if (file[0].size > maxFileSize) {

            return 'Image size more then 2Mb';

        }
    }
    ;

    $(document).on("click", "#rem-image", function () {

        $('#disp-image').hide();

        $('#upload-image').val('');

        $('#rem-image').hide();
    })

</script>


<%--</body>--%>
<%--</html>--%>