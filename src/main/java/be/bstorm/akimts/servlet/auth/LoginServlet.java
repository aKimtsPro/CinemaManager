package be.bstorm.akimts.servlet.auth;

import be.bstorm.akimts.models.dto.UtilisateurDTO;
import be.bstorm.akimts.service.impl.AuthServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final AuthServiceImpl authService = AuthServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if( session.getAttribute("username") != null ){
            response.sendError(403, "déjà connecté");
        }
        else {
            request.getRequestDispatcher("/WEB-INF/auth/connexion.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if( session.getAttribute("username") != null ){
            response.sendError(403, "déjà connecté");
        }
        else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if( authService.validateCredentials(username, password) ) {
                session = request.getSession();
                UtilisateurDTO dto = authService.findByUsername(username)
                        .orElseThrow();
                session.setAttribute( "username", username );
                session.setAttribute( "role", dto.getRole() );
                session.setAttribute( "user_id", dto.getId() );
                System.out.println("connecté");
            }
            response.sendRedirect( request.getContextPath() );
        }
    }
}
