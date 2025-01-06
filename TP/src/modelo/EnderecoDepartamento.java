package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tp.Conexao;

public class EnderecoDepartamento {

    private int id;//colocar a mesma variavel do id departamento aqui no java
    private String rua;
    private String bairro;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private int departamento_id;//colocar a mesma variavel do id departamento aqui no java

    public EnderecoDepartamento() {
    }

    public EnderecoDepartamento(int id, String rua, String bairro, int numero, String complemento, String cidade, String estado, String cep, int departamento_id) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.departamento_id = departamento_id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void InserirEnderecoDepartamento() throws Exception {
        Conexao con = new Conexao();
        String sql = "insert into endereco_departamento (rua, bairro, numero, complemento, cidade, estado, cep, departamento_id) values (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, getRua());
            pst.setString(2, getBairro());
            pst.setInt(3, getNumero());
            pst.setString(4, getComplemento());
            pst.setString(5, getCidade());
            pst.setString(6, getEstado());
            pst.setString(7, getCep());
            pst.setInt(8, getDepartamento_id());
            pst.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public EnderecoDepartamento ConsultarEnderecoDepartamentoPorCodigo(int codigo) throws Exception {
        Conexao con = new Conexao();
        String sql = "Select * from endereco_departamento where id = " + codigo + " ;";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            EnderecoDepartamento enderecoDepartamento = null;
            while (rs.next()) {
                enderecoDepartamento = new EnderecoDepartamento();
                enderecoDepartamento.setId(rs.getInt("id"));
                enderecoDepartamento.setRua(rs.getString("rua"));
                enderecoDepartamento.setBairro(rs.getString("bairro"));
                enderecoDepartamento.setNumero(rs.getInt("numero"));
                enderecoDepartamento.setComplemento(rs.getString("complemento"));
                enderecoDepartamento.setCidade(rs.getString("cidade"));
                enderecoDepartamento.setEstado(rs.getString("estado"));
                enderecoDepartamento.setCep(rs.getString("cep"));
                enderecoDepartamento.setDepartamento_id(rs.getInt("departamento_id"));
            }
            return enderecoDepartamento;
        } catch (Exception ex) {
            System.out.println("Exception" + ex.getMessage());
        }
        return null;
    }

    public ArrayList<EnderecoDepartamento> ConsultarEnderecosDepartamentoPorNome(String nome) throws Exception {
        ArrayList<EnderecoDepartamento> lista = new ArrayList<>();
        Conexao con = new Conexao();
        String sql = "SELECT ed.id, ed.rua, ed.bairro, ed.numero, ed.complemento, ed.cidade, ed.estado, ed.cep, ed.departamento_id FROM endereco_departamento ed JOIN departamento d ON ed.departamento_id = d.id WHERE d.nome like '%" + nome + "%';";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                EnderecoDepartamento enderecoDepartamento = new EnderecoDepartamento();
                enderecoDepartamento.setId(rs.getInt("id"));
                enderecoDepartamento.setRua(rs.getString("rua"));
                enderecoDepartamento.setBairro(rs.getString("bairro"));
                enderecoDepartamento.setNumero(rs.getInt("numero"));
                enderecoDepartamento.setComplemento(rs.getString("complemento"));
                enderecoDepartamento.setCidade(rs.getString("cidade"));
                enderecoDepartamento.setEstado(rs.getString("estado"));
                enderecoDepartamento.setCep(rs.getString("cep"));
                enderecoDepartamento.setDepartamento_id(rs.getInt("departamento_id"));

                lista.add(enderecoDepartamento);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return lista;
    }

    public void remover() {
        try {
            Conexao con = new Conexao();
            String sql = "DELETE FROM endereco_departamento WHERE id = ?;"; // Using parameterized statement
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id); // Setting the parameter
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public static void removerPorId(int id) throws Exception {
        Conexao con = new Conexao();
        String sql = "DELETE FROM endereco_departamento WHERE id = ?;"; // Using parameterized statement
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
