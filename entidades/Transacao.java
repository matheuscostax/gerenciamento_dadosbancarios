package entidades;

import java.sql.*;
import java.time.LocalDateTime;
public class Transacao {
    private int id_transacao;
    private int id_conta;
    private double valor;
    private LocalDateTime data_transacao;
    private String transferencia;

    public Transacao(int id_transacao, int id_conta, double valor, LocalDateTime data_transacao, String transferencia) {
        this.id_transacao = id_transacao;
        this.id_conta = id_conta;
        this.valor = valor;
        this.data_transacao = data_transacao;
        this.transferencia = transferencia;
    }

    public int getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(int id_transacao) {
        this.id_transacao = id_transacao;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData_transacao() {
        return data_transacao;
    }

    public void setData_transacao(LocalDateTime data_transacao) {
        this.data_transacao = data_transacao;
    }

    public String getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(String transferencia) {
        this.transferencia = transferencia;
    }
}
