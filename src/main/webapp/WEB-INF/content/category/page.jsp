<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/15/2023
  Time: 5:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page='../../bootstrap/nav-bar.jsp'>
  <jsp:param name="articleId" value=""/>
</jsp:include>
<head>
    <title>Title</title>
  <style>
    .colum {
      width: 33.3%;
      padding: 10px 10px;
    }
    .card {
      box-shadow: 10px 10px 2px 1px rgba(230, 0, 255, 0.97);
      background-color: aqua;
    }
    div.card {
      padding: 10px;
    }
    img {
      width: auto;
      height: 200px;
      border-radius: 15%;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="col-12" style="margin-top: 20px">
    <div class="row">
      <c:forEach items="${listCategory}" var="ctm">
        <div class="colum">
          <div class="card">
            <img  src="${ctm.getAvatar()}" alt="">
            <b>${ctm.getName()}</b>
          </div>
        </div>
      </c:forEach>
    </div>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <c:if test="${pageNumber != 1}">
          <li class="page-item"><a class="page-link" href="/category?action=page_grid&page=${pageNumber-1}">Previous</a>
          </li>
        </c:if>
        <c:forEach begin="1" end="${sumOfPage}" var="i" varStatus="red">
          <c:choose>
            <c:when test="${pageNumber eq i}">
              <li class="page-item"><a style="color: red" class="page-link" href="/category?action=page_grid&page=${i}">${i}</a></li>
            </c:when>
            <c:otherwise>
              <li class="page-item"><a  class="page-link" href="/category?action=page_grid&page=${i}">${i}</a></li>
            </c:otherwise>
          </c:choose>

        </c:forEach>
        <c:if test="${pageNumber lt sumOfPage}">
          <li class="page-item"><a href="/category?action=page_gid&page=${pageNumber + 1}">Next</a></li>
        </c:if>
      </ul>
    </nav>
  </div>
</div>

</body>
</html>
