package br.com.boeira.orm;

import br.com.boeira.orm.model.Aluno;
import br.com.boeira.orm.model.Curso;
import br.com.boeira.orm.repository.AlunoRepository;
import br.com.boeira.orm.repository.CursoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class CrudConsoleApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CrudConsoleApplication.class);

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(CrudConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("=== SISTEMA DE GESTÃO DE CURSOS E ALUNOS ===");

        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gerenciar Cursos");
            System.out.println("2. Gerenciar Alunos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> menuCurso();
                case 2 -> menuAluno();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        log.info("Aplicação finalizada com sucesso!");
    }

    // ================================================================
    // MENU CURSOS
    // ================================================================
    private void menuCurso() {
        int opcao;
        do {
            System.out.println("\n--- MENU DE CURSOS ---");
            System.out.println("1. Cadastrar novo curso");
            System.out.println("2. Listar todos os cursos");
            System.out.println("3. Atualizar curso");
            System.out.println("4. Deletar curso");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarCurso();
                case 2 -> listarCursos();
                case 3 -> atualizarCurso();
                case 4 -> deletarCurso();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarCurso() {
        System.out.println("\n--- Cadastrar Curso ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Carga Horária: ");
        int carga = scanner.nextInt();
        scanner.nextLine();

        Curso curso = new Curso(codigo, nome, carga);
        cursoRepository.save(curso);
        System.out.println("✅ Curso cadastrado com sucesso!");
    }

    private void listarCursos() {
        System.out.println("\n--- LISTA DE CURSOS ---");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-10s %-25s %-10s%n", "ID", "CÓDIGO", "NOME", "CARGA");
        System.out.println("--------------------------------------------------");

        cursoRepository.findAll().forEach(curso ->
                System.out.printf("%-5d %-10s %-25s %-10dh%n",
                        curso.getId(),
                        curso.getCodigo(),
                        curso.getNome(),
                        curso.getCargaHoraria())
        );
    }

    private void atualizarCurso() {
        System.out.print("Digite o ID do curso que deseja atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Curso> optCurso = cursoRepository.findById(id);
        if (optCurso.isPresent()) {
            Curso curso = optCurso.get();
            System.out.println("Curso atual: " + curso);
            System.out.print("Novo nome: ");
            curso.setNome(scanner.nextLine());
            System.out.print("Nova carga horária: ");
            curso.setCargaHoraria(scanner.nextInt());
            scanner.nextLine();
            cursoRepository.save(curso);
            System.out.println("✅ Curso atualizado com sucesso!");
        } else {
            System.out.println("❌ Curso não encontrado!");
        }
    }

    private void deletarCurso() {
        System.out.print("Digite o ID do curso a excluir: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            System.out.println("✅ Curso excluído com sucesso!");
        } else {
            System.out.println("❌ Curso não encontrado!");
        }
    }

    // ================================================================
    // MENU ALUNOS
    // ================================================================
    private void menuAluno() {
        int opcao;
        do {
            System.out.println("\n--- MENU DE ALUNOS ---");
            System.out.println("1. Cadastrar novo aluno");
            System.out.println("2. Listar todos os alunos");
            System.out.println("3. Atualizar aluno");
            System.out.println("4. Deletar aluno");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarAluno();
                case 2 -> listarAlunos();
                case 3 -> atualizarAluno();
                case 4 -> deletarAluno();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void criarAluno() {
        System.out.println("\n--- Cadastrar Aluno ---");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Documento: ");
        String documento = scanner.nextLine();

        listarCursos();
        System.out.print("Digite o ID do curso: ");
        Long cursoId = scanner.nextLong();
        scanner.nextLine();

        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isPresent()) {
            Aluno aluno = new Aluno(matricula, nome, documento, cursoOpt.get());
            alunoRepository.save(aluno);
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } else {
            System.out.println("❌ Curso não encontrado!");
        }
    }

    private void listarAlunos() {
        System.out.println("\n--- LISTA DE ALUNOS ---");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-5s %-12s %-25s %-15s %-20s%n", "ID", "MATRÍCULA", "NOME", "DOCUMENTO", "CURSO");
        System.out.println("--------------------------------------------------------------------------");

        alunoRepository.findAll().forEach(aluno -> {
            String nomeCurso = (aluno.getCurso() != null) ? aluno.getCurso().getNome() : "Sem curso";
            System.out.printf("%-5d %-12s %-25s %-15s %-20s%n",
                    aluno.getId(),
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getDocumento(),
                    nomeCurso);
        });
    }

    private void atualizarAluno() {
        System.out.print("Digite o ID do aluno que deseja atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Aluno> optAluno = alunoRepository.findById(id);
        if (optAluno.isPresent()) {
            Aluno aluno = optAluno.get();
            System.out.println("Aluno atual: " + aluno);

            System.out.print("Novo nome: ");
            aluno.setNome(scanner.nextLine());

            System.out.print("Novo documento: ");
            aluno.setDocumento(scanner.nextLine());

            listarCursos();
            System.out.print("Novo ID do curso: ");
            Long novoCursoId = scanner.nextLong();
            scanner.nextLine();

            Optional<Curso> cursoOpt = cursoRepository.findById(novoCursoId);
            cursoOpt.ifPresent(aluno::setCurso);

            alunoRepository.save(aluno);
            System.out.println("✅ Aluno atualizado com sucesso!");
        } else {
            System.out.println("❌ Aluno não encontrado!");
        }
    }

    private void deletarAluno() {
        System.out.print("Digite o ID do aluno a excluir: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            System.out.println("✅ Aluno excluído com sucesso!");
        } else {
            System.out.println("❌ Aluno não encontrado!");
        }
    }
}


/*package br.com.boeira.orm;

import br.com.boeira.orm.model.*;
import br.com.boeira.orm.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CrudConsoleApplication implements CommandLineRunner {

    // Usando o logger do Spring
    private static final Logger log = LoggerFactory.getLogger(CrudConsoleApplication.class);

    // Injeta o Repositório para podermos usá-lo
    @Autowired
    private CursoRepository cursoRepository;

    public static void main(String[] args) {
        // O SpringApplication.run inicia o Spring...
        log.info("INICIANDO O SPRING BOOT...");
        SpringApplication.run(CrudConsoleApplication.class, args);
        log.info("APLICAÇÃO FINALIZADA.");
        // ...e depois o Spring chama o método 'run' abaixo.
    }

    // Este método é o "novo main". Ele roda DEPOIS que o Spring
    // já configurou o banco de dados e a injeção de dependências.
    @Override
    public void run(String... args) throws Exception {

        log.info("--- INICIANDO OPERAÇÕES CRUD ---");

        // 1. CREATE (Criar)
        log.info("--- 1. CREATE ---");
        Curso  c1 = new Curso("1256", "Sistemas", 1200);
        cursoRepository.save(c1);
        log.info("Produtos criados!");
/*
        // 2. READ (Ler todos)
        log.info("--- 2. READ (Todos) ---");
        List<Produto> todos = produtoRepository.findAll();
        todos.forEach(p -> log.info(p.toString()));

        // 3. READ (Ler por ID)
        log.info("--- 3. READ (Por ID 1) ---");
        Optional<Produto> pBusca = produtoRepository.findById(1L);
        pBusca.ifPresent(p -> log.info("Encontrado: " + p));

        // 4. UPDATE (Atualizar)
        log.info("--- 4. UPDATE (ID 1) ---");
        if (pBusca.isPresent()) {
            Produto produtoParaAtualizar = pBusca.get();
            log.info("Preço antigo: " + produtoParaAtualizar.getPreco());
            produtoParaAtualizar.setPreco(1750.00); // Novo preço
            produtoRepository.save(produtoParaAtualizar);
            log.info("Produto ID 1 atualizado: " + produtoParaAtualizar);
        }

        // 5. DELETE (Deletar)
        log.info("--- 5. DELETE (ID 2) ---");
        produtoRepository.deleteById(2L);
        log.info("Produto ID 2 deletado.");

        // Lista final
        //log.info("--- LISTA FINAL DE CURSO ---");
       // cursoRepository.findAll().forEach(p -> log.info(p.toString()));


       // log.info("--- OPERAÇÕES CRUD CONCLUÍDAS ---");
    }
}

******************************

spring.application.name=orm

# --- Configuração do H2 (Baseado em Arquivo Local) ---
spring.datasource.url=jdbc:h2:file:./bancoii
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# --- Configuração do JPA/Hibernate ---
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Mostra o SQL gerado no console
spring.jpa.show-sql=true

# H2 Console desativado
spring.h2.console.enabled=false

 */

