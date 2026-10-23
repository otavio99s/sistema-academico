
package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Aluno;
import br.edu.faex.academico.repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private AlunoRepository repository;
    private Long proximoId = 1L;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    // Cadastrar aluno
    public void cadastrar(Aluno aluno) {

        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            System.out.println("O nome do aluno é obrigatório.");
            return;
        }

        if (aluno.getEmail() == null || aluno.getEmail().isBlank()) {
            System.out.println("O e-mail do aluno é obrigatório.");
            return;
        }

        String email = aluno.getEmail().trim();

        if (!email.contains("@")) {
            System.out.println("E-mail inválido.");
            return;
        }

        boolean emailDuplicado = repository.listar().stream()
                .anyMatch(a -> a.getEmail() != null
                        && a.getEmail().equalsIgnoreCase(email));

        if (emailDuplicado) {
            System.out.println("E-mail já cadastrado.");
            return;
        }

        aluno.setEmail(email);
        aluno.setId(proximoId);
        proximoId++;

        repository.salvar(aluno);

        System.out.println("Aluno cadastrado com sucesso.");
    }

    // Listar alunos
    public List<Aluno> listar() {
        return repository.listar();
    }

    // Buscar aluno por ID
    public Aluno buscarPorId(Long id) {

        Aluno aluno = repository.buscarPorId(id);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return null;
        }

        return aluno;
    }

    // Excluir aluno
    public void excluir(Long id) {

        Aluno aluno = repository.buscarPorId(id);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        repository.excluir(id);

        System.out.println("Aluno excluído com sucesso.");
    }

    // Atualizar aluno
    public void atualizar(Aluno alunoEditado) {

        if (alunoEditado == null) {
            System.out.println("Aluno inválido.");
            return;
        }

        Aluno aluno = repository.buscarPorId(alunoEditado.getId());

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        if (alunoEditado.getNome() == null
                || alunoEditado.getNome().isBlank()) {
            System.out.println("O nome do aluno é obrigatório.");
            return;
        }

        if (alunoEditado.getEmail() == null
                || alunoEditado.getEmail().isBlank()) {
            System.out.println("O e-mail do aluno é obrigatório.");
            return;
        }

        String email = alunoEditado.getEmail().trim();

        if (!email.contains("@")) {
            System.out.println("E-mail inválido.");
            return;
        }

        // Verifica se outro aluno já utiliza esse e-mail.
        // O próprio aluno é ignorado pela comparação do ID.
        boolean emailDuplicado = repository.listar().stream()
                .anyMatch(a ->
                        !a.getId().equals(alunoEditado.getId())
                                && a.getEmail() != null
                                && a.getEmail().equalsIgnoreCase(email)
                );

        if (emailDuplicado) {
            System.out.println("E-mail já cadastrado.");
            return;
        }

        // Atualiza somente depois de todas as validações.
        alunoEditado.setNome(alunoEditado.getNome().trim());
        alunoEditado.setEmail(email);

        repository.atualizar(alunoEditado);

        System.out.println("Aluno atualizado com sucesso.");
    }
}
