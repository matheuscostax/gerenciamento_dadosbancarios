package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciamentobanco";
    private static final String USUARIO = "matheus";
    private static final String SENHA = "aluno";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }

    // Teste de conexão (pode remover em produção)
    public static void main(String[] args) {
        Connection teste = getConexao();
        if (teste != null) {
            System.out.println("Conectado com sucesso!");
        }
    }
}
