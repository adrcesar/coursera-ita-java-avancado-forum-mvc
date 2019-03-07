
import Model.Usuario;
import Session.UsuarioDAO;
import java.util.List;
import static org.junit.Assert.assertEquals;

//import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteUsuarioDAO {

    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/coursera", "postgres",
                "PTacf4994");
        
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        
        IDataSet dataSetDeleteComentario = loader.load("/comentario.xml");
        jdt.setDataSet(dataSetDeleteComentario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();
        
        IDataSet dataSetDeleteTopico = loader.load("/topico.xml");
        jdt.setDataSet(dataSetDeleteComentario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();
        
        IDataSet dataSetDeleteUsuario = loader.load("/usuario.xml");
        jdt.setDataSet(dataSetDeleteUsuario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();
        
        jdt.setDataSet(loader.load("/usuario.xml"));
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();
        
        
        
    }

    @Test
    public void inserirUsuario() throws Exception {
        Usuario u = new Usuario();
        u.setLogin("fernanda");
        u.setNome("FERNANDA DA SILVA");
        u.setEmail("fernanda@gmail.com");
        u.setSenha("12345");
        u.setPontos(10);
        UsuarioDAO ub = new UsuarioDAO();
        ub.insert(u);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("usuario");
        
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/usuarioVerifica.xml");
        
        ITable expectedTable = expectedDataset.getTable("usuario");
        Assertion.assertEquals(expectedTable, currentTable);
        
    }

    @Test
    public void recuperaLogin() throws Exception {
        UsuarioDAO login = new UsuarioDAO();
        Usuario u;
        u = login.selectID("pedro");
        assertEquals("PEDRO DA SILVA", u.getNome());
    }

    @Test
    public void atualizarPontos() throws Exception {
        UsuarioDAO login = new UsuarioDAO();
        Usuario u;
        u = login.selectID("joao");
        login.atualizaPontos(u, "topico");
        assertEquals(10, u.getPontos());
        login.atualizaPontos(u, "comentario");
        assertEquals(13, u.getPontos());
    }

    @Test
    public void ranking() throws Exception {
        UsuarioDAO login = new UsuarioDAO();

        Usuario u1;
        u1 = login.selectID("joao");
        login.atualizaPontos(u1, "topico");

        Usuario u2;
        u2 = login.selectID("pedro");
        login.atualizaPontos(u2, "comentario");

        List<Usuario> usuarios = login.ranking();
  
        assertEquals(10, usuarios.get(0).getPontos());
        assertEquals(3, usuarios.get(1).getPontos());
    }

    @Test
    public void autenticar() throws Exception {
        UsuarioDAO login = new UsuarioDAO();
        Usuario u1;
        u1 = login.autenticar("joao", "12345");
        assertEquals("joao", u1.getLogin());
    }

    @After
    public void destroy() throws Exception {
        FlatXmlDataFileLoader loaderDelete = new FlatXmlDataFileLoader();
        
        IDataSet dataSetDeleteComentario = loaderDelete.load("/comentario.xml");
        jdt.setDataSet(dataSetDeleteComentario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();

        IDataSet dataSetDeleteTopico = loaderDelete.load("/topico.xml");
        jdt.setDataSet(dataSetDeleteTopico);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();

        IDataSet dataSetDeleteUsuario = loaderDelete.load("/usuario.xml");
        jdt.setDataSet(dataSetDeleteUsuario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();
    }

}
