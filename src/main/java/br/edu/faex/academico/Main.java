
package br.edu.faex.academico;

import br.edu.faex.academico.controller.AlunoController;
import br.edu.faex.academico.model.Aluno;
import br.edu.faex.academico.repository.AlunoRepository;
import br.edu.faex.academico.service.AlunoService;

public class Main {

    public static void main(String[] args) {

        AlunoRepository alunoRepository = new AlunoRepository();
        AlunoService alunoService = new AlunoService(alunoRepository);
        AlunoController alunoController = new AlunoController(alunoService);

        System.out.println("===== TESTE 1: CADASTRAR ALUNOS =====");

        Aluno aluno1 = new Aluno(
                "Aleandro Ribeiro de Lima",
                "aleandro.lima@faex.edu.br"
        );

        Aluno aluno2 = new Aluno(
                "Maria Helena de Lima",
                "maria.lima@faex.edu.br"
        );

        alunoController.cadastrar(aluno1);
        alunoController.cadastrar(aluno2);

        System.out.println("\n===== TESTE 2: E-MAIL DUPLICADO NO CADASTRO =====");

        Aluno aluno3 = new Aluno(
                "João da Silva",
                "ALEANDRO.LIMA@FAEX.EDU.BR"
        );

        alunoController.cadastrar(aluno3);

        System.out.println("\n===== TESTE 3: ATUALIZAR NOME MANTENDO O E-MAIL =====");

        Aluno alunoEditado1 = new Aluno(
                "Aleandro Ribeiro Atualizado",
                "aleandro.lima@faex.edu.br"
        );

        alunoEditado1.setId(1L);
        alunoController.atualizar(alunoEditado1);

        System.out.println("\n===== TESTE 4: ATUALIZAR COM E-MAIL DE OUTRO ALUNO =====");

        Aluno alunoEditado2 = new Aluno(
                "Aleandro Ribeiro de Lima",
                "MARIA.LIMA@FAEX.EDU.BR"
        );

        alunoEditado2.setId(1L);
        alunoController.atualizar(alunoEditado2);

        System.out.println("\n===== TESTE 5: ATUALIZAR COM E-MAIL INVÁLIDO =====");

        Aluno alunoEditado3 = new Aluno(
                "Aleandro Ribeiro de Lima",
                "emailinvalido.com"
        );

        alunoEditado3.setId(1L);
        alunoController.atualizar(alunoEditado3);

        System.out.println("\n===== TESTE 6: ATUALIZAR COM NOME VAZIO =====");

        Aluno alunoEditado4 = new Aluno(
                "",
                "aleandro.lima@faex.edu.br"
        );

        alunoEditado4.setId(1L);
        alunoController.atualizar(alunoEditado4);

        System.out.println("\n===== TESTE 7: ATUALIZAR ID INEXISTENTE =====");

        Aluno alunoInexistente = new Aluno(
                "Aluno Inexistente",
                "inexistente@faex.edu.br"
        );

        alunoInexistente.setId(999L);
        alunoController.atualizar(alunoInexistente);

        System.out.println("\n===== TESTE 8: BUSCAR ALUNO POR ID =====");

        Aluno alunoEncontrado = alunoController.buscarPorId(2L);

        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado!");
            System.out.println("ID: " + alunoEncontrado.getId());
            System.out.println("Nome: " + alunoEncontrado.getNome());
            System.out.println("E-mail: " + alunoEncontrado.getEmail());
        } else {
            System.out.println("Aluno não encontrado.");
        }

        System.out.println("\n===== TESTE 9: LISTAGEM FINAL =====");

        for (Aluno aluno : alunoController.listar()) {
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
            System.out.println("Ativo: " + aluno.isAtivo());
            System.out.println("-------------------------");
        }

        System.out.println("\n===== TESTE 10: EXCLUIR ALUNO =====");

        alunoController.excluir(2L);

        System.out.println("\n===== LISTAGEM APÓS EXCLUSÃO =====");

        for (Aluno aluno : alunoController.listar()) {
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("E-mail: " + aluno.getEmail());
            System.out.println("-------------------------");
        }
    }
}
