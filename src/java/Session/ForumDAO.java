package Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ForumDAO<T> {

    private static Connection connection;
    private static String driver = "jdbc:postgresql://localhost/coursera";
    private static String usuario = "postgres";
    private static String senha = "PTacf4994";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static  Connection conexao() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(ForumDAO.driver, ForumDAO.usuario, ForumDAO.senha);

            } catch (SQLException ex) {
                Logger.getLogger(ForumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return connection;
    }

    public abstract void insert(Object i) throws Exception;

    public abstract void update(Object u) throws Exception;

    public abstract void delete(Object d) throws Exception;

    public abstract T selectID(Object id) throws Exception;

    public abstract List<T> selectAll() throws Exception;
}
