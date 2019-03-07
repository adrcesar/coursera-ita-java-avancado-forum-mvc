
import Model.Comentario;
import Model.Topico;
import Model.Usuario;
import Session.ComentarioDAO;
import Session.TopicoDAO;
import Session.UsuarioDAO;
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

public class TesteComentarioDAO {

    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost/coursera", "postgres",
                "PTacf4994");

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

        IDatabaseConnection connection = jdt.getConnection();
        Statement statement = connection.getConnection().createStatement();
        statement.execute("alter sequence comentario_id_comentario_seq RESTART WITH 5");

        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();

        jdt.setDataSet(loader.load("/usuario.xml"));
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();

        jdt.setDataSet(loader.load("/topico.xml"));
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();

        jdt.setDataSet(loader.load("/comentario.xml"));
        jdt.setSetUpOperation(DatabaseOperation.INSERT);
        jdt.onSetup();
    }

    @Test
    public void inserirComentarioESelecionarTodos() throws Exception {

        TopicoDAO tDAO = new TopicoDAO();
        Topico topicoJoao = tDAO.selectID(2);

        UsuarioDAO login = new UsuarioDAO();
        Usuario usuarioPedro;
        usuarioPedro = login.selectID("pedro");

        Comentario comentario = new Comentario();
        comentario.setComentario("Quarto comentario do pedro no topico do joao");
        comentario.setTopico(topicoJoao);
        comentario.setUsuario(usuarioPedro);

        ComentarioDAO cDAO = new ComentarioDAO();
        List<Comentario> comentarioBefore = cDAO.selectAll();
        cDAO.insert(comentario);
        List<Comentario> comentarioAfter = cDAO.selectAll();

        assertEquals(comentarioBefore.size() + 1, comentarioAfter.size());

    }

    @Test
    public void inserirComentarioXML() throws Exception {

        TopicoDAO tDAO = new TopicoDAO();
        Topico topicoJoao = tDAO.selectID(2);

        UsuarioDAO login = new UsuarioDAO();
        Usuario usuarioPedro;
        usuarioPedro = login.selectID("pedro");

        Comentario comentario = new Comentario();
        comentario.setComentario("Quarto comentario do pedro no topico do joao");
        comentario.setTopico(topicoJoao);
        comentario.setUsuario(usuarioPedro);

        ComentarioDAO cDAO = new ComentarioDAO();
        cDAO.insert(comentario);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("comentario");

        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/comentarioVerifica.xml");

        ITable expectedTable = expectedDataset.getTable("comentario");
        Assertion.assertEquals(expectedTable, currentTable);

    }

    @Test
    public void SelecionarComentariosDeUmTopico() throws Exception {

        ComentarioDAO tDAO = new ComentarioDAO();
        List<Comentario> comentarios = tDAO.selectComentariosTopico(2);

        comentarios.forEach((c) -> {
            assertEquals(2, c.getTopico().getId());
        });

    }

    @Test
    public void SelecionarComentarioPeloId() throws Exception {
        ComentarioDAO cDAO = new ComentarioDAO();
        Comentario comentario = cDAO.selectID(2);
        assertEquals("Primeiro comentario do pedro no topico do joao", comentario.getComentario());
    }

    @Test
    public void UpdateComentario() throws Exception {

        ComentarioDAO cDAO = new ComentarioDAO();
        Comentario comentario = cDAO.selectID(2);
        assertEquals("Primeiro comentario do pedro no topico do joao", comentario.getComentario());

        comentario.setComentario(comentario.getComentario() + " ALTERADO");
        cDAO.update(comentario);
        comentario = cDAO.selectID(2);
        assertEquals("Primeiro comentario do pedro no topico do joao ALTERADO", comentario.getComentario());

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
