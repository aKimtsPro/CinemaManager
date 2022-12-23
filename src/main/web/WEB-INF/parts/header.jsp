<%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 20-12-22
  Time: 01:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">CinéManager</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>">accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/films">films</a>
                </li>
                <% if( session == null || session.getAttribute("username") == null ) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/login">connexion</a>
                    </li>
                <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">déconnexion</a>
                    </li>
                    <% if( session.getAttribute("role").equals("CUSTOMER") ) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/reservation">mes réservations</a>
                    </li>
                    <% } %>
                <% } %>
            </ul>
        </div>
    </div>
</nav>