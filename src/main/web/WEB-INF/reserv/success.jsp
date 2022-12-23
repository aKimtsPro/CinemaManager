<%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 21-12-22
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <%@include file="/WEB-INF/parts/style.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/parts/header.jsp"%>

<div class="container">
  <h1 class="display-1">Réservation réussie!</h1>
  <a href="<%= request.getContextPath() %>/reservation"><button class="btn btn-info">Voir mes réservations</button></a>
</div>

<%@ include file="/WEB-INF/parts/footer.jsp"%>

<%@include file="/WEB-INF/parts/script.jsp"%>
</body>
</html>

