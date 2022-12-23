<%@ page import="java.util.List" %>
<%@ page import="be.bstorm.akimts.models.entities.Film" %><%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 20-12-22
  Time: 01:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<% List<Film> films = (List<Film>) request.getAttribute("films"); %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/parts/style.jsp"%>
</head>
<body>

    <%@ include file="/WEB-INF/parts/header.jsp"%>

    <div class="container">
        <h1 class="display-1">Films</h1>

        <ul>
            <% for (Film film : films) { %>
            <li>    <a href="<%= request.getContextPath() %>/film?id=<%=film.getId()%>"><%= film.getTitre() %></a> </li>
            <% } %>
        </ul>
    </div>

    <%@ include file="/WEB-INF/parts/footer.jsp"%>

    <%@include file="/WEB-INF/parts/script.jsp"%>
</body>
</html>
