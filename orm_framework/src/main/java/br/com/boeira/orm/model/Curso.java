package br.com.boeira.orm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nome;
    private int cargaHoraria;

    public Curso(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}







/*package br.com.boeira.orm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.type.internal.ImmutableNamedBasicTypeImpl;

import java.sql.Connection;

@Entity  diz para Spring que é uma entidade(model)
@Data  diz que vai ser armazenado em BAnco
@NoArgsConstructor// criando construtor vazio
@AllArgsConstructor // Como se tivesse um construtor cheio com todos os campos
public class Curso {
    @Id// definindo chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar sequencial
    private Long id;
    private String codigo;
    private String nome;
    private int cargaHoraria;

    public Curso(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }
}*/
