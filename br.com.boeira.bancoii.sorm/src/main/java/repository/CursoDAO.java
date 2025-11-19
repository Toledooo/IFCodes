package repository;

import model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public static void criarCurso(Curso curso) {
        String sql = "INSERT INTO curso(curso_codigo, curso_nome, curso_carga_horaria) VALUES (?, ?, ?)";

        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, curso.codCurso());
            preparedStatement.setString(2, curso.nomeCurso());
            preparedStatement.setInt(3, curso.cargaHoraria());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar curso", e);
        }
    }

    public static List<Curso> listarCursos() {
        String sql = "SELECT * FROM curso";
        List<Curso> cursos = new ArrayList<>();

        try (Connection connection = FabricaConexao.buscarConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Curso curso = new Curso(
                        resultSet.getLong("curso_id"),
                        resultSet.getString("curso_codigo"),
                        resultSet.getString("curso_nome"),
                        resultSet.getInt("curso_carga_horaria")
                );
                cursos.add(curso);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar cursos", e);
        }

        return cursos;
    }

    public static Curso buscarPorId(Long id) {
        String sql = "SELECT * FROM curso WHERE curso_id = ?";
        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getLong("curso_id"),
                        rs.getString("curso_codigo"),
                        rs.getString("curso_nome"),
                        rs.getInt("curso_carga_horaria")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar curso por ID", e);
        }
        return null;
    }

    public static void atualizarCurso(Curso curso) {
        String sql = """
                UPDATE curso 
                SET curso_codigo = ?, curso_nome = ?, curso_carga_horaria = ? 
                WHERE curso_id = ?
                """;
        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, curso.codCurso());
            ps.setString(2, curso.nomeCurso());
            ps.setInt(3, curso.cargaHoraria());
            ps.setLong(4, curso.id());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar curso", e);
        }
    }

    public static void deletarCurso(Long id) {
        String sql = "DELETE FROM curso WHERE curso_id = ?";

        try (Connection connection = FabricaConexao.buscarConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar curso", e);
        }
    }
}