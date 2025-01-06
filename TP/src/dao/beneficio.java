/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.Beneficio;

/**
 *
 * @author 0040838
 */
public class beneficio {
    public void Inserir() throws Exception {
        Conexao con = new Conexao();
        String sql = "insert into beneficio (vale_transporte, vale_refeicao, motorista_id) values (?, ?, ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setBoolean(1, isVale_transporte());
            pst.setBoolean(2, isVale_refeicao());
            pst.setInt(3, getMotorista_id());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Benefício inserido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public Beneficio ConsultarPorId(int id) throws Exception {
        Conexao con = new Conexao();
        String sql = "Select id, vale_transporte, vale_refeicao, motorista_id from beneficio where id = " + id + ";";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Beneficio beneficio = new Beneficio();
                beneficio.setId(rs.getInt("id"));
                beneficio.setVale_transporte(rs.getBoolean("vale_transporte"));
                beneficio.setVale_refeicao(rs.getBoolean("vale_refeicao"));
                beneficio.setMotorista_id(rs.getInt("motorista_id"));
                return beneficio;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return null;
    }

    public Beneficio ConsultarPorNome(String nome) throws Exception {
        Conexao con = new Conexao();
        String sql = "Select id, vale_transporte, vale_refeicao, motorista_id from beneficio where motorista_id in (select id from motorista where nome like ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, "%" + nome + "%");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Beneficio beneficio = new Beneficio();
                beneficio.setId(rs.getInt("id"));
                beneficio.setVale_transporte(rs.getBoolean("vale_transporte"));
                beneficio.setVale_refeicao(rs.getBoolean("vale_refeicao"));
                beneficio.setMotorista_id(rs.getInt("motorista_id"));
                return beneficio;
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
        String sql = "delete from beneficio where id = " + getId() + ";";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Benefício removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void RemoverById(int id) throws Exception {
        Conexao con = new Conexao();
        String sql = "delete from beneficio where id = " + id + ";";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Benefício removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
