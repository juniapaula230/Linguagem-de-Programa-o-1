package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tp.Conexao;

public class EnderecoMotorista {

    private int id;//colocar a mesma variavel do id motorista aqui no java
    private String rua;
    private String bairro;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private int motorista_id;//colocar a mesma variavel do id motorista aqui no java

    public EnderecoMotorista() {
    }

    public EnderecoMotorista(int id, String bairro, String complemento, String cidade, String cep, int motorista_id) {
        this.id = id;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
        this.motorista_id = motorista_id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public void InserirEnderecoMotorista() throws Exception {
        Conexao con = new Conexao();
        String sql = "insert into endereco_motorista (rua, bairro, numero, complemento, cidade, estado, cep, motorista_id) values (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, getRua());
            pst.setString(2, getBairro());
            pst.setInt(3, getNumero());
            pst.setString(4, getComplemento());
            pst.setString(5, getCidade());
            pst.setString(6, getEstado());
            pst.setString(7, getCep());
            pst.setInt(8, getMotorista_id());
            pst.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public EnderecoMotorista ConsultarEnderecoMotoristaPorCodigo(int codigo) throws Exception {
        Conexao con = new Conexao();
        String sql = "Select * from endereco_motorista where motorista_id = " + codigo + " ;";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            EnderecoMotorista enderecoMotorista = null;
            while (rs.next()) {
                enderecoMotorista = new EnderecoMotorista();
                enderecoMotorista.setId(rs.getInt("id"));
                enderecoMotorista.setRua(rs.getString("rua"));
                enderecoMotorista.setBairro(rs.getString("bairro"));
                enderecoMotorista.setNumero(rs.getInt("numero"));
                enderecoMotorista.setComplemento(rs.getString("complemento"));
                enderecoMotorista.setCidade(rs.getString("cidade"));
                enderecoMotorista.setEstado(rs.getString("estado"));
                enderecoMotorista.setCep(rs.getString("cep"));
                enderecoMotorista.setMotorista_id(rs.getInt("motorista_id"));
            }
            return enderecoMotorista;
        } catch (Exception ex) {
            System.out.println("Exception" + ex.getMessage());
        }
        return null;
    }

    //vai ser usada na preencher tabela por isso esta aqui
    public ArrayList<EnderecoMotorista> ConsultarEnderecosMotoristaPorNome(String nome) throws Exception {
        ArrayList<EnderecoMotorista> lista = new ArrayList<>();
        Conexao con = new Conexao();
        String sql = "Select * from endereco_motorista where motorista_id in (select motorista_id from motorista where nome like '" + nome + "%');";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            EnderecoMotorista enderecoMotorista = null;
            while (rs.next()) {
                enderecoMotorista = new EnderecoMotorista();
                enderecoMotorista.setId(rs.getInt("id"));
                enderecoMotorista.setRua(rs.getString("rua"));
                enderecoMotorista.setBairro(rs.getString("bairro"));
                enderecoMotorista.setNumero(rs.getInt("numero"));
                enderecoMotorista.setComplemento(rs.getString("complemento"));
                enderecoMotorista.setCidade(rs.getString("cidade"));
                enderecoMotorista.setEstado(rs.getString("estado"));
                enderecoMotorista.setCep(rs.getString("cep"));
                enderecoMotorista.setMotorista_id(rs.getInt("motorista_id"));
                lista.add(enderecoMotorista);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return lista;
    }

    
    //esse nao e muito bom de usar
    public void RemoverEnderecoMotorista() {
    try {
        Conexao con = new Conexao();
        String sql = "delete from endereco_motorista where motorista_id = ?";
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, getMotorista_id());
        if (pst.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Endereço do motorista excluído com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Endereço do motorista não foi excluído com sucesso");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}

    public void RemoverEnderecoMotorisyaById(int id) {
    try {
        Conexao con = new Conexao();
        String sql = "delete from endereco_motorista where id = " + id + ";";
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Endereço do motorista excluido com sucesso!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}

    //falta o atualizar que e mais complicado no motorista e aqui
    
    
    
    
    
    
}//final

