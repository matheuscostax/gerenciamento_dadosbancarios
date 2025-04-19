package entidades;

public abstract class Cliente {
    private int id_cliente;
    private String nome;
    private String email;
    private String tipo;

    public Cliente(int id_cliente, String nome, String email, String tipo) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
