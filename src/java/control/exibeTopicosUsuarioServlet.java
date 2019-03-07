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
@WebServlet(urlPatterns = {"/exibeTopicosUsuario"})
public class exibeTopicosUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        try {
            TopicoDAO topicoDAO = new TopicoDAO();
            List<Topico> topicos = topicoDAO.selectTopicosUsuario(usuario.getLogin());
            request.setAttribute("topicos", topicos);
            request.setAttribute("exibeTopicos", "todos");
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erro","S");
            request.setAttribute("erroMSG", ex.getMessage());
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        }
    }

    

}
