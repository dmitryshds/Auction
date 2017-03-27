<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="home" value="/" scope="request" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>


            <table id="bids-table" cellspacing="0"  class="table table-striped table-bordered" width="100%">
                <thead>
                    <tr>
                        <th>Item:</th>
                        <th>Your bid:</th>
                        <th>Date: </th>
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
                                <fmt:formatDate type="both" value="${bid.bidDate}"/>
                            </td>

                        </tr>


                     </c:forEach>
              </tbody>
            </table>




<script src="<c:url value="/resources/js/adm/jquery-1.9.1.min.js"/>"></script>

<script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>

<script src="<c:url value="/resources/js/dataTables.bootstrap.min.js"/>"></script>
    <script>
        $(document).ready(function() {
            $('#bids-table').DataTable();
        } );
    </script>