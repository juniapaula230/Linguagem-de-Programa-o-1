package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tp.Conexao;

public class UtilizacaoVeiculo {

    private int id; //codigo de utilizacao pode ser serial
    private int motorista_id; //informar que motorista esta usando
    private int veiculo_id; //qual veiculo esta usando
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private float quilometragem_veiculo;
    private float distancia;
    private String cidade_origem;
    private String cidade_destino;

    public UtilizacaoVeiculo() {
    }

    public UtilizacaoVeiculo(int id, int motorista_id, int veiculo_id, Timestamp data_inicio, Timestamp data_fim, float quilometragem_veiculo, float distancia, String cidade_origem, String cidade_destino) {
        this.id = id;
        this.motorista_id = motorista_id;
        this.veiculo_id = veiculo_id;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.quilometragem_veiculo = quilometragem_veiculo;
        this.distancia = distancia;
        this.cidade_origem = cidade_origem;
        this.cidade_destino = cidade_destino;
    }

    public String getCidade_destino() {
        return cidade_destino;
    }

    public void setCidade_destino(String cidade_destino) {
        this.cidade_destino = cidade_destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    public int getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(int veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public float getQuilometragem_veiculo() {
        return quilometragem_veiculo;
    }

    public void setQuilometragem_veiculo(float quilometragem_veiculo) {
        this.quilometragem_veiculo = quilometragem_veiculo;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getCidade_origem() {
        return cidade_origem;
    }

    public void setCidade_origem(String cidade_origem) {
        this.cidade_origem = cidade_origem;
    }

    public void inserirUtilizacaoVeiculo() throws Exception {
        Conexao con = new Conexao();
        String sql = "INSERT INTO utilizacao_veiculo (motorista_id, veiculo_id, data_inicio, data_fim, quilometragem_veiculo, distancia, cidade_origem, cidade_destino) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, getMotorista_id());
        pst.setInt(2, getVeiculo_id());
        pst.setTimestamp(3, getData_inicio());
        pst.setTimestamp(4, getData_fim());
        pst.setFloat(5, getQuilometragem_veiculo());
        pst.setFloat(6, getDistancia());
        pst.setString(7, getCidade_origem());
        pst.setString(8, getCidade_destino());

        pst.executeUpdate();

    }

    public UtilizacaoVeiculo consultarUtilizacaoVeiculoPorId(int id) throws SQLException, Exception {
        Conexao con = new Conexao();
        String sql = "SELECT * FROM utilizacao_veiculo WHERE id = ?";
        try (PreparedStatement pst = con.getConexao().prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            UtilizacaoVeiculo utilizacaoVeiculo = null;

            while (rs.next()) {
                utilizacaoVeiculo = new UtilizacaoVeiculo();
                utilizacaoVeiculo.setId(rs.getInt("id"));
                utilizacaoVeiculo.setMotorista_id(rs.getInt("motorista_id"));
                utilizacaoVeiculo.setVeiculo_id(rs.getInt("veiculo_id"));
                utilizacaoVeiculo.setData_inicio(rs.getTimestamp("data_inicio"));
                utilizacaoVeiculo.setData_fim(rs.getTimestamp("data_fim"));
                utilizacaoVeiculo.setQuilometragem_veiculo(rs.getFloat("quilometragem_veiculo"));
                utilizacaoVeiculo.setDistancia(rs.getFloat("distancia"));
                utilizacaoVeiculo.setCidade_origem(rs.getString("cidade_origem"));
                utilizacaoVeiculo.setCidade_destino(rs.getString("cidade_destino"));
            }

            return utilizacaoVeiculo;
        } catch (SQLException ex) {
            // Trate a exceção apropriadamente, como exibir uma mensagem de erro.
            ex.printStackTrace();
        }

        return null;
    }

    public ArrayList<UtilizacaoVeiculo> consultarUtilizacaoVeiculoPorNomeMotorista(String nome) throws Exception {
        ArrayList<UtilizacaoVeiculo> lista = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            String sql = "SELECT uv.* FROM utilizacao_veiculo uv "
                    + "INNER JOIN motorista m ON uv.motorista_id = m.codigo "
                    + "WHERE m.nome LIKE ?;";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, nome + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                UtilizacaoVeiculo utilizacaoVeiculo = new UtilizacaoVeiculo();
                utilizacaoVeiculo.setId(rs.getInt("id"));
                utilizacaoVeiculo.setMotorista_id(rs.getInt("motorista_id"));
                utilizacaoVeiculo.setVeiculo_id(rs.getInt("veiculo_id"));
                utilizacaoVeiculo.setData_inicio(rs.getTimestamp("data_inicio"));
                utilizacaoVeiculo.setData_fim(rs.getTimestamp("data_fim"));
                utilizacaoVeiculo.setQuilometragem_veiculo(rs.getFloat("quilometragem_veiculo"));
                utilizacaoVeiculo.setDistancia(rs.getFloat("distancia"));
                utilizacaoVeiculo.setCidade_origem(rs.getString("cidade_origem"));
                utilizacaoVeiculo.setCidade_destino(rs.getString("cidade_destino"));
                lista.add(utilizacaoVeiculo);
            }
        } catch (SQLException ex) {
            // Trate a exceção apropriadamente, como exibir uma mensagem de erro.
            ex.printStackTrace();
        }
        return lista;
    }

    public Object[] getDadosUtilizacaoVeiculo() {
        Object[] retorno = new Object[]{
            getId(),
            getMotorista_id(),
            getVeiculo_id(),
            getData_inicio(),
            getData_fim(),
            getQuilometragem_veiculo(),
            getDistancia(),
            getCidade_origem(),
            getCidade_destino()
        };
        return retorno;
    }

    public void removerUtilizacaoVeiculo() {
        try {
            Conexao con = new Conexao();
            String sql = "DELETE FROM utilizacao_veiculo WHERE id = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, getId());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Utilização de veículo excluída com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Utilização de veículo não foi excluída com sucesso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void removerUtilizacaoVeiculoById(int id) {
        try {
            Conexao con = new Conexao();
            String sql = "DELETE FROM utilizacao_veiculo WHERE id = " + id + ";";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Utilização de veículo excluída com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

}//final
