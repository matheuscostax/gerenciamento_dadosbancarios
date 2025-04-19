package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.*;
import entidades.*;

import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        Gerente gerente = new Gerente(3, "jamires", 5000.0, "gerente", "aluno");
        do {
            System.out.println("====== Menu de Opções ======");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Listar Contas");
            System.out.println("4. Excluir Conta");
            System.out.println("5. Adicionar um funcionário");
            System.out.println("6. Excluir um funcionário");
            System.out.println("7. Exibir Funcionários");
            System.out.println("8. Realizar Depósito");
            System.out.println("9. Realizar Saque");
            System.out.println("10. Fazer Tranferência Pix");
            System.out.println("11. Fazer Transferência Ted");
            System.out.println("12. Sair\n");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o cpf ou deixe vazio: ");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o cnpj ou deixe vazio: ");
                    String cnpj = scanner.nextLine();
                    System.out.println("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.println("Digite o tipo (FISICO/JURIDICO): ");
                    String tipo = scanner.nextLine();

                    Cliente cliente = null;

                    if (tipo.equalsIgnoreCase("FISICO")) {
                        cliente = new ClientePF(0, nome, email, tipo, cpf.isEmpty() ? null : cpf);
                    } else if (tipo.equalsIgnoreCase("JURIDICO")) {
                        cliente = new ClientePJ(0, nome, email, tipo, cnpj.isEmpty() ? null : cnpj);
                    } else {
                        System.out.println("Tipo inválido.");
                    }

                    ClienteDAO.adicionarCliente(cliente);
                    break;

                case 2:
                    System.out.println("ID do cliente: ");
                    int cliente_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Tipo da conta (Corrente/Poupança): ");
                    String tipoConta = scanner.nextLine();
                    System.out.println("Digite a chave PIX: ");
                    String chavePIX = scanner.nextLine();
                    ContaDAO.criarConta(cliente_id, tipoConta, chavePIX);
                    break;

                case 3:
                    ContaDAO.listarContas();
                    break;


                case 4:
                    System.out.println("=== Acesso Restrito: Exclusão de Conta ===");
                    System.out.println("Digite o nome do gerente: ");
                    String nomeGerente = scanner.nextLine();

                    System.out.println("Digite o e-mail do gerente: ");
                    email = scanner.nextLine();

                    System.out.println("Digite a senha do gerente: ");
                    String senha = scanner.nextLine();

                    if (gerente.autenticar(senha)) {
                        System.out.println("Autenticado com sucesso!");
                        System.out.println("Digite o número da conta a ser apagada: ");
                        String numeroConta = scanner.nextLine();
                        ContaDAO.excluirConta(numeroConta);
                    } else {
                        System.out.println("Autenticação falhou. Acesso negado.");
                    }
                    break;


                case 5:
                    System.out.println("Digite o nome do funcionário");
                    nome = scanner.nextLine();
                    System.out.println("Digite o salário");
                    Double salario = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite o cargo do funcionário");
                    String cargo = scanner.nextLine();
                    FuncionarioDAO.adicionarFuncionario(nome, salario, cargo);
                    break;

                case 6:
                    System.out.println("Selecione o id do funcionário a ser excluido");
                    int id_funcionario = scanner.nextInt();
                    FuncionarioDAO.excluirFuncionario(id_funcionario);
                    break;


                case 7:
                    FuncionarioDAO.listarFuncionarios();
                    break;


                case 8:
                    System.out.println("Valor: ");
                    Double valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Número da conta: ");
                    String numeroConta = scanner.nextLine();

                    TransacaoDAO.deposito(numeroConta, valor);
                    break;

                case 9:
                    System.out.println("Valor: ");
                    valor = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Número Conta: ");
                    numeroConta = scanner.nextLine();
                    TransacaoDAO.saque(numeroConta, valor);
                    break;

                case 10:
                    System.out.println("Digite o valor a ser enviado: ");
                    valor = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite o Número da sua Chave Pix: ");
                    String chaveOrigem = scanner.nextLine();
                    System.out.println("Digite o Número da Chave de Destino: ");
                    String chaveDestino = scanner.nextLine();

                    Transferencia_PIX pix = new Transferencia_PIX(valor);
                    pix.realizarTransferencia(chaveOrigem, chaveDestino);
                    break;
                case 11:
                    System.out.println("Digite o valor: ");
                    valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Digite o numero da conta de origem: ");
                    String contaOrigem = scanner.nextLine();

                    System.out.println("Digite o numero da conta de destino: ");
                    String contaDestino = scanner.nextLine();

                    Transferencia_TED ted = new Transferencia_TED(valor);
                    ted.realizarTransferencia(contaOrigem, contaDestino);
                    break;

                case 12:
                    System.out.println("Saindo do programa....");
                    break;

            }
        }
        while (opcao != 12) ;
        scanner.close();

    }
}
