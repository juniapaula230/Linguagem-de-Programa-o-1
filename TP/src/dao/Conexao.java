/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author 0040838
 */
public class Conexao {

    private String url = "jdbc:postgresql://localhost:5432/db_controle";//mudar nome do banco
    private String usuario = "postgres";
    private String senha = "root";
    private Connection con = null;

    public Conexao() throws Exception {
        carregarDrive();
    }

    public Connection getConexao() throws Exception {

        try {
            if (con == null) {
                Properties prop = new Properties();
                prop.put("user", usuario);
                prop.put("password", senha);
                con = DriverManager.getConnection(url, prop);
            }
            return con;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private void carregarDrive() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Conexao con = new Conexao();
            Connection c = con.getConexao();
            System.out.println(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}