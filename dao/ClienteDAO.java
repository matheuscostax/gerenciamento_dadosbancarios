package dao;

import entidades.Cliente;
import entidades.ClientePF;
import entidades.ClientePJ;

import java.sql.*;

public class ClienteDAO {

    public static void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, cnpj, email, tipo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNome());

            if (cliente instanceof ClientePF) {
                String cpf = ((ClientePF) cliente).getCpf();
                statement.setString(2, cpf != null ? cpf : null);
            } else {
                statement.setString(2, null);
            }

            if (cliente instanceof ClientePJ) {
                String cnpj = ((ClientePJ) cliente).getCnpj();
                statement.setString(3, cnpj != null ? cnpj : null);
            } else {
                statement.setString(3, null);
            }

            statement.setString(4, cliente.getEmail());

            // Define tipo (PF ou PJ)
            String tipo = (cliente instanceof ClientePF) ? "PF" : "PJ";
            statement.setString(5, tipo);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    cliente.setId_cliente(idGerado);  // define o ID no objeto
                    System.out.println("Usuário inserido com êxito! ID gerado: " + idGerado);
                }
            } else {
                System.out.println("Erro ao inserir usuário");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

}
