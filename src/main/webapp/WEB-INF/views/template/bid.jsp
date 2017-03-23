<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="home" value="/" scope="request" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>


        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Item:</th>
                        <th>Your bid:</th>
                        <td>Date: </td>
                    </tr>
                </thead>
                <tbody>
                     <c:forEach items="${bids}" var="bid">
                        <tr>
                            <td>
                             <a href="/showItem/${bid.item.idItems}">${bid.item.name}</a>
                            </td>
                            <td>
                                ${bid.bid}
                             </td>
                            <td>
                                <c:set var="date" value="${bid.bidDate}"/>
                                <fmt:formatDate type="both" value="${date}"/>
                            </td>

                        </tr>


                     </c:forEach>
              </tbody>
            </table>



        </div>

