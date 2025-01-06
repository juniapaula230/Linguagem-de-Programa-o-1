package prova1.lpr.debi;
/**
 *
 * @author 0040838
 */
import java.util.Scanner;

public class Menu {
    private Controle controle;

    public Menu(Controle controle) {
        this.controle = controle;
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Criar turma: ");
            System.out.println("2 - Adicionar aluno a turma: ");
            System.out.println("3 - Consultar turmas.");
            System.out.println("4 - Sumário de turma.");
            System.out.println("5 - Alunos por sexo.");
            System.out.println("6 - Alunos casados.");
            System.out.println("7 - SAIR!");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Digite o código da turma:");
                    String codigoTurma = scanner.nextLine();

                    System.out.println("Digite a descrição da turma:");
                    String descricaoTurma = scanner.nextLine();

                    System.out.println("Digite o número de aulas na semana (2, 4 ou 6):");
                    int numAulas = scanner.nextInt();

                    controle.criarTurma(codigoTurma, descricaoTurma, numAulas);
                    break;
                case 2:
                    System.out.println("Digite o código da turma:");
                    codigoTurma = scanner.nextLine();

                    System.out.println("Digite a matrícula do aluno:");
                    String matricula = scanner.nextLine();

                    System.out.println("Digite o nome do aluno:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o sexo do aluno (M/F):");
                    char sexo = scanner.nextLine().toUpperCase().charAt(0);

                    System.out.println("Digite o estado civil do aluno (S/C/D):");
                    char estadoCivil = scanner.nextLine().toUpperCase().charAt(0);

                    Aluno aluno = new Aluno(matricula, nome, sexo, estadoCivil);
                    controle.adicionarAlunoTurma(codigoTurma, aluno);
                    break;
                case 3:
                    System.out.println("Turmas criadas:");
                    for (Turma turma : controle.listarTurmas()) {
                        System.out.println(turma.getCodigo() + " - " + turma.getDescricao());
                    }
                    break;
                case 4:
                    System.out.println("Digite o código da turma:");
                    codigoTurma = scanner.nextLine();
                    Turma turma = controle.buscarTurmaPorCodigo(codigoTurma);
                    if (turma != null) {
                        turma.mostrarSumario();
                    } else {
                        System.out.println("TURMA NÃO ENCONTRADA!");
                    }
                    break;
                case 5:
                    System.out.println("Digite o sexo para contagem (M/F):");
                    char sexoContagem = scanner.nextLine().toUpperCase().charAt(0);
                    if (sexoContagem == 'M' || sexoContagem == 'F') {
                        int totalAlunosSexo = 0;
                        for (Turma t : controle.listarTurmas()) {
                            totalAlunosSexo += t.contarAlunosPorSexo(sexoContagem);
                        }
                        System.out.println("Total de alunos do sexo " + sexoContagem + ": " + totalAlunosSexo);
                    } else {
                        System.out.println("Sexo inválido. Use 'M' para masculino ou 'F' para feminino.");
                    }
                    break;
                case 6:
                    int totalAlunosCasados = 0;
                    for (Turma t : controle.listarTurmas()) {
                        totalAlunosCasados += t.contarAlunosCasados();
                    }
                    System.out.println("Alunos Casados: " + totalAlunosCasados);
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
            }
        }
    }
}

