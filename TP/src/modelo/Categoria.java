package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tp.Conexao;

public class Categoria {

    private int id;
    private String nome;
    private float capacidade_carga;
    private int numero_passageiro;
    private float consumo_medio;

    public Categoria() {
    }

    public Categoria(int id, String nome, float capacidade_carga, int numero_passageiro, float consumo_medio) {
        this.id = id;
        this.nome = nome;
        this.capacidade_carga = capacidade_carga;
        this.numero_passageiro = numero_passageiro;
        this.consumo_medio = consumo_medio;
    }

    public float getConsumo_medio() {
        return consumo_medio;
    }

    public void setConsumo_medio(float consumo_medio) {
        this.consumo_medio = consumo_medio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCapacidade_carga() {
        return capacidade_carga;
    }

    public void setCapacidade_carga(float capacidade_carga) {
        this.capacidade_carga = capacidade_carga;
    }

    public int getNumero_passageiro() {
        return numero_passageiro;
    }

    public void setNumero_passageiro(int numero_passageiro) {
        this.numero_passageiro = numero_passageiro;
    }

    public void Inserir() throws Exception {
        Conexao con = new Conexao();
        String sql = "insert into categoria (nome, capacidade_carga, numero_passageiro, consumo_medio) values (?, ?, ?, ?);";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, getNome());
            pst.setFloat(2, getCapacidade_carga());
            pst.setInt(3, getNumero_passageiro());
            pst.setFloat(4, getConsumo_medio());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Categoria inserida com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

  

public ArrayList<Categoria> ConsultarPorNome(String nome) throws Exception {
    try {
        Conexao con = new Conexao();
        String sql = "Select * from categoria where nome like '" + nome + "%';";
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        ArrayList<Categoria> lista = new ArrayList<>();
        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            categoria.setCapacidade_carga(rs.getFloat("capacidade_carga"));
            categoria.setNumero_passageiro(rs.getInt("numero_passageiro"));
            categoria.setConsumo_medio(rs.getFloat("consumo_medio"));
            lista.add(categoria);
        }
        return lista;
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
    return null;
}


public Categoria ConsultarPorId(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "Select * from categoria where id = " + id + ";"; // Using the provided SQL query
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        Categoria categoria = new Categoria(); // Creating an instance of the Categoria class

        while (rs.next()) { // Iterating through the result set
            categoria.setId(rs.getInt("id")); // Setting the ID property
            categoria.setNome(rs.getString("nome")); // Setting the nome property
            categoria.setCapacidade_carga(rs.getFloat("capacidade_carga")); // Setting the capacidadeCarga property
            categoria.setNumero_passageiro(rs.getInt("numero_passageiro")); // Setting the numeroPassageiros property
            categoria.setConsumo_medio(rs.getFloat("consumo_medio")); // Setting the consumoMedio property
        }

        return categoria; // Returning the Categoria object
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage()); // Handling any exceptions
    }

    return null; // Returning null if no matching record is found
}


public void Remover() throws Exception {
    Conexao con = new Conexao();
    String sql = "delete from categoria where id = " + getId() + ";";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
}


public static void RemoverPorId(int id) throws Exception {
    Conexao con = new Conexao();
    String sql = "delete from categoria where id = ?;";
    try {
        PreparedStatement pst = con.getConexao().prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
    } catch (Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }
}


//falta atualizacao
}//final
