<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/13/2023
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
  <jsp:include page="../upload/upload-avatar.jsp">
    <jsp:param name="articleId" value=""/>
  </jsp:include>
  <button type="submit">Upload</button>
</form>
</body>
</html>
