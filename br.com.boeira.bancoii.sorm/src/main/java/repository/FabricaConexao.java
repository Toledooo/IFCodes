package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FabricaConexao {

    private static final String URL = "jdbc:h2:file:./bancoii";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection buscarConexao() throws Exception {
        try {
            Class.forName("org.h2.Driver");

            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Erro ao criar conexão com o banco", e);
        }
    }


    public static void criarTabelas(Connection connection) {
        String criarTabelaCurso = """
        CREATE TABLE IF NOT EXISTS curso (
            curso_id INT AUTO_INCREMENT PRIMARY KEY,
            curso_codigo VARCHAR(20) NOT NULL,
            curso_nome VARCHAR(100) NOT NULL,
            curso_carga_horaria INT NOT NULL
        );
        """;

        String criarTabelaAluno = """
        CREATE TABLE IF NOT EXISTS aluno (
            aluno_id INT AUTO_INCREMENT PRIMARY KEY,
            aluno_matricula VARCHAR(20) NOT NULL,
            aluno_nome VARCHAR(100) NOT NULL,
            aluno_documento VARCHAR(20) NOT NULL,
            fk_curso_id int NOT NULL,
            CONSTRAINT fk_curso_aluno FOREIGN KEY (fk_curso_id) REFERENCES curso (curso_id)
        );
        """;

        try (Statement statement = connection.createStatement()){
            System.out.println("Executando criação da tabela 'curso'...");
            statement.execute(criarTabelaCurso);
            System.out.println("Tabela 'curso' verificada/criada.");

            System.out.println("Executando criação da tabela 'aluno'...");
            statement.execute(criarTabelaAluno);
            System.out.println("Tabela 'aluno' verificada/criada.");

        } catch (Exception e) {
            // Lança uma exceção mais específica para falha de DDL
            throw new RuntimeException("Erro ao criar tabelas", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Iniciando aplicação e conectando ao banco...");

        try (Connection conexao = buscarConexao()) {

            System.out.println("Conexão estabelecida com sucesso!");

            criarTabelas(conexao);

            System.out.println("Processo de inicialização do banco concluído.");

        } catch (Exception e) {
            System.err.println("Ocorreu um erro geral:");
            e.printStackTrace();
        }
    }
}