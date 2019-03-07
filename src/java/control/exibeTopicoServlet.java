package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Comentario;
import Model.Topico;
import Session.ComentarioDAO;
import Session.TopicoDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/exibeTopico"})
public class exibeTopicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTopicoStr = request.getParameter("idTopico");
        int idTopico = Integer.parseInt(idTopicoStr);
        
        try {
            TopicoDAO td = new TopicoDAO();
            Topico t;
            t = td.selectID(idTopico);
            request.setAttribute("topico", t);
            
            ComentarioDAO com = new ComentarioDAO();
            List<Comentario> comentarios = com.selectComentariosTopico(idTopico);
            request.setAttribute("comentarios", comentarios);
            
            request.getRequestDispatcher("exibeTopico.jsp").forward(request, response);
            //response.getWriter().print("<html><h1>conteudo "+t.getId()+" - "+t.getConteudo()+"</h1></html>");
            //response.getWriter().print("<html><h1>comentario "+comentarios.get(0).getComentario()+"</h1></html>");
        } catch (Exception ex) {
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("pagina", "topicos.jsp");
            request.getRequestDispatcher("falha.jsp").forward(request, response);
            //response.getWriter().print("<html><h1>erro </h1></html>");
        }
        
        
    }

    

}
