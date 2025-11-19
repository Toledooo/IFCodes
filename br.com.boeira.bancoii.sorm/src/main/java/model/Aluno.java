package model;

public record Aluno(
        Long id,
        String matricula,
        String nome,
        String documento,
        Curso curso
) {}
