package br.edu.faex.academico.service;

import br.edu.faex.academico.model.Curso;
import br.edu.faex.academico.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void cadastrar(Curso curso) {
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            System.out.println("Erro: O nome do curso não pode ser vazio.");
            return;
        }
        if (curso.getModalidade() == null || curso.getModalidade().trim().isEmpty()) {
            System.out.println("Erro: A modalidade do curso não pode ser vazia.");
            return;
        }
        cursoRepository.salvar(curso);
    }

    public List<Curso> listar() {
        return cursoRepository.listar();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.buscarPorId(id);
    }

    public void excluir(Long id) {
        Curso curso = cursoRepository.buscarPorId(id);
        if (curso == null) {
            System.out.println("Erro: Curso com ID " + id + " não encontrado.");
            return;
        }
        cursoRepository.excluir(id);
    }

    public void atualizar(Curso curso) {
        if (curso.getId() == null) {
            System.out.println("Erro: ID do curso não pode ser nulo para atualização.");
            return;
        }
        cursoRepository.atualizar(curso);
    }
}