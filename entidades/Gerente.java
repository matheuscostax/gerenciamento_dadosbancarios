package entidades;

import entidades.Autenticavel;

public class Gerente extends Funcionario implements Autenticavel {
    private String senha;

    public Gerente(int id_funcionario, String nome, double salario, String cargo, String senha) {
        super(id_funcionario, nome, salario, cargo);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public boolean autenticar(String senha){
    return this.senha.equals(senha);
    }
}

