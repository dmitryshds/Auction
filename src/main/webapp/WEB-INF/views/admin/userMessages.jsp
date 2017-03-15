<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 10.03.2017
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row-fluid">
                       <!-- block -->
                       <div class="block">
                           <div class="navbar navbar-inner block-header">
                               <div class="muted pull-left">Send messages</div>
                           </div>
                           <div class="block-content collapse in">
                               <div class="span12">
                                   <%--<form class="form-horizontal">--%>
                                     <fieldset>
                                       <%--<legend></legend>--%>

                                         <form method="post" action="/admin/sendMessage" enctype="multipart/form-data">

                                       <div class="control-group">
                                         <label class="control-label" for="typeahead">Enter e-mail address</label>
                                         <div class="controls">
                                           <input type="text" name="address" class="span6" id="typeahead"  data-provide="typeahead" data-items="4" >
                                           <p class="help-block">Or select bulk malling</p>
                                         </div>
                                       </div>
                                       <div class="control-group">
                                         <label class="control-label" for="date01">Subject</label>
                                         <div class="controls">
                                           <input type="text" name="theme" class="input-xlarge datepicker" id="date01" >
                                           <%--<p class="help-block">In addition to freeform text, any HTML5 text-based input appears like so.</p>--%>
                                         </div>
                                       </div>

                                       <div class="control-group">
                                         <label class="control-label" for="select01">Bulk malling</label>
                                         <div class="controls">
                                           <select id="select01" class="chzn-select" name="select">
                                             <option></option>
                                             <option>ALL_USERS</option>
                                             <option>ITEM_OWNERS</option>
                                         </select>
                                         </div>
                                       </div>

                                       <div class="control-group">
                                         <label class="control-label" for="fileInput">Attach file</label>
                                         <div class="controls">
                                           <input class="input-file uniform_on" id="fileInput" type="file" name="file">
                                         </div>
                                       </div>
                                       <div class="control-group">
                                         <label class="control-label" >Enter message</label>
                                         <div class="controls">
                                           <textarea name="textarea" class="input-xlarge textarea" placeholder="Enter text ..." style="width: 810px; height: 200px"></textarea>
                                         </div>
                                       </div>
                                       <div class="form-actions">
                                         <button type="submit" class="btn btn-primary">Send message</button>
                                         <button type="reset" class="btn">Cancel</button>
                                       </div>

                                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                     </form>
                                     </fieldset>
                                   <%--</form>--%>

                               </div>
                           </div>
                       </div>
                       <!-- /block -->
                   </div>

