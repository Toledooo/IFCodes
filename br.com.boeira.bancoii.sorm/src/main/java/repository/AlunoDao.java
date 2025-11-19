package repository;

import model.Aluno;
import model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    public static void criarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno(aluno_matricula, aluno_nome, aluno_documento, fk_curso_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, aluno.matricula());
            preparedStatement.setString(2, aluno.nome());
            preparedStatement.setString(3, aluno.documento());
            preparedStatement.setLong(4, aluno.curso().id());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar aluno", e);
        }
    }

    public static List<Aluno> listarAlunos() {
        String sql = """
                SELECT a.*, c.curso_codigo, c.curso_nome, c.curso_carga_horaria 
                FROM aluno a
                JOIN curso c ON a.fk_curso_id = c.curso_id
                """;
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = FabricaConexao.buscarConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Curso curso = new Curso(
                        resultSet.getLong("fk_curso_id"),
                        resultSet.getString("curso_codigo"),
                        resultSet.getString("curso_nome"),
                        resultSet.getInt("curso_carga_horaria")
                );

                Aluno aluno = new Aluno(
                        resultSet.getLong("aluno_id"),
                        resultSet.getString("aluno_matricula"),
                        resultSet.getString("aluno_nome"),
                        resultSet.getString("aluno_documento"),
                        curso
                );
                alunos.add(aluno);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }

        return alunos;
    }

    public static Aluno buscarPorId(Long id) {
        String sql = """
                SELECT a.*, c.curso_codigo, c.curso_nome, c.curso_carga_horaria 
                FROM aluno a
                JOIN curso c ON a.fk_curso_id = c.curso_id
                WHERE a.aluno_id = ?
                """;
        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso(
                        rs.getLong("fk_curso_id"),
                        rs.getString("curso_codigo"),
                        rs.getString("curso_nome"),
                        rs.getInt("curso_carga_horaria")
                );

                return new Aluno(
                        rs.getLong("aluno_id"),
                        rs.getString("aluno_matricula"),
                        rs.getString("aluno_nome"),
                        rs.getString("aluno_documento"),
                        curso
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar aluno por ID", e);
        }
        return null;
    }

    public static void atualizarAluno(Aluno aluno) {
        String sql = """
                UPDATE aluno 
                SET aluno_matricula = ?, aluno_nome = ?, aluno_documento = ?, fk_curso_id = ?
                WHERE aluno_id = ?
                """;
        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, aluno.matricula());
            ps.setString(2, aluno.nome());
            ps.setString(3, aluno.documento());
            ps.setLong(4, aluno.curso().id());
            ps.setLong(5, aluno.id());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public static void deletarAluno(Long id) {
        String sql = "DELETE FROM aluno WHERE aluno_id = ?";

        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }
}