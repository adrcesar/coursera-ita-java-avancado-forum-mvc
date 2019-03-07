
import Model.Topico;
import Model.Usuario;
import Session.ForumDAO;
import Session.TopicoDAO;
import Session.UsuarioDAO;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.assertEquals;

//import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteTopicoDAO {

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
        jdt.setDataSet(dataSetDeleteTopico);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();

        IDataSet dataSetDeleteUsuario = loader.load("/usuario.xml");
        jdt.setDataSet(dataSetDeleteUsuario);
        jdt.setSetUpOperation(DatabaseOperation.DELETE_ALL);
        jdt.onSetup();
        
        IDatabaseConnection connection = jdt.getConnection();
        Statement statement = connection.getConnection().createStatement();
        statement.execute("alter sequence topico_id_topico_seq RESTART WITH 5");

        IDataSet dataSetInsereUsuario = loader.load("/usuario.xml");
        jdt.setDataSet(dataSetInsereUsuario);
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();

        IDataSet dataSetInsereTopico = loader.load("/topico.xml");
        jdt.setDataSet(dataSetInsereTopico);
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();

    }

    @Test
    public void inserirTopicoESelecionarTodos() throws Exception {

        TopicoDAO tDAO = new TopicoDAO();

        List<Topico> topicos = tDAO.selectAll();
        int qtdTopicosAnterior = topicos.size();

        UsuarioDAO login = new UsuarioDAO();
        Usuario u = login.selectID("pedro");

        Topico t = new Topico();
        t.setId(3);
        t.setTitulo("outro topico do pedro");
        t.setConteudo("topico do pedro");
        t.setUsuario(u);

        tDAO.insert(t);

        topicos = tDAO.selectAll();

        assertEquals(qtdTopicosAnterior + 1, topicos.size());
    }
    
    @Test
    public void inserirTopicoXML() throws Exception {

        UsuarioDAO login = new UsuarioDAO();
        Usuario u = login.selectID("pedro");

        Topico t = new Topico();
        t.setTitulo("outro topico do pedro");
        t.setConteudo("topico do pedro");
        t.setUsuario(u);

        TopicoDAO tDAO = new TopicoDAO();
        tDAO.insert(t);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("topico");

        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/topicoVerifica.xml");
        ITable expectedTable = expectedDataset.getTable("topico");

        Assertion.assertEquals(expectedTable, currentTable);

        
    }

    @Test
    public void alterarTopicoESelectId() throws Exception {
        
        TopicoDAO tDAO = new TopicoDAO();
        
        Topico topico = tDAO.selectID(1);
        
        topico.setConteudo(topico.getConteudo() + " alteracao");
        tDAO.update(topico);
        
        Topico topicoAlterado = tDAO.selectID(1);
        
        
        assertEquals(topico.getConteudo(), topicoAlterado.getConteudo());
    }
    
    @Test
    public void selecionarTopicosDoUsuario() throws Exception {

        TopicoDAO tDAO = new TopicoDAO();

        List<Topico> topicosUsuario = tDAO.selectTopicosUsuario("pedro");

        topicosUsuario.forEach((t) -> {
            assertEquals("pedro", t.getUsuario().getLogin());
        });
        
        assertEquals(3, topicosUsuario.size());
        
        
            
        
    }
    
    @After
    public void destroy() throws Exception {
       // jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/coursera", "postgres",
             //   "PTacf4994");
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
