/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Comentario;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADRIANO
 */
public class ComentarioDAO extends ForumDAO<Comentario> {

    Connection conexao = null;

    public ComentarioDAO() {
        this.conexao = conexao();
    }

    @Override
    public void insert(Object i) throws Exception {
        String sql = "INSERT INTO public.comentario(comentario, login, id_topico) VALUES (?, ?, ? )";
        PreparedStatement stm = conexao.prepareStatement(sql);
        Comentario comentario = (Comentario) i;
        stm.setString(1, comentario.getComentario());
        stm.setString(2, comentario.getUsuario().getLogin());
        stm.setInt(3, comentario.getTopico().getId());

        stm.executeUpdate();

    }

    @Override
    public void update(Object u) throws Exception {
        String sql = "update comentario "
                + "set comentario  = ?, "
                + "login  = ?, "
                + "id_topico  = ? "
                + "where id_comentario = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        Comentario comentario = (Comentario) u;
        stm.setString(1, comentario.getComentario());
        stm.setString(2, comentario.getUsuario().getLogin());
        stm.setInt(3, comentario.getTopico().getId());
        stm.setInt(4, comentario.getId());
        stm.executeUpdate();

    }

    @Override
    public void delete(Object d) throws Exception {

        String sql = "delete from comentario where id_comentario = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        Comentario comentario = (Comentario) d;
        stm.setInt(1, comentario.getId());
        stm.executeUpdate();

    }

    @Override
    public Comentario selectID(Object id) throws Exception {

        String sql = "select id_comentario, comentario, login, id_topico from comentario where id_comentario = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        int comentarioId = (int) id;
        stm.setInt(1, comentarioId);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {

            Comentario m = new Comentario();
            m.setId(rs.getInt("id_comentario"));
            m.setComentario(rs.getString("comentario"));

            UsuarioDAO u = new UsuarioDAO();
            m.setUsuario(u.selectID(rs.getString("login")));

            TopicoDAO t = new TopicoDAO();
            m.setTopico(t.selectID(rs.getInt("id_topico")));

            return m;
        } else {
            throw new Exception("Comentario não encontrado!");
        }

    }

    @Override
    public List<Comentario> selectAll() throws Exception {

        String sql = "select id_comentario, comentario, login, id_topico from comentario";
        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Comentario> comentarios = new ArrayList<>();
        while (rs.next()) {

            Comentario m = new Comentario();
            m.setId(rs.getInt("id_comentario"));
            m.setComentario(rs.getString("comentario"));

            UsuarioDAO u = new UsuarioDAO();
            m.setUsuario(u.selectID(rs.getString("login")));

            TopicoDAO t = new TopicoDAO();
            m.setTopico(t.selectID(rs.getInt("id_topico")));

            comentarios.add(m);
        }
        return comentarios;

    }

    public List<Comentario> selectComentariosTopico(int idTopico) throws Exception {
        List<Comentario> todos = new ArrayList<>();

        String sql = "SELECT id_comentario, comentario, login, id_topico FROM comentario where id_topico = ? order by id_comentario";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, idTopico);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Comentario com = new Comentario();
            com.setId(rs.getInt("id_comentario"));
            com.setComentario(rs.getString("comentario"));

            UsuarioDAO u = new UsuarioDAO();
            com.setUsuario(u.selectID(rs.getString("login")));

            TopicoDAO t = new TopicoDAO();
            com.setTopico(t.selectID(rs.getInt("id_topico")));
            todos.add(com);

        }
        return todos;

    }

}
