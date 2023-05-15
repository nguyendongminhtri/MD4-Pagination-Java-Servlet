<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page='../../bootstrap/nav-bar.jsp'>
    <jsp:param name="articleId" value=""/>
</jsp:include>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12" style="margin-top: 20px; margin-bottom: 20px">
            <a href="/category?action=create">Create Category</a>
            <table class="table">
                <tr>
                    <th>STT</th>
                    <th>NAME</th>
                    <th>AVATAR</th>
                </tr>
                <c:forEach items="${requestScope['listCategory']}" var="ctg">
                    <tr>
                        <td>${ctg.getId()}</td>
                        <td>${ctg.getName()}</td>
                        <td><img width="200" height="200" src="${ctg.getAvatar()}" alt=""></td>
                    </tr>
                </c:forEach>
            </table>

            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pageNumber != 1}">
                        <li class="page-item"><a class="page-link" href="/category?action=page&page=${pageNumber-1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${sumOfPage}" var="i">
                        <c:choose>
                            <c:when test="${pageNumber eq i}">
                                <li class="page-item"><a style="color: red" class="page-link" href="/category?action=page&page=${i}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a  class="page-link" href="/category?action=page&page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pageNumber lt sumOfPage}">
                        <li class="page-item"><a href="/category?action=page&page=${pageNumber + 1}">Next</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>


<%--For displaying Next link --%>

<%--<c:if test="${currentPage lt noOfPages}">--%>
<%--    <a href="/category?action=page&page=${currentPage + 1}">Next2</a>--%>
<%--</c:if>--%>

</body>
</html>
