
import java.util.List;
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

            prep.execute();

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel cadastrar no banco " + sqle.getMessage());
        }

        //conn = new conectaDAO().connectDB();
    }

    public List<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";
        List<ProdutosDTO> produtos = new ArrayList<>();

        try {
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                produtos.add(produto);

            }

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel conectar ao banco " + sqle.getMessage());
        }

        return produtos;
    }

    public List<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status='vendido' ";
        List<ProdutosDTO> produtos = new ArrayList<>();

        try {

            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                produtos.add(produto);

            }

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel conectar ao banco " + sqle.getMessage());

        }
        return produtos;

    }

    public void venderProduto(ProdutosDTO produto) {

        String sql = "UPDATE PRODUTOS SET status=? where id=?";

        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);

            prep.setString(1, produto.getStatus());
            prep.setInt(2, produto.getId());

            prep.execute();

        } catch (SQLException sqle) {
            System.out.println("Não foi possivel atualizar no banco " + sqle.getMessage());
        }

    }
}
