package be.bstorm.akimts.servlet.reservation;

import be.bstorm.akimts.data.impl.ReservationDAO;
import be.bstorm.akimts.models.dto.ReservationDTO;
import be.bstorm.akimts.models.entities.Reservation;
import be.bstorm.akimts.models.form.ReservationForm;
import be.bstorm.akimts.service.ReservationService;
import be.bstorm.akimts.service.impl.ReservationServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReservationServlet", value = "/reservation")
public class ReservationServlet extends HttpServlet {

    private final ReservationService reservationService = ReservationServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if( request.getSession().getAttribute("user_id") == null ){
            response.sendError(401, "you should connect");
        } else if ( !request.getSession().getAttribute("role").equals("CUSTOMER") ) {
            response.sendError(403, "you dont have the permission");
        } else {
            List<ReservationDTO> reservations = reservationService.getReservationOfUtilisateur(
                    (String) request.getSession().getAttribute("username")
            );

            request.setAttribute("reservations", reservations);

            request.getRequestDispatcher("/WEB-INF/reserv/my_reserv.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if( request.getSession().getAttribute("user_id") == null ){
            response.sendError(401, "you should connect");
        } else if ( !request.getSession().getAttribute("role").equals("CUSTOMER") ) {
            response.sendError(403, "you dont have the permission");
        } else {
            ReservationForm form = new ReservationForm();

            form.setProjectionId( Long.parseLong(request.getParameter("projection_id")) );
            form.setUtilisateurId( (Long) request.getSession().getAttribute("user_id") );

            reservationService.createReservation( form );

            request.getRequestDispatcher("/WEB-INF/reserv/success.jsp").forward(request, response);
        }

    }
}
