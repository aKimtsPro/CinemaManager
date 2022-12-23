<%@ page import="java.util.List" %>
<%@ page import="be.bstorm.akimts.data.impl.ReservationDAO" %>
<%@ page import="be.bstorm.akimts.models.dto.ReservationDTO" %><%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 21-12-22
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<ReservationDTO> reservations = (List<ReservationDTO>) request.getAttribute("reservations"); %>
<html>
<head>
  <title>Title</title>
  <%@include file="/WEB-INF/parts/style.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/parts/header.jsp"%>

<div class="container">
  <h1 class="display-1">Vos réservations</h1>

  <ul>
  <% for (ReservationDTO reservation : reservations) { %>
    <li><%= reservation %></li>
  <% } %>
  </ul>
</div>

<%@ include file="/WEB-INF/parts/footer.jsp"%>

<%@include file="/WEB-INF/parts/script.jsp"%>
</body>
</html>
