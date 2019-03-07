package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Topico;
import Model.Usuario;
import Session.TopicoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADRIANO
 */
@WebServlet(urlPatterns = {"/exibeTodosTopicos"})
public class exibeTodosTopicosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TopicoDAO topico = new TopicoDAO();
            List<Topico> topicos = topico.selectAll();
            request.setAttribute("topicos", topicos);
            request.setAttribute("exibeTopicos", "usuario");
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erro","S");
            request.setAttribute("erroMSG", ex.getMessage());
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        }
    }

    

}
