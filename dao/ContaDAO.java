package dao;

import java.sql.*;
import entidades.Conta;
import java.sql.PreparedStatement;
import java.util.Random;

    public class ContaDAO {
        //Método para criar conta
        public static void criarConta(int cliente_id, String tipoConta, String chavePIX) {
            if (cliente_id == -1) {
                System.out.println("Não foi possível criar a conta. Cliente inválido.");
                return;
            }

            String sql = "INSERT INTO conta (numeroConta, saldo, tipo, chavePix, cliente_id) VALUES (?, ?, ?, ?, ?)";
            String numeroConta = gerarNumeroConta();

            try (Connection conexao = ConexaoMySQL.getConexao();
                 PreparedStatement statement = conexao.prepareStatement(sql)) {

                statement.setString(1, numeroConta);
                statement.setDouble(2, 0.0); // saldo inicial
                statement.setString(3, tipoConta);
                statement.setString(4, chavePIX);
                statement.setInt(5, cliente_id);

                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Conta criada com sucesso! Número da conta: " + numeroConta);
                } else {
                    System.out.println("Erro ao criar conta.");
                }

            } catch (SQLException e) {
                System.err.println("Erro ao criar conta: " + e.getMessage());
            }
        }

        // Método auxiliar privado para gerar número da conta aleatório
        private static String gerarNumeroConta() {
            Random rand = new Random();
            StringBuilder numeroConta = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                numeroConta.append(rand.nextInt(10));
            }
            return numeroConta.toString();
        }

        //Método para excluir Conta
        public static void excluirConta(String numeroConta) {
            String sql = "DELETE FROM conta WHERE numeroConta = ?";

            try (Connection conexao = ConexaoMySQL.getConexao();
                 PreparedStatement statement = conexao.prepareStatement(sql)) {

                statement.setString(1, numeroConta);
                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("A conta " + numeroConta + " foi excluída com êxito!");
                } else {
                    System.out.println("Conta não encontrada.");
                }

            } catch (SQLException e) {
                System.err.println("Erro ao excluir conta: " + e.getMessage());
            }
        }
        //Método para listar contas
        public static void listarContas() {
            String sql = "SELECT * FROM conta";

            try (Connection conexao = ConexaoMySQL.getConexao();
                 PreparedStatement statement = conexao.prepareStatement(sql);
                 ResultSet resultado = statement.executeQuery()) {

                System.out.println("As contas existentes são:");
                System.out.println("==========================");

                while (resultado.next()) {
                    int id = resultado.getInt("id_conta");
                    String numeroConta = resultado.getString("numeroConta");
                    double saldo = resultado.getDouble("saldo");
                    String tipoConta = resultado.getString("tipo");
                    String chavePIX = resultado.getString("chavePIX");

                    System.out.println("ID: " + id);
                    System.out.println("Número da Conta: " + numeroConta);
                    System.out.println("Saldo: R$ " + saldo);
                    System.out.println("Tipo: " + tipoConta);
                    System.out.println("Chave PIX: " + chavePIX);
                    System.out.println("--------------------------");
                }

            } catch (SQLException e) {
                System.err.println("Erro ao listar contas: " + e.getMessage());
            }
        }
    }











