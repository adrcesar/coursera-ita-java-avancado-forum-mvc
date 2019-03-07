/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Topico;
import Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADRIANO
 */
public class TopicoDAO extends ForumDAO<Topico> {

    Connection conexao = null;
    
    public TopicoDAO() {
        this.conexao = conexao();
    }
    
    @Override
    public void insert(Object i) throws Exception {
            Topico t = (Topico) i;
            String sql = "INSERT INTO public.topico(titulo, conteudo, login) VALUES (?, ?, ?)";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, t.getTitulo());
            stm.setString(2, t.getConteudo());
            stm.setString(3, t.getUsuario().getLogin());

            stm.executeUpdate();

        
    }

    @Override
    public void update(Object u) throws Exception {
            String sql = "update public.topico set titulo = ?, conteudo   = ?, login    = ? where id_topico = ?";

            PreparedStatement stm = conexao.prepareStatement(sql);
            Topico topico = (Topico) u;
            stm.setString(1, topico.getTitulo());
            stm.setString(2, topico.getConteudo());
            stm.setString(3, topico.getUsuario().getLogin());
            stm.setInt(4, topico.getId());

            stm.executeUpdate();

        
    }

    @Override
    public void delete(Object d) throws Exception {
        
            String sql = "delete public.topico where login = ? ";
            PreparedStatement stm = conexao.prepareStatement(sql);
            Topico topico = (Topico) d;
            stm.setString(1, topico.getTitulo());
            stm.setString(2, topico.getConteudo());
            stm.setString(3, topico.getUsuario().getLogin());

            stm.executeUpdate();

       
    }

    @Override
    public Topico selectID(Object id) throws Exception {
        
            String sql = "SELECT id_topico, titulo, conteudo, login FROM topico where id_topico = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            int topicoId = (int) id;
            stm.setInt(1, topicoId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Topico t = new Topico();

                t.setId(rs.getInt("id_topico"));
                t.setTitulo(rs.getString("titulo"));
                t.setConteudo(rs.getString("conteudo"));

                UsuarioDAO u = new UsuarioDAO();
                t.setUsuario((Usuario) u.selectID(rs.getString("login")));
                return t;
            } else {
                throw new Exception("Topico nao encontrado!");
            }
        
    }

    @Override
    public List<Topico> selectAll() throws Exception {
        List<Topico> topicos = new ArrayList<>();
        
            String sql = "SELECT id_topico, titulo, conteudo, login FROM topico";
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Topico t = new Topico();
                t.setId(rs.getInt("id_topico"));
                t.setTitulo(rs.getString("titulo"));
                t.setConteudo(rs.getString("conteudo"));

                UsuarioDAO u = new UsuarioDAO();
                t.setUsuario((Usuario) u.selectID(rs.getString("login")));
                topicos.add(t);

            }
            return topicos;
        
    }

    public List<Topico> selectTopicosUsuario(String login) throws Exception {
        List<Topico> todos = new ArrayList<>();

            
            String sql = "SELECT id_topico, titulo, conteudo, login FROM topico where login = ? order by id_topico desc";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topico t = new Topico();
                t.setId(rs.getInt("id_topico"));
                t.setTitulo(rs.getString("titulo"));
                t.setConteudo(rs.getString("conteudo"));

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                t.setUsuario(usuarioDAO.selectID(rs.getString("login")));
                todos.add(t);
            }
            return todos;

        
    }

}
