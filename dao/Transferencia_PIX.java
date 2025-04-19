package dao;

import java.sql.*;
//Polimorfismo
public class Transferencia_PIX extends Transferencia {

    public Transferencia_PIX(double valor) {
        super(valor);
    }

    @Override
    public void realizarTransferencia(String chaveOrigem, String chaveDestino) {
        if (valor <= 0) {
            System.out.println("A transferência deve ser maior que 0.");
            return;
        }

        String sqlBuscaId = "SELECT id_conta FROM conta WHERE chavePIX = ?";
        String sqlDebito = "UPDATE conta SET saldo = saldo - ? WHERE chavePIX = ? AND saldo >= ?";
        String sqlCredito = "UPDATE conta SET saldo = saldo + ? WHERE chavePIX = ?";
        String sqlTransacao = "INSERT INTO transacao (conta_id, tipo_transacao, valor, data_transacao, tipo_tranferencia) VALUES (?, ?, ?, NOW(), ?)";

        try (Connection conexao = ConexaoMySQL.getConexao()) {
            conexao.setAutoCommit(false);

            try (PreparedStatement debitoStmt = conexao.prepareStatement(sqlDebito);
                 PreparedStatement creditoStmt = conexao.prepareStatement(sqlCredito);
                 PreparedStatement transacaostatement = conexao.prepareStatement(sqlTransacao);
                 PreparedStatement buscarIdstatement = conexao.prepareStatement(sqlBuscaId)) {

                buscarIdstatement.setString(1, chaveOrigem);
                ResultSet resultado = buscarIdstatement.executeQuery();
                int contaId;
                if (resultado.next()) {
                    contaId = resultado.getInt("id_conta");
                } else {
                    System.out.println("Chave de origem não encontrada.");
                    conexao.rollback();
                    return;
                }

                debitoStmt.setDouble(1, valor);
                debitoStmt.setString(2, chaveOrigem);
                debitoStmt.setDouble(3, valor);
                if (debitoStmt.executeUpdate() == 0) {
                    System.out.println("Saldo insuficiente ou conta de origem não encontrada!");
                    conexao.rollback();
                    return;
                }

                creditoStmt.setDouble(1, valor);
                creditoStmt.setString(2, chaveDestino);
                if (creditoStmt.executeUpdate() == 0) {
                    System.out.println("Conta de destino não encontrada.");
                    conexao.rollback();
                    return;
                }

                transacaostatement.setInt(1, contaId);
                transacaostatement.setString(2, null);
                transacaostatement.setDouble(3, valor);
                transacaostatement.setString(4, "pix");
                transacaostatement.executeUpdate();

                conexao.commit();
                System.out.printf("Transferência de R$ %.2f da chave %s para a chave %s realizada com sucesso!\n",
                        valor, chaveOrigem, chaveDestino);

            } catch (SQLException e) {
                conexao.rollback();
                System.err.println("Erro ao executar a transferência: " + e.getMessage());
            } finally {
                conexao.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão ao transferir: " + e.getMessage());
        }
    }
}
