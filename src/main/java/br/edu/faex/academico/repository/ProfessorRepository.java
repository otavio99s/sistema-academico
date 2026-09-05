package br.edu.faex.academico.repository;

import br.edu.faex.academico.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {

    private List<Professor> professores = new ArrayList<>();

    public void salvar(Professor professor) {
        professores.add(professor);
    }

    public List<Professor> listar() {
        return professores;
    }

    public Professor buscarPorId(long id) {
        for (Professor professor : professores) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }

        return null;
    }
}

