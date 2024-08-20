
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectaDAO {

    public Connection connectDB() {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/leiloes_td_sat",
                    "root",
                    "285072Gaba#"
            );
            System.out.println("Conexao realizada");
            return conn;

        } catch (SQLException sqle) {
            System.out.println("Erro ao conectar " + sqle.getMessage());
            return null;
        }
    }

}
