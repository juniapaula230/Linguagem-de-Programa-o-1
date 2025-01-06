package prova2bsi;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 0040838
 */
public class Diario {

    private int id;
    private Tipo tipo;
    private String nome;
    private double valor;
    private Date dataDespesa;

    public Diario() {
    }

    public Diario(int id, Tipo tipo, String nome, double valor, Date dataDespesa) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.valor = valor;
        this.dataDespesa = dataDespesa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Date dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public void inserir() throws Exception {
        try {
        Conexao con = new Conexao();
        String sql = "insert into funcionario (codigo, nome, cpf, sexo, data_despesa, ativo) values (?, ?, ?, ?, ?, ?);";
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, getCodigo());
        pst.setString(2, getNome());
        pst.setString(3, getCpf());
        pst.setString(4, getSexo());
        pst.setDate(5, new java.sql.Date(getDataDespesa().getTime()));
        pst.setBoolean(6, isAtivo());
        pst.execute();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}

     public void delete(int identificado) {
        try {
        Conexao con = new Conexao();

        String sql = "DELETE FROM Diario WHERE id = ?";

        PreparedStatement stmt = con.getConexao().prepareStatement(sql);
        stmt.setInt(1, identificado);

        stmt.execute();
        con.fecharConexao();
    } catch (Exception ex) {
    }
}
        
   public List<Diario> getDiarios() {
    ArrayList<Diario> lista = new ArrayList<>();
    try {
        Conexao con = new Conexao();

        String sql = "SELECT * FROM Diario";

        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Diario diario = new Diario();
            diario.setId(rs.getInt("id"));
            diario.setTipo(Tipo.getTipoById(rs.getInt("tipo_id")));
            diario.setNome(rs.getString("nome"));
            diario.setValor(rs.getDouble("valor"));
            diario.setDataDespesa(new Date(rs.getDate("data_despesa").getTime()));
            lista.add(diario);
        }

        con.fecharConexao();
    } catch (Exception ex) {
    }
    return lista;
}

    public Object[] getDadosModel() {
        Object[] retorno = new Object[]{getId(), getNome(), getTipo().getNome(), getDataDespesa(), getValor()};
        return retorno;
    }

    private boolean isAtivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getSexo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getCpf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
