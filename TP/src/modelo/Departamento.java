package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tp.Conexao;

public class Departamento {
    
  private int id ; 
  private String nome;

  public Departamento(){}

    public Departamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    // Insert function
    public void inserir() throws Exception {
        Conexao con = new Conexao();
        String sql = "INSERT INTO departamento (nome) VALUES (?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, nome);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    
    // Consult function
    public static ArrayList<Departamento> consultar() throws Exception {
        Conexao con = new Conexao();
        String sql = "SELECT * FROM departamento;";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ArrayList<Departamento> lista = new ArrayList<>();
            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("id"));
                departamento.setNome(rs.getString("nome"));
                lista.add(departamento);
            }
            return lista;
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return null;
    }

    
   public static ArrayList<Departamento> consultarPorNome(String nome) throws Exception {
    ArrayList<Departamento> lista = new ArrayList<>();
    try {
        Conexao con = new Conexao();
        String sql = "SELECT * FROM departamento WHERE nome like ?;"; // Using parameterized statement
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setString(1, "%" + nome + "%"); // Setting the parameter
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Departamento departamento = new Departamento();
            departamento.setId(rs.getInt("id"));
            departamento.setNome(rs.getString("nome"));
            lista.add(departamento);
        }
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
    return lista;
}

public static Departamento consultarPorId(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "SELECT * FROM departamento WHERE id = ?;"; // Using parameterized statement
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, id); // Setting the parameter
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Departamento departamento = new Departamento();
            departamento.setId(rs.getInt("id"));
            departamento.setNome(rs.getString("nome"));
            return departamento;
        } else {
            return null;
        }
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
    return null;
}

 public void remover() {
    try {
        Conexao con = new Conexao();
        String sql = "DELETE FROM departamento WHERE id = ?;"; // Using parameterized statement
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, id); // Setting the parameter
        pst.executeUpdate();
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
}

public static void removerPorId(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "DELETE FROM departamento WHERE id = ?;"; // Using parameterized statement
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, id); // Setting the parameter
        pst.executeUpdate();
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
}
   
    
    //falta atualizar 
    
    
}//final
