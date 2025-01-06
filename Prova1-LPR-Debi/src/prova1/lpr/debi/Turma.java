package prova1.lpr.debi;
/**
 *
 * @author 0040838
 */
import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private String descricao;
    private int numeroAulasSemana;
    private List<Aluno> alunos;

    public Turma(String codigo, String descricao, int numeroAulasSemana) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.numeroAulasSemana = numeroAulasSemana;
        this.alunos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumeroAulasSemana() {
        return numeroAulasSemana;
    }

    public void setNumeroAulasSemana(int numeroAulasSemana) {
        this.numeroAulasSemana = numeroAulasSemana;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public void mostrarSumario() {
        System.out.println("Código: " + codigo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Número de Aulas na Semana: " + numeroAulasSemana);
        System.out.println("Número de Alunos: " + alunos.size());
    }

    public int contarAlunosPorSexo(char sexo) {
        int contador = 0;
        for (Aluno aluno : alunos) {
            if (aluno.getSexo() == sexo) {
                contador++;
            }
        }
        return contador;
    }

    public int contarAlunosCasados() {
        int contador = 0;
        for (Aluno aluno : alunos) {
            if (aluno.getEstadoCivil() == 'C') {
                contador++;
            }
        }
        return contador;
    }
}

