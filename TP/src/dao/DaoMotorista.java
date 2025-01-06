/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Motorista;

/**
 *
 * @author 0040838
 */
public class DaoMotorista {
    
    public void InserirMotorista(Motorista m) throws Exception {
        Conexao con = new Conexao();
        String sql = "insert into motorista (nome, cpf, salario, sexo, tipo_motorista) values (?, ?, ?, ?, ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, m.getNome());
            pst.setString(2, m.getCpf());
            pst.setFloat(3, m.getSalario());
            pst.setString(4, m.getSexo());
            pst.setString(5, m.getTipo_motorista());
            pst.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public modelo.Motorista ConsultarMotoristaPorCodigo(int codigo) throws Exception {
        Conexao con = new Conexao();
        String sql = "Select * from motorista where codigo = " + codigo + " ;";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            modelo.Motorista motorista = null;
            while (rs.next()) {
                motorista = new modelo.Motorista();
                motorista.setCodigo(rs.getInt("codigo"));
                motorista.setNome(rs.getString("nome"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setSalario(rs.getFloat("salario"));
                motorista.setSexo(rs.getString("sexo"));
                motorista.setTipo_motorista(rs.getString("tipo_motorista"));
            }
            return motorista;
        } catch (Exception ex) {
            System.out.println("Exception" + ex.getMessage());
        }
        return null;
    }

    //vai ser usada na preencher tabela por isso esta aqui
    public ArrayList<modelo.Motorista> ConsultarMotoristaPorNome(String nome) {
        ArrayList<modelo.Motorista> lista = new ArrayList<>();
        try {
            Conexao con = new Conexao();
            String sql = "Select * from motorista where nome like '" + nome + "%';";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            modelo.Motorista motorista = null;
            while (rs.next()) {
                motorista = new modelo.Motorista();
                motorista.setCodigo(rs.getInt("codigo"));
                motorista.setNome(rs.getString("nome"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setSalario(rs.getFloat("salario"));
                motorista.setSexo(rs.getString("sexo"));
                motorista.setTipo_motorista(rs.getString("tipo_motorista"));
                lista.add(motorista);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return lista;
    }

    //retorna um objeto com os dados do motorista
    public Object[] getDadosMotorista(Motorista m) {
        Object[] retorno = new Object[]{
            m.getCodigo(),
            m.getNome(),
            m.getCpf(),
            m.getSalario(),
            m.getSexo(),
            m.getTipo_motorista()
        };
        return retorno;
    }

    public void RemoverMotorista(Motorista m) {
        try {
            Conexao con = new Conexao();
            String sql = "delete from motorista where id = ?";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, m.getCodigo());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Motorista excluído com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Motorista não foi excluído com sucesso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }

    }

    public void RemoverMotoristaById(int id) {
        try {
            Conexao con = new Conexao();
            String sql = "delete from motorista where id = " + id + ";";
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Motorista excluído com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
