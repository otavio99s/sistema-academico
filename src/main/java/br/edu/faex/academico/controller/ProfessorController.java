package br.edu.faex.academico.controller;

import br.edu.faex.academico.model.Professor;
import br.edu.faex.academico.service.ProfessorService;

import java.util.List;

public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    public void cadastrar(Professor professor) {
        professorService.cadastrar(professor);
    }

    public List<Professor> listar() {
        return professorService.listar();
    }

    public Professor buscarPorId(Long id) {
        return professorService.buscarPorId(id);
    }

    public void excluir(Long id) {
        professorService.excluir(id);
    }

    public void atualizar(Professor professor) {
        professorService.atualizar(professor);
    }
}