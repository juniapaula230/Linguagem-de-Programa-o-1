package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Beneficio {

    private int id; //colocar a mesma variavel do id motorista aqui no java 
    private boolean vale_transporte;
    private boolean vale_refeicao;
    private int motorista_id;

    public Beneficio() {
    }

    public Beneficio(int id, boolean vale_refeicao, int motorista_id) {
        this.id = id;
        this.vale_refeicao = vale_refeicao;
        this.motorista_id = motorista_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVale_transporte() {
        return vale_transporte;
    }

    public void setVale_transporte(boolean vale_transporte) {
        this.vale_transporte = vale_transporte;
    }

    public boolean isVale_refeicao() {
        return vale_refeicao;
    }

    public void setVale_refeicao(boolean vale_refeicao) {
        this.vale_refeicao = vale_refeicao;
    }

    public int getMotorista_id() {
        return motorista_id;
    }

    public void setMotorista_id(int motorista_id) {
        this.motorista_id = motorista_id;
    }

    
}//final
