package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tp.Conexao;

public class Veiculo {

    private int id;
    private String placa;
    private String modelo;
    private int ano;
    private String cor;
    private String tipo_combustivel;
    private int codigo_unico;
    private int categoria_id;

    public Veiculo() {
    }

public Veiculo(int id, String placa, String modelo, int ano, String cor, String tipo_combustivel, int codigo_unico, int categoria_id) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.tipo_combustivel = tipo_combustivel;
        this.codigo_unico = codigo_unico;
        this.categoria_id = categoria_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo_combustivel() {
        return tipo_combustivel;
    }

    public void setTipo_combustivel(String tipo_combustivel) {
        this.tipo_combustivel = tipo_combustivel;
    }

    public int getCodigo_unico() {
        return codigo_unico;
    }

    public void setCodigo_unico(int codigo_unico) {
        this.codigo_unico = codigo_unico;
    }
  
  
  public void inserirVeiculo() throws SQLException, Exception {
        Conexao con = new Conexao();
        String sql = "INSERT INTO veiculo (placa, modelo, ano, cor, tipo_combustivel, codigo_unico, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pst = con.getConexao().prepareStatement(sql)) {
            pst.setString(1, getPlaca());
            pst.setString(2, getModelo());
            pst.setInt(3, getAno());
            pst.setString(4, getCor());
            pst.setString(5, getTipo_combustivel());
            pst.setInt(6, getCodigo_unico());
            pst.setInt(7, getCategoria_id());

            pst.executeUpdate();
        } catch (SQLException ex) {
            // Trate a exceção apropriadamente, como exibir uma mensagem de erro.
            ex.printStackTrace();
        }
    }
  
   public Veiculo consultarVeiculoPorCodigo(int codigo) throws SQLException, Exception {
        Conexao con = new Conexao();
        String sql = "SELECT * FROM veiculo WHERE codigo_unico = ?;";

        try (PreparedStatement pst = con.getConexao().prepareStatement(sql)) {
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();

            Veiculo veiculo = null;

            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setCodigo_unico(rs.getInt("codigo_unico"));
                veiculo.setCategoria_id(rs.getInt("categoria_id"));
            }

            return veiculo;
        } catch (SQLException ex) {
            // Trate a exceção apropriadamente, como exibir uma mensagem de erro.
            ex.printStackTrace();
        }

        return null;
    }

   
   
   public ArrayList<Veiculo> consultarVeiculoPorModelo(String modelo) throws Exception {
        ArrayList<Veiculo> lista = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            String sql = "SELECT * FROM veiculo WHERE modelo LIKE ?;";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, modelo + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setCodigo_unico(rs.getInt("codigo_unico"));
                veiculo.setCategoria_id(rs.getInt("categoria_id"));
                lista.add(veiculo);
            }
        } catch (SQLException ex) {
            // Trate a exceção apropriadamente, como exibir uma mensagem de erro.
            ex.printStackTrace();
        }
        return lista;
    }
   
   public Object[] getDadosVeiculo() {
        Object[] retorno = new Object[]{
            getPlaca(),
            getModelo(),
            getAno(),
            getCor(),
            getTipo_combustivel(),
            getCodigo_unico(),
            getCategoria_id()
        };
        return retorno;
    }
   
   
       public void removerVeiculo() {
        try {
            Conexao con = new Conexao();
            String sql = "DELETE FROM veiculo WHERE codigo_unico = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, getCodigo_unico());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Veículo não foi excluído com sucesso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void removerVeiculoById(int codigo_unico) {
        try {
            Conexao con = new Conexao();
            String sql = "DELETE FROM veiculo WHERE codigo_unico = " + codigo_unico + ";";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
   
   
   
   
   
   
}//final
