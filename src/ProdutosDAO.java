
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    private Connection conn;
    private conectaDAO conectdadao;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conectdadao = new conectaDAO();
        this.conn = conectdadao.connectDB();

    }

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome,valor) VALUES(?,?)";

        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
//            prep.setString(3, produto.getStatus());

            prep.execute();

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel cadastrar no banco " + sqle.getMessage());
        }

        //conn = new conectaDAO().connectDB();
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }
}
