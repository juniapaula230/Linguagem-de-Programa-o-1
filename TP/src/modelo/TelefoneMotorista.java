package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import tp.Conexao;

public class TelefoneMotorista {
    
  private int id;//colocar a mesma variavel do id motorista aqui no java
  private String telefone_fixo;
  private String celular;
  private int motorista_id;//colocar a mesma variavel do id motorista aqui no java

  public TelefoneMotorista(){
  }
  
    public TelefoneMotorista(int id, String telefone_fixo, String celular, int motorista_id) {
        this.id = id;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.motorista_id = motorista_id;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone_fixo() {
        return telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
  
  public void Inserir() throws Exception {
    Conexao con = new Conexao();
    String sql = "insert into telefone_motorista (telefone_fixo, celular, motorista_id) values (?, ?, ?);";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setString(1, getTelefone_fixo());
        pst.setString(2, getCelular());
        pst.setInt(3, getMotorista_id());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Telefone inserido com sucesso!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
  
  
  
  public TelefoneMotorista ConsultarPorNome(String nome) throws Exception {
    Conexao con = new Conexao();
    String sql = "Select id, telefone_fixo, celular, motorista_id from telefone_motorista where motorista_id in (select id from motorista where nome like ?);";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setString(1, "%" + nome + "%");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            TelefoneMotorista telefoneMotorista = new TelefoneMotorista();
            telefoneMotorista.setId(rs.getInt("id"));
            telefoneMotorista.setTelefone_fixo(rs.getString("telefone_fixo"));
            telefoneMotorista.setCelular(rs.getString("celular"));
            telefoneMotorista.setMotorista_id(rs.getInt("motorista_id"));
            return telefoneMotorista;
        } else {
            return null;
        }
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
    return null;
}

public TelefoneMotorista ConsultarPorId(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "Select id, telefone_fixo, celular, motorista_id from telefone_motorista where id = " + id + ";";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            TelefoneMotorista telefoneMotorista = new TelefoneMotorista();
            telefoneMotorista.setId(rs.getInt("id"));
            telefoneMotorista.setTelefone_fixo(rs.getString("telefone_fixo"));
            telefoneMotorista.setCelular(rs.getString("celular"));
            telefoneMotorista.setMotorista_id(rs.getInt("motorista_id"));
            return telefoneMotorista;
        } else {
            return null;
        }
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
    return null;
}

  
  public void Remover() throws Exception {
    Conexao con = new Conexao();
    String sql = "delete from telefone_motorista where id = " + getId() + ";";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Telefone removido com sucesso!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}

public void RemoverById(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "delete from telefone_motorista where id = " + id + ";";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Telefone removido com sucesso!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
 //faltando a atualizar

}//final
