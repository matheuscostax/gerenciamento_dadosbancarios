package dao;


import entidades.Transacao;
import java.sql.*;

public class TransacaoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciamentobanco";
    private static final String USUARIO = "matheus";
    private static final String SENHA = "aluno";

    public static void deposito(String numeroConta, double valor){
        String sql = "update conta set saldo = saldo + ? where numeroConta = ?";
        String sqlBuscaId = "select id_conta from conta where numeroConta = ?";
        String sqlTransacao = "insert into transacao (conta_id, tipo_transacao, valor, data_transacao, tipo_tranferencia) VALUES (?, ?, ?, NOW(), ?)";

        if(valor <= 0){
            System.out.println("O valor do deposito deve ser positivo!!");
            return;
        }

        try(Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement statement = conexao.prepareStatement(sql);
            PreparedStatement buscaIdstatement = conexao.prepareStatement(sqlBuscaId);
            PreparedStatement transacaostatement = conexao.prepareStatement(sqlTransacao)){

            statement.setDouble(1, valor);
            statement.setString(2, numeroConta);

            int linhasAfetadas = statement.executeUpdate();

            buscaIdstatement.setString(1, numeroConta);
            ResultSet resultado = buscaIdstatement.executeQuery();

            int contaId;

            if (resultado.next()) {
                contaId = resultado.getInt("id_conta");
            } else {
                System.out.println("Número Conta não encontrado.");
                conexao.rollback();
                return;
            }

            transacaostatement.setInt(1, contaId);
            transacaostatement.setString(2, "deposito");
            transacaostatement.setDouble(3, valor);
            transacaostatement.setString(4, null);
            transacaostatement.executeUpdate();


            if(linhasAfetadas > 0){
                System.out.println("Depósito de R$ " + valor + " realizado com sucesso na conta!!");
            } else {
                System.out.println("Conta não encontrada");
            }
        } catch (SQLException e){
            System.err.println("Erro ao realizar deposito " + e.getMessage());
        }

    }

    public static void saque(String numeroConta, double valor){
        String sql = "update conta set saldo = saldo - ? where numeroConta = ? and saldo >= ?";
        String sqlBuscaId = "select id_conta from conta where numeroConta = ?";
        String sqlTransacao = "insert into transacao (conta_id, tipo_transacao, valor, data_transacao, tipo_tranferencia) VALUES (?, ?, ?, NOW(), ?)";

        if(valor <= 0){
            System.out.println("O valor do saque deve ser positivo!!");
            return;
        }

        try(Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            PreparedStatement statement = conexao.prepareStatement(sql);
            PreparedStatement buscaIdstatement = conexao.prepareStatement(sqlBuscaId);
            PreparedStatement transacaostatement = conexao.prepareStatement(sqlTransacao)){


            buscaIdstatement.setString(1, numeroConta);
            ResultSet resultado = buscaIdstatement.executeQuery();
            int contaId;

            if (resultado.next()) {
                contaId = resultado.getInt("id_conta");
            } else {
                System.out.println("Chave de origem não encontrada.");
                conexao.rollback();
                return;
            }

            statement.setDouble(1, valor);
            statement.setString(2, numeroConta);
            statement.setDouble(3, valor); // Verificação de saldo suficiente

            int linhasAfetadas = statement.executeUpdate();

            transacaostatement.setInt(1, contaId);
            transacaostatement.setString(2, "saque");
            transacaostatement.setDouble(3, valor);
            transacaostatement.setString(4, null);
            transacaostatement.executeUpdate();


            if(linhasAfetadas > 0){
                System.out.println("Saque de R$ " + valor + " realizado com sucesso na conta!!");
            } else {
                System.out.println("Conta não encontrada ou saldo negativo");
            }
        } catch (SQLException e){
            System.err.println("Erro ao realizar saque " + e.getMessage());
        }

    }

}
