package dao;

import entidades.Funcionario;

import java.sql.*;

public class FuncionarioDAO {

    public static void adicionarFuncionario(String nome, double salario, String cargo) {
        String sql = "INSERT INTO funcionario (nome, salario, cargo) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, nome);
            statement.setDouble(2, salario);
            statement.setString(3, cargo);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário adicionado com êxito!");
            } else {
                System.out.println("Não foi possível adicionar o funcionário.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public static void excluirFuncionario(int id_funcionario) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, id_funcionario);

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário com ID " + id_funcionario + " excluído com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
    public static void listarFuncionarios() {
        String sql = "SELECT * FROM funcionario";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            System.out.println("Os funcionários existentes são:");
            System.out.println("===============================");

            while (resultado.next()) {
                int idFuncionario = resultado.getInt("id_funcionario");
                String nome = resultado.getString("nome");
                double salario = resultado.getDouble("salario");
                String cargo = resultado.getString("cargo");

                System.out.println("ID: " + idFuncionario);
                System.out.println("Nome: " + nome);
                System.out.println("Salário: R$ " + salario);
                System.out.println("Cargo: " + cargo);
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }

}