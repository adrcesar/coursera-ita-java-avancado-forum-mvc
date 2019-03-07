package control;

import Model.Topico;
import Session.TopicoDAO;
import Session.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        try {
            UsuarioDAO userDAO = new UsuarioDAO();
            request.getSession().setAttribute("usuario", userDAO.autenticar(login, senha));
            
            TopicoDAO topicoDAO = new TopicoDAO();
            List<Topico> topicos = (List<Topico>) topicoDAO.selectAll();
            request.setAttribute("topicos", topicos);
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getSession().removeAttribute("usuario");
            request.setAttribute("erro","S");
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("login", login);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
