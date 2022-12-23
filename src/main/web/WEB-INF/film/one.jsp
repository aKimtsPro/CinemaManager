<%@ page import="be.bstorm.akimts.models.entities.Film" %>
<%@ page import="be.bstorm.akimts.models.entities.Realisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="be.bstorm.akimts.models.entities.Projection" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="be.bstorm.akimts.models.entities.Salle" %><%--
  Created by IntelliJ IDEA.
  User: akimt
  Date: 20-12-22
  Time: 01:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Film film = (Film) request.getAttribute("film"); %>
<% List<Projection> projections = (List<Projection>) request.getAttribute("projections"); %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/parts/style.jsp"%>
</head>
<body>

<%@ include file="/WEB-INF/parts/header.jsp"%>

    <div class="container">
        <h1 class="display-1"><%=film.getTitre()%></h1>

        <div>
            <div>
                <p><span>id:</span> <%=film.getId()%></p>
            </div>
            <div>
                <p><span>titre:</span> <%=film.getTitre()%></p>
            </div>
            <div>
                <p><span>rating:</span> <%=film.getRating()%></p>
            </div>
            <div>
                <p><span>durée:</span> <%=film.getDuree()%>min.</p>
            </div>
            <div>
                <p>
                    <span>genre:</span>
                    <%for (String s : film.getGenre()) { %>
                    <%=s%>
                    <% }%>
                </p>
            </div>
            <div>
                <p>
                    <span>réalisateur:</span>
                    <%for (Realisateur realisateur : film.getRealisateurs()) { %>
                    <%=realisateur.getNom()%>
                    <% }%>
                </p>
            </div>
            <div>
                <p>
                    <span>projections:</span>
                </p>

                <% if( session.getAttribute("username") != null && session.getAttribute("role").equals("ADMIN") ) { %>

                    <button id="hide-btn" class="btn btn-success">ajouter</button>
                    <div class="container" id="form-container" hidden>
                        <% DateTimeFormatter heureFormat = DateTimeFormatter.ofPattern("HH:mm"); %>
                        <% List<LocalTime> heures = (List<LocalTime>) request.getAttribute("heures"); %>
                        <% List<Salle> salles = (List<Salle>) request.getAttribute("salles"); %>
                        <form action="<%= request.getContextPath() %>/projection/add" method="post">

                            <input type="hidden" name="film_id" value="<%= request.getParameter("id") %>">
                            <div class="mb-3">
                                <label for="in-date" class="form-label">date</label>
                                <input type="date" name="date" class="form-control" id="in-date">
                            </div>
                            <div class="mb-3">
                                <label for="in-heure" class="form-label">heure</label>
                                <select id="in-heure" name="heure" class="form-select">
                                    <% for (LocalTime heure: heures) { %>
                                    <option value="<%=heure%>"> <%= heure.format(heureFormat) %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="in-salle" class="form-label">salle</label>
                                <select id="in-salle" name="salle_id" class="form-select">
                                    <% for (Salle salle : salles) { %>
                                    <option value="<%=salle.getId()%>"><%= salle.getCinema().getNom() %> - <%= salle.getNumero() %></option>
                                    <% } %>
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>

                        </form>
                    </div>
                    <script src="<%=request.getContextPath()%>/assets/js/hide_form.js"></script>
                <% } %>



                <ul>
                    <% DateTimeFormatter forDate = DateTimeFormatter.ofPattern("eeee dd/MM/yyyy");%>
                    <% DateTimeFormatter forTime = DateTimeFormatter.ofPattern("HH:mm");%>
                    
                    
                    <% for (Projection projection : projections) { %>
                    <li>
                        <%= projection.getHeure().format(forDate) %> -
                        <span class="fw-bold">[<%= projection.getHeure().format(forTime) %>]</span> -
                        SALLE <%= projection.getSalle().getNumero() %>

                        <% if( session.getAttribute("username") != null && session.getAttribute("role").equals("CUSTOMER") ) { %>

                        <form action="<%= request.getContextPath() %>/reservation" method="post">
                            <input type="hidden" name="projection_id" value="<%= projection.getId() %>">
                            <button type="submit" class="btn btn-primary">réserver</button>
                        </form>

                        <script src="<%=request.getContextPath()%>/assets/js/hide_form.js"></script>
                        <% } %>
                    </li>
                    <% }%>
                </ul>
            </div>
        </div>
    </div>





    <%@ include file="/WEB-INF/parts/footer.jsp"%>

    <%@include file="/WEB-INF/parts/script.jsp"%>
</body>
</html>
