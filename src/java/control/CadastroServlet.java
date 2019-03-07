package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Usuario;
import Session.UsuarioDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADRIANO
 */
@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");

        Usuario u = new Usuario();
        u.setLogin(login);
        u.setEmail(email);
        u.setNome(nome);
        u.setSenha(senha);
        u.setPontos(0);

        try {
            UsuarioDAO ud = new UsuarioDAO();
            ud.insert(u);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("pagina", "cadastro.jsp");
            request.getRequestDispatcher("falha.jsp").forward(request, response);
        }

    }

}
