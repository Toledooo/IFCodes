package br.com.boeira.orm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private String nome;
    private String documento;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Aluno(String matricula, String nome, String documento, Curso curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.documento = documento;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", curso=" + (curso != null ? curso.getNome() : "Sem curso") +
                '}';
    }
}

/*package br.com.boeira.orm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity /* diz para Spring que é uma entidade(model)
@Data /* diz que vai ser armazenado em BAnco
@NoArgsConstructor// criando construtor vazio
// Como se tivesse um construtor cheio com todos os campos
public class Aluno {    @Id// definindo chave primaria
@GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar sequencial
    private Long id;
    private String matricula;
    private String nome;
    private String documento;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    public Aluno(Long id, String matricula, String nome, String documento, Curso curso) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.documento = documento;
        this.curso = curso;
    }
}*/