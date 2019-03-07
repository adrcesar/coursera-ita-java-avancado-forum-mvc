package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Usuario;
import Session.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADRIANO
 */
@WebServlet(urlPatterns = {"/ranking"})
public class rankingServlet extends HttpServlet {

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
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<Usuario> ranking = usuarioDAO.ranking();
            request.setAttribute("ranking", ranking);
            request.getRequestDispatcher("ranking.jsp").forward(request, response);
        } catch (IOException | ServletException ex) {
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("pagina", "topicos.jsp");
            request.getRequestDispatcher("falha.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(rankingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(rankingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
