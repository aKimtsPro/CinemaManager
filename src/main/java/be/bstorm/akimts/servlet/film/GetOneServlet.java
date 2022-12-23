package be.bstorm.akimts.servlet.film;

import be.bstorm.akimts.data.impl.FilmDAO;
import be.bstorm.akimts.data.impl.HoraireDAO;
import be.bstorm.akimts.data.impl.ProjectionDAO;
import be.bstorm.akimts.data.impl.SalleDAO;
import be.bstorm.akimts.models.entities.Film;
import be.bstorm.akimts.models.entities.Salle;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet(name = "GetOneServlet", value = "/film")
public class GetOneServlet extends HttpServlet {

    private final FilmDAO filmDAO = FilmDAO.getInstance();
    private final ProjectionDAO projectionDAO = ProjectionDAO.getInstance();
    private final HoraireDAO horaireDAO = HoraireDAO.getInstance();
    private final SalleDAO salleDAO = SalleDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String idParam = request.getParameter("id");
            long id = Long.parseLong(idParam);
            Optional<Film> optionalFilm = filmDAO.getOne( id );

            request.setAttribute(
                    "film",
                    optionalFilm.orElseThrow()
            );

            request.setAttribute(
                    "projections",
                    projectionDAO.getAllFuturProjectionForMovie( id )
            );


            List<LocalTime> heures = horaireDAO.getHoraire();
            request.setAttribute("heures", heures);
            List<Salle> salles = salleDAO.getAll();
            request.setAttribute("salles", salles);

            request.getRequestDispatcher("/WEB-INF/film/one.jsp").forward(request,response);
        }
        catch (NoSuchElementException ex){
            response.sendError(404, "no such film");
        }
        catch (Exception ex){
            response.sendError(400, "problem happened: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
