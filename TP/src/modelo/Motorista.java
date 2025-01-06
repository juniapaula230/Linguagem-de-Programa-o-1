package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Motorista {

    private int codigo;
    private String nome;
    private String cpf;
    private String sexo;
    private float salario;
    private String tipo_motorista;

    public Motorista() {
    }

    public Motorista(int codigo, String nome, String cpf, String sexo, float salario, String tipo_motorista) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.salario = salario;
        this.tipo_motorista = tipo_motorista;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTipo_motorista() {
        return tipo_motorista;
    }

    public void setTipo_motorista(String tipo_motorista) {
        this.tipo_motorista = tipo_motorista;
    }

}