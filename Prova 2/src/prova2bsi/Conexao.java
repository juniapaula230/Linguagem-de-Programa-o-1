package prova2bsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author fabio.martins
 */
public class Conexao {

    private String url = "jdbc:postgresql://localhost:5432/aulabsi";
    private String usuario = "postgres";
    private String senha = "root";
    private Connection con = null;

    public Conexao() throws Exception {
        carregarDrive();
    }

    public Connection getConexao() throws Exception {

        try {
            if (con == null) {
                Properties prop = new Properties();
                prop.put("user", usuario);
                prop.put("password", senha);
                con = DriverManager.getConnection(url, prop);
            }
            return con;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private void carregarDrive() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Conexao con = new Conexao();
            Connection c = con.getConexao();
            System.out.println(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void fecharConexao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
