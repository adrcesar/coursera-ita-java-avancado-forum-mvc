package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Topico;


import Model.Usuario;
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
@WebServlet(urlPatterns = {"/insereTopico"})
public class insereTopicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String conteudo = request.getParameter("conteudo");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        Topico t = new Topico();
        t.setId(1);
        t.setTitulo(titulo);
        t.setConteudo(conteudo);
        t.setUsuario(usuario);
        
        
        try {
            TopicoDAO topicoDAO = new TopicoDAO();
            topicoDAO.insert(t);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizaPontos(usuario, "topico");
            
            List<Topico> topicos = topicoDAO.selectAll();
            request.setAttribute("topicos", topicos);
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("erroMSG", ex.getMessage());
            request.setAttribute("pagina", "insereTopico.jsp");
            request.getRequestDispatcher("falha.jsp").forward(request, response);
        }
        
        
        
    }

    

}
