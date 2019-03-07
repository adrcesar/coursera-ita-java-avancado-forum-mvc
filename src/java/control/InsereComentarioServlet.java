package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Comentario;
import Model.Topico;
import Model.Usuario;
import Session.ComentarioDAO;
import Session.TopicoDAO;
import Session.UsuarioDAO;
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
@WebServlet(urlPatterns = {"/insereComentario"})
public class InsereComentarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String comentario = request.getParameter("comentario");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String idTopicoStr = request.getParameter("idTopico");
        int idTopico = Integer.parseInt(idTopicoStr);
        
        
        
        try {
            Comentario c = new Comentario();
            c.setComentario(comentario);

            Topico t;
            TopicoDAO topicoDAO = new TopicoDAO();
            t = topicoDAO.selectID(idTopico);
            c.setTopico(t);

            c.setUsuario(usuario);
            
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.insert(c);
            
            List<Comentario> comentarios = comentarioDAO.selectComentariosTopico(idTopico);
            
            // response.getWriter().print("<html><h1>Oi "+usuario.getLogin()+"</h1></html>");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizaPontos(usuario, "comentario");
            
            request.setAttribute("topico", t);
            request.setAttribute("comentarios", comentarios);
            request.getRequestDispatcher("exibeTopico.jsp").forward(request, response);
            
            //response.getWriter().print("<html><h1>Oi "+c+"</h1></html>");
            //response.getWriter().print("<html><h1>Oi "+comentarios.get(1).getComentario()+"</h1></html>");
            //response.getWriter().print("<html><h1>Oi "+comentarios.get(5).getComentario()+"</h1></html>");

        } catch (Exception ex) {
            //response.getWriter().print("<html><h1>Oi "+ex.getMessage()+"</h1></html>");
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("pagina", "exibeTopico.jsp");
            request.getRequestDispatcher("falha.jsp").forward(request, response);
        }
    }

    

}
