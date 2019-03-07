package Session;

import Model.PontosEventos;
import Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO extends ForumDAO<Usuario> {

    private Connection conexao = null;

    public UsuarioDAO() {
        this.conexao = conexao();
    }

    @Override
    public void insert(Object u) throws Exception {

        Usuario user = (Usuario) u;
        String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = conexao.prepareStatement(sql);
        System.out.println(u.toString());
        stm.setString(1, user.getLogin());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getNome());
        stm.setString(4, user.getSenha());
        stm.setInt(5, user.getPontos());
        stm.executeUpdate();

    }

    @Override
    public void update(Object u) throws Exception {

        Usuario user = (Usuario) u;
        String sql = "update usuario "
                + "set nome  = ?, "
                + "email  = ?, "
                + "senha  = ?, "
                + "pontos = ? "
                + "where login = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, user.getNome());
        stm.setString(2, user.getEmail());
        stm.setString(3, user.getSenha());
        stm.setInt(4, user.getPontos());
        stm.setString(5, user.getLogin());
        stm.executeUpdate();

    }

    @Override
    public void delete(Object d) throws Exception {

        Usuario user = (Usuario) d;
        String sql = "delete usuario where login = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, user.getLogin());
        stm.executeUpdate();

    }

    @Override
    public Usuario selectID(Object id) throws Exception {

        String sql = "select login, nome, email, senha, pontos from usuario where login = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        String login = (String) id;
        stm.setString(1, login);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setLogin(rs.getString("login"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setPontos(rs.getInt("pontos"));
            return u;
        } else {
            throw new Exception("Login não cadastrado!");
        }

    }

    @Override
    public List<Usuario> selectAll() throws Exception {
        String sql = "select login, nome, email, senha, pontos from usuario";
        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setLogin(rs.getString("login"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setPontos(rs.getInt("pontos"));
            usuarios.add(u);
        }
        return usuarios;
    }

    public Usuario autenticar(String login, String senha) throws Exception {
        Usuario u = (Usuario) selectID(login);
        if (u.getSenha().equals(senha)) {
            return u;
        } else {
            throw new Exception("Login não encontrado");
        }
    }

    public List<Usuario> ranking() throws SQLException, Exception {
        List<Usuario> todos = new ArrayList<>();

        String sql = "select * from usuario order by pontos desc";
        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setLogin(rs.getString("login"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setPontos(rs.getInt("pontos"));
            todos.add(u);
        }
        return todos;
    }

    public void atualizaPontos(Usuario u, String tipo) throws Exception {

        int pontos;
        if ("topico".equals(tipo)) {
            pontos = 10;
        } else if ("comentario".equals(tipo)) {
            pontos = 3;
        } else {
            throw new Exception("Login não encontrado");
        }

        u.setPontos(u.getPontos() + pontos);
        this.update(u);
    }

}
