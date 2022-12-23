package be.bstorm.akimts.servlet.projection;

import be.bstorm.akimts.data.impl.HoraireDAO;
import be.bstorm.akimts.data.impl.ProjectionDAO;
import be.bstorm.akimts.data.impl.SalleDAO;
import be.bstorm.akimts.models.entities.Salle;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "ProjCreateServlet", value = "/projection/add")
public class ProjCreateServlet extends HttpServlet {

    private final ProjectionDAO projectionDAO = ProjectionDAO.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalDateTime heure = date.atTime( LocalTime.parse(request.getParameter("heure")) );
        long salleId = Long.parseLong( request.getParameter("salle_id") );
        long filmId = Long.parseLong( request.getParameter("film_id") );

        projectionDAO.createProjection(heure, salleId, filmId);

        response.sendRedirect(request.getContextPath()+"/film?id="+filmId);
    }
}
