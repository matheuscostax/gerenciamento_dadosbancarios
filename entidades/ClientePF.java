package entidades;

public class ClientePF extends Cliente{
    private String cpf;

    public ClientePF(int id_cliente, String nome, String email, String tipo, String cpf) {
        super(id_cliente, nome, email, tipo);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}

