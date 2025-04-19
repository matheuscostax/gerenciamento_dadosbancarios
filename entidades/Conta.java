package entidades;

public class Conta {
    private int id_conta;
    private String numeroConta;
    private String saldo;
    private String tipoConta;
    private int cliente_id;
    private String chavePIX;

    public Conta(int id_conta, String numeroConta, String saldo, String tipoConta, int cliente_id, String chavePIX) {
        this.id_conta = id_conta;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.cliente_id = cliente_id;
        this.chavePIX = chavePIX;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getChavePIX() {
        return chavePIX;
    }

    public void setChavePIX(String chavePIX) {
        this.chavePIX = chavePIX;
    }
}
