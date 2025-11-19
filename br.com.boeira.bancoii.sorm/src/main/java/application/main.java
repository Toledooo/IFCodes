package application;

import model.Aluno;
import model.Curso;
import repository.AlunoDao;
import repository.CursoDAO;
import repository.FabricaConexao;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conexao = FabricaConexao.buscarConexao()) {
            FabricaConexao.criarTabelas(conexao);
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco: " + e.getMessage());
        }

        int opcaoPrincipal;
        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Gerenciar Cursos");
            System.out.println("2 - Gerenciar Alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcaoPrincipal = Integer.parseInt(sc.nextLine());

            switch (opcaoPrincipal) {
                case 1 -> menuCursos();
                case 2 -> menuAlunos();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida!");
            }

        } while (opcaoPrincipal != 0);
    }

    // ================= MENU CURSOS =================
    private static void menuCursos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR CURSOS ---");
            System.out.println("1 - Cadastrar Curso");
            System.out.println("2 - Listar Cursos");
            System.out.println("3 - Buscar Curso por ID");
            System.out.println("4 - Atualizar Curso");
            System.out.println("5 - Deletar Curso");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarCurso();
                case 2 -> listarCursos();
                case 3 -> buscarCursoPorId();
                case 4 -> atualizarCurso();
                case 5 -> deletarCurso();
                case 0 -> {}
                default -> System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarCurso() {
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Carga Horaria: ");
        int carga = Integer.parseInt(sc.nextLine());

        Curso curso = new Curso(null, codigo, nome, carga);
        CursoDAO.criarCurso(curso);
        System.out.println("✅ Curso cadastrado com sucesso!");
    }

    private static void listarCursos() {
        List<Curso> cursos = CursoDAO.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            cursos.forEach(c ->
                    System.out.printf("[%d] %s - %s (%dh)%n", c.id(), c.codCurso(), c.nomeCurso(), c.cargaHoraria())
            );
        }
    }

    private static void buscarCursoPorId() {
        System.out.print("ID do curso: ");
        Long id = Long.parseLong(sc.nextLine());
        Curso c = CursoDAO.buscarPorId(id);
        if (c != null)
            System.out.printf("ID: %d | Código: %s | Nome: %s | Carga: %dh%n", c.id(), c.codCurso(), c.nomeCurso(), c.cargaHoraria());
        else
            System.out.println("Curso nao encontrado.");
    }

    private static void atualizarCurso() {
        System.out.print("ID do curso a atualizar: ");
        Long id = Long.parseLong(sc.nextLine());
        Curso existente = CursoDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("Curso nao encontrado.");
            return;
        }

        System.out.print("Novo codigo: ");
        String cod = sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Nova carga horaria: ");
        int carga = Integer.parseInt(sc.nextLine());

        Curso atualizado = new Curso(id, cod, nome, carga);
        CursoDAO.atualizarCurso(atualizado);
        System.out.println("✅ Curso atualizado com sucesso!");
    }

    private static void deletarCurso() {
        System.out.print("ID do curso a deletar: ");
        Long id = Long.parseLong(sc.nextLine());
        CursoDAO.deletarCurso(id);
        System.out.println("✅ Curso deletado com sucesso!");
    }

    // ================= MENU ALUNOS =================
    private static void menuAlunos() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR ALUNOS ---");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Listar Alunos");
            System.out.println("3 - Buscar Aluno por ID");
            System.out.println("4 - Atualizar Aluno");
            System.out.println("5 - Deletar Aluno");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> buscarAlunoPorId();
                case 4 -> atualizarAluno();
                case 5 -> deletarAluno();
                case 0 -> {}
                default -> System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarAluno() {
        System.out.print("Matricula: ");
        String matricula = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Documento: ");
        String doc = sc.nextLine();
        listarCursos();
        System.out.print("ID do curso: ");
        Long idCurso = Long.parseLong(sc.nextLine());
        Curso curso = CursoDAO.buscarPorId(idCurso);

        if (curso == null) {
            System.out.println("Curso invalido!");
            return;
        }

        Aluno aluno = new Aluno(null, matricula, nome, doc, curso);
        AlunoDao.criarAluno(aluno);
        System.out.println("✅ Aluno cadastrado com sucesso!");
    }

    private static void listarAlunos() {
        List<Aluno> alunos = AlunoDao.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            alunos.forEach(a ->
                    System.out.printf("[%d] %s - %s | Curso: %s%n", a.id(), a.matricula(), a.nome(), a.curso().nomeCurso())
            );
        }
    }

    private static void buscarAlunoPorId() {
        System.out.print("ID do aluno: ");
        Long id = Long.parseLong(sc.nextLine());
        Aluno a = AlunoDao.buscarPorId(id);
        if (a != null)
            System.out.printf("ID: %d | Nome: %s | Documento: %s | Curso: %s%n",
                    a.id(), a.nome(), a.documento(), a.curso().nomeCurso());
        else
            System.out.println("Aluno nao encontrado.");
    }

    private static void atualizarAluno() {
        System.out.print("ID do aluno a atualizar: ");
        Long id = Long.parseLong(sc.nextLine());
        Aluno existente = AlunoDao.buscarPorId(id);
        if (existente == null) {
            System.out.println("Aluno nao encontrado.");
            return;
        }

        System.out.print("Nova matricula: ");
        String matricula = sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo documento: ");
        String doc = sc.nextLine();
        listarCursos();
        System.out.print("ID do novo curso: ");
        Long idCurso = Long.parseLong(sc.nextLine());
        Curso curso = CursoDAO.buscarPorId(idCurso);

        if (curso == null) {
            System.out.println("Curso invalido!");
            return;
        }

        Aluno atualizado = new Aluno(id, matricula, nome, doc, curso);
        AlunoDao.atualizarAluno(atualizado);
        System.out.println("✅ Aluno atualizado com sucesso!");
    }

    private static void deletarAluno() {
        System.out.print("ID do aluno a deletar: ");
        Long id = Long.parseLong(sc.nextLine());
        AlunoDao.deletarAluno(id);
        System.out.println("✅ Aluno deletado com sucesso!");
    }
}



/*package application;

import model.Aluno;
import model.Curso;
import repository.AlunoDao;
import repository.CursoDAO;
import repository.FabricaConexao;
import java.sql.Connection;

public class main {
    public static void main(String[] args) {
        System.out.println("Inicializando o sistema...");

        try (Connection conexao = FabricaConexao.buscarConexao()) {
            FabricaConexao.criarTabelas(conexao);

            Curso curso = new Curso(1L, "123", "Engenharia de Software", 3600);
            CursoDAO.criarCurso(curso);

            Aluno aluno = new Aluno(1L, "A001", "Marcelo Boeira", "999.999.999-99", curso);
            AlunoDao.criarAluno(aluno);

            System.out.println("Aluno e curso cadastrados com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/


/*package application;

import model.Aluno;
import model.Curso;
import repository.AlunoDao;
import repository.CursoDAO;
import repository.FabricaConexao;

public class main {
    public static void main(String[] args) {
        Curso curso = new Curso(1L, "123", "Teste", 136);
        Aluno aluno = new Aluno(1L, "123", "Teste", "123", curso);

        // Primeiro cria o curso
        CursoDAO.criarCurso(curso);

        // Depois cria o aluno vinculado a esse curso
        AlunoDao.criarAluno(aluno);
    }
}*/


/*public class main {
    public static void main(String[] args) {
        Curso curso = new Curso(1L, "123", "Teste", 136 );
        Aluno aluno = new Aluno(1L, "123", "Teste", "123", curso);

        AlunoDao.criarAluno(aluno);

    }
}*/
