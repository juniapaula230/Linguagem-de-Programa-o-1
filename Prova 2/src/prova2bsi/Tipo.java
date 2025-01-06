package prova2bsi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 0040838
 */
public class Tipo {

    static Tipo getTipoById(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getObjetoPorId(int id) {
        Tipo t = new Tipo();
        try {
             Conexao con = new Conexao();

        String sql = "SELECT * FROM Tipo WHERE id = ?";

        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            t = new Tipo();
            t.setId(rs.getInt("id"));
            t.setNome(rs.getString("nome"));
        }
      con.fecharConexao();
    } catch (Exception ex) {
    }
        return t;
    }

    public List<Tipo> getTodos() {
        List<Tipo> lista = new ArrayList<>();
        try {
            Conexao con = new Conexao();

            String sql = "SELECT * FROM Tipo";

            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Tipo tipo = new Tipo();
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                lista.add(tipo);
            }

            con.fecharConexao();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
    
    

}
