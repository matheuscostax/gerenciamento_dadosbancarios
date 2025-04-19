package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transferencia_TED extends Transferencia{

    public Transferencia_TED(double valor) {
        super(valor);
    }

    @Override
    public void realizarTransferencia(String contaOrigem, String contaDestino) {
        if (valor <= 0) {
            System.out.println("A transferência deve ser maior que 0.");
            return;
        }

        String sqlBuscaId = "SELECT id_conta FROM conta WHERE numeroConta = ?";
        String sqlDebito = "UPDATE conta SET saldo = saldo - ? WHERE numeroConta = ? AND saldo >= ?";
        String sqlCredito = "UPDATE conta SET saldo = saldo + ? WHERE numeroConta = ?";
        String sqlTransacao = "INSERT INTO transacao (conta_id, tipo_transacao, valor, data_transacao, tipo_tranferencia) VALUES (?, ?, ?, NOW(), ?)";

        try (Connection conexao = ConexaoMySQL.getConexao()) {
            conexao.setAutoCommit(false);

            try (PreparedStatement debitoStmt = conexao.prepareStatement(sqlDebito);
                 PreparedStatement creditoStmt = conexao.prepareStatement(sqlCredito);
                 PreparedStatement transacaoStmt = conexao.prepareStatement(sqlTransacao);
                 PreparedStatement buscarIdStmt = conexao.prepareStatement(sqlBuscaId)) {

                buscarIdStmt.setString(1, contaOrigem);
                ResultSet resultado = buscarIdStmt.executeQuery();

                int contaId;
                if (resultado.next()) {
                    contaId = resultado.getInt("id_conta");
                } else {
                    System.out.println("Conta de origem não encontrada.");
                    conexao.rollback();
                    return;
                }

                // Debita da conta de origem
                debitoStmt.setDouble(1, valor);
                debitoStmt.setString(2, contaOrigem);
                debitoStmt.setDouble(3, valor);

                if (debitoStmt.executeUpdate() == 0) {
                    System.out.println("Saldo insuficiente ou conta de origem não encontrada.");
                    conexao.rollback();
                    return;
                }

                // Credita na conta de destino
                creditoStmt.setDouble(1, valor);
                creditoStmt.setString(2, contaDestino);

                if (creditoStmt.executeUpdate() == 0) {
                    System.out.println("Conta de destino não encontrada.");
                    conexao.rollback();
                    return;
                }

                // Registra transação
                transacaoStmt.setInt(1, contaId);
                transacaoStmt.setString(2, null);
                transacaoStmt.setDouble(3, valor);
                transacaoStmt.setString(4, "ted");
                transacaoStmt.executeUpdate();

                conexao.commit();
                System.out.printf("Transferência de R$%.2f da conta %s para a conta %s realizada com sucesso!\n",
                        valor, contaOrigem, contaDestino);

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
