package be.bstorm.akimts.servlet;

import be.bstorm.akimts.data.impl.FilmDAO;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FilmServlet", value = "/film")
public class FilmServlet extends HttpServlet {

    private final FilmDAO filmDAO = FilmDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("films", filmDAO.getAll());
        request.getRequestDispatcher("/WEB-INF/film/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
