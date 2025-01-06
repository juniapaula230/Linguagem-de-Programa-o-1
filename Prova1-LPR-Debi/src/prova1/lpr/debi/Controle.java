package prova1.lpr.debi;
/**
 *
 * @author 0040838
 */
import java.util.ArrayList;
import java.util.List;

public class Controle {
    private List<Turma> turmas;

    public Controle() {
        turmas = new ArrayList<>();
    }

    public void criarTurma(String codigo, String descricao, int numeroAulasSemana) {
        Turma turma = new Turma(codigo, descricao, numeroAulasSemana);
        turmas.add(turma);
    }

    public void adicionarAlunoTurma(String codigoTurma, Aluno aluno) {
        for (Turma turma : turmas) {
            if (turma.getCodigo().equals(codigoTurma)) {
                turma.adicionarAluno(aluno);
                break;
            }
        }
    }

    public Turma buscarTurmaPorCodigo(String codigoTurma) {
        for (Turma turma : turmas) {
            if (turma.getCodigo().equals(codigoTurma)) {
                return turma;
            }
        }
        return null;
    }

    public List<Turma> listarTurmas() {
        return turmas;
    }
}

