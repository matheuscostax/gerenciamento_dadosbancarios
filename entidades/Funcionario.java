package entidades;

public abstract class Funcionario {
private int id_funcionario;
private String nome;
private double salario;
private String cargo;

    public Funcionario(int id_funcionario, String nome, double salario, String cargo) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
