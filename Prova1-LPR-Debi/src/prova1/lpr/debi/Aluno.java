package prova1.lpr.debi;
/**
 *
 * @author 0040838
 */
public class Aluno {
    private String matricula;
    private String nome;
    private char sexo;
    private char estadoCivil;

    public Aluno(String matricula, String nome, char sexo, char estadoCivil) {
        this.matricula = matricula;
        this.nome = nome;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(char estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nome: " + nome + ", Sexo: " + sexo + ", Estado Civil: " + estadoCivil;
    }
}
