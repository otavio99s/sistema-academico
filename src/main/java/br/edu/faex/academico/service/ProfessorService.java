package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.repository.ProfessorRepository;

import java.util.List;

public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void cadastrar(Professor professor) {
        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            System.out.println("Erro: O nome do professor não pode ser vazio.");
            return;
        }
        if (professor.getEmail() == null || !professor.getEmail().contains("@")) {
            System.out.println("Erro: E-mail do professor inválido.");
            return;
        }
        professorRepository.salvar(professor);
    }

    public List<Professor> listar() {
        return professorRepository.listar();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.buscarPorId(id);
    }

    public void excluir(Long id) {
        Professor professor = professorRepository.buscarPorId(id);
        if (professor == null) {
            System.out.println("Erro: Professor com ID " + id + " não encontrado.");
            return;
        }
        professorRepository.excluir(id);
    }

    public void atualizar(Professor professor) {
        if (professor.getId() == null) {
            System.out.println("Erro: ID do professor não pode ser nulo para atualização.");
            return;
        }
        professorRepository.atualizar(professor);
    }
}