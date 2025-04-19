package entidades;

public class ClientePJ extends Cliente{
    private String cnpj;

    public ClientePJ(int id_cliente, String nome, String email,String cnpj, String tipo) {
        super(id_cliente, nome, email, tipo);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
