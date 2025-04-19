package dao;

public abstract class Transferencia {
    protected double valor;

    public Transferencia(double valor) {
        this.valor = valor;
    }

    public abstract void realizarTransferencia(String origem, String destino);

}
